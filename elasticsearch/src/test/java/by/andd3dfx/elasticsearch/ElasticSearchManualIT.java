package by.andd3dfx.elasticsearch;

import static java.lang.Thread.sleep;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

import by.andd3dfx.elasticsearch.dto.Person;
import com.alibaba.fastjson2.JSON;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This test requires Elasticsearch instance running with cluster name `elasticsearch`
 */
public class ElasticSearchManualIT {

    private static final Logger log = Logger.getLogger(ElasticSearchManualIT.class.getName());

    private static List<Person> listOfPersons = new ArrayList<>() {{
        add(new Person(8, "John Woodcraft", new Date()));
        add(new Person(10, "John Doe", new Date()));
        add(new Person(25, "Janette Doe", new Date()));
        add(new Person(55, "John Smith", new Date()));
        add(new Person(65, "Susan Stevenson", new Date()));
    }};
    private static Client client;

    @BeforeClass
    public static void setUp() throws UnknownHostException, InterruptedException {
        client = new PreBuiltTransportClient(Settings.builder()
            .put("cluster.name", "docker-cluster").build())
            .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

        for (Person person : listOfPersons) {
            IndexResponse response = createIndexResponse(person);
            assertEquals(Result.CREATED, response.getResult());
            assertEquals(response.getIndex(), "people");
        }
        sleep(1000);
    }

    @AfterClass
    public static void tearDown() {
        client.close();
    }

    private static IndexResponse createIndexResponse(Object object) {
        String jsonObject = JSON.toJSONString(object);
        return client
            .prepareIndex("people", "Bla")
            .setSource(jsonObject, XContentType.JSON)
            .get();
    }

    @Test
    public void peopleIndicesSearch() {
        SearchResponse response = client
            .prepareSearch()
            .setIndices("people")
            .get();

        checkSearchResponse(response, 5);
    }

    @Test
    public void filterByAgeSearch() {
        SearchResponse response = client
            .prepareSearch()
            .setQuery(QueryBuilders.rangeQuery("age").from(10).to(30))
            .get();

        checkSearchResponse(response, 2);
    }

    /**
     * Trying to search with error in searched name
     */
    @Test
    public void fuzzySearch() {
        SearchResponse response = client
                .prepareSearch()
                .setQuery(QueryBuilders.fuzzyQuery("fullName", "Sevenson"))
                .get();

        var result = checkSearchResponse(response, 1);
        log.info("Found next persons: " + result);
    }

    private List<Person> checkSearchResponse(SearchResponse searchResponse, int expectedSize) {
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        List<Person> result = Arrays.stream(searchHits)
            .map(hit -> JSON.parseObject(hit.getSourceAsString(), Person.class))
            .toList();
        assertThat("Wrong result items count", result.size(), is(expectedSize));
        return result;
    }
}
