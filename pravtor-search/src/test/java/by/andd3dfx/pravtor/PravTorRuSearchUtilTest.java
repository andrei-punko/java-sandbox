package by.andd3dfx.pravtor;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class PravTorRuSearchUtilTest {

    private String STARTING_URL = "https://pravtor.ru/viewforum.php?f=28";  // Святоотеческие тексты и жития святых
    private String OUTPUT_FOLDER = "target/pravtor-search-result.txt";

    private PravTorRuSearchUtil searchUtil;

    @Before
    public void setup() {
        searchUtil = new PravTorRuSearchUtil();
    }

    @Test
    public void search() throws IOException, InterruptedException {
        List<TorrentData> result = searchUtil.batchSearch(STARTING_URL, 2, 20);

        assertThat("Wrong amount of result records", result.size(), greaterThan(0));

        searchUtil.saveResultsSorted(result, OUTPUT_FOLDER);
    }

    @Test
    public void testMain() throws IOException, InterruptedException {
        PravTorRuSearchUtil.main(new String[]{STARTING_URL, OUTPUT_FOLDER, "2"});
    }
}
