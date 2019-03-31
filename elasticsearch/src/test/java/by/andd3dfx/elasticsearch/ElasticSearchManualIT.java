package by.andd3dfx.elasticsearch;

import static java.lang.Thread.sleep;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This Manual test requires: Elasticsearch instance running on host with cluster name = elasticsearch
 */
public class ElasticSearchManualIT {

    private static List<Person> listOfPersons = new ArrayList<Person>() {{
        add(new Person(10, "John Doe", new Date()));
        add(new Person(25, "Janette Doe", new Date()));
        add(new Person(55, "John Smith", new Date()));
    }};
    private static Client client;

    @BeforeClass
    public static void setUp() throws UnknownHostException, InterruptedException {
        client = new PreBuiltTransportClient(Settings.builder()
            .put("cluster.name", "docker-cluster").build())
            .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

        for (Person person : listOfPersons) {
            IndexResponse response = createIndexResponseFirstWay(person);
            assertEquals(Result.CREATED, response.getResult());
            assertEquals(response.getIndex(), "people");
            assertEquals(response.getType(), "Doe");
        }
        sleep(1000);
    }

    @AfterClass
    public static void tearDown() {
        client.close();
    }

    private static IndexResponse createIndexResponseFirstWay(Object object) {
        String jsonObject = JSON.toJSONString(object);
        return client
            .prepareIndex("people", "Doe")
            .setSource(jsonObject, XContentType.JSON)
            .get();
    }

    private static IndexResponse createIndexResponseSecondWay() throws IOException {
        XContentBuilder builder = XContentFactory
            .jsonBuilder()
            .startObject()
            .field("fullName", "Test")
            .field("salary", "11500")
            .field("age", "10")
            .endObject();
        return client
            .prepareIndex("people", "Doe")
            .setSource(builder)
            .get();
    }

    @Test
    public void givenJsonString_whenJavaObject_thenIndexDocument() {
        SearchResponse response = client
            .prepareSearch()
            .setQuery(QueryBuilders.rangeQuery("age").from(10).to(30))
            .get();
        checkSearchResponse(response, 2);
    }

    @Test
    public void allItemsSearch() {
        SearchResponse response = client
            .prepareSearch()
            .get();
        checkSearchResponse(response, 3);
    }

    @Test
    public void peopleIndicesSearch() {
        SearchResponse response = client
            .prepareSearch()
            .setIndices("people")
            .get();
        checkSearchResponse(response, 3);
    }

    @Test
    public void filterByAgeSearch() {
        SearchResponse response = client
            .prepareSearch()
            .setQuery(QueryBuilders.rangeQuery("age").from(10).to(30))
            .get();
        checkSearchResponse(response, 2);
    }

    private void checkSearchResponse(SearchResponse searchResponse, int expectedSize) {
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        List<Person> result = Arrays.stream(searchHits)
            .map(hit -> JSON.parseObject(hit.getSourceAsString(), Person.class))
            .collect(Collectors.toList());
        assertThat("Wrong result items count", result.size(), is(expectedSize));
    }
}