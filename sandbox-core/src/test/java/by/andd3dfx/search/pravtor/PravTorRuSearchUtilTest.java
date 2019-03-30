package by.andd3dfx.search.pravtor;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

public class PravTorRuSearchUtilTest {

    private String STARTING_URL = "https://pravtor.ru/viewforum.php?f=28";
    private PravTorRuSearchUtil searchUtil;

    @Before
    public void setup() {
        searchUtil = new PravTorRuSearchUtil();
    }

    @Test
    public void search() throws IOException, InterruptedException {
        List<TorrentData> result = searchUtil.search(new SearchCriteria(STARTING_URL, 150));

        System.out.println("Records retrieved: " + result.size());
        assertThat("Wrong amount of result records", result.size(), greaterThan(0));

        Files.write(Paths.get("target/pravtor-search-result.txt"),
            result.stream()
                .filter(item -> item.getDownloadedCount() != null)
                .sorted((o1, o2) -> o2.getDownloadedCount() - o1.getDownloadedCount())
                .map(String::valueOf)
                .collect(Collectors.toList()),
            Charset.defaultCharset());
    }
}
