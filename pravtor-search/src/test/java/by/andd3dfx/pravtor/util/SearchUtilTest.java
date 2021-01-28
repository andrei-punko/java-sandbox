package by.andd3dfx.pravtor.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import by.andd3dfx.pravtor.model.SingleSearchResult;
import by.andd3dfx.pravtor.model.TorrentData;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class SearchUtilTest {

    private String STARTING_URL = "https://pravtor.ru/viewforum.php?f=28";  // Святоотеческие тексты и жития святых

    private SearchUtil searchUtil;

    @Before
    public void setup() {
        searchUtil = new SearchUtil();
    }

    @Test
    public void batchSearch() throws IOException, InterruptedException {
        List<TorrentData> result = searchUtil.batchSearch(STARTING_URL, 2, 20);

        assertThat("Wrong amount of result records", result.size(), is(100));
    }

    @Test
    public void singleSearch() throws IOException {
        SingleSearchResult result = searchUtil.singleSearch(STARTING_URL);

        assertThat("Wrong amount of result records", result.getDataItems().size(), is(50));
    }

    @Test
    public void testMain() throws IOException, InterruptedException {
        SearchUtil.main(new String[]{"src/test/resources/test-params.txt", "target/tmp-result.xls"});
    }

    @Test
    public void testMainWithWrongParamsCount() throws IOException, InterruptedException {
        try {
            SearchUtil.main(new String[]{});
            fail("Exception should be thrown");
        } catch (IllegalArgumentException iae) {
            assertThat(iae.getMessage(), is("Should be 2 parameters!"));
        }

        try {
            SearchUtil.main(new String[]{"src/test/resources/test-params.txt"});
            fail("Exception should be thrown");
        } catch (IllegalArgumentException iae) {
            assertThat(iae.getMessage(), is("Should be 2 parameters!"));
        }

        try {
            SearchUtil.main(new String[]{"src/test/resources/test-params.txt", "target/tmp-result.xls", "one more"});
            fail("Exception should be thrown");
        } catch (IllegalArgumentException iae) {
            assertThat(iae.getMessage(), is("Should be 2 parameters!"));
        }
    }
}
