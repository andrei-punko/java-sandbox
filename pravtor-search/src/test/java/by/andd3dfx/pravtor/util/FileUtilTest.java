package by.andd3dfx.pravtor.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import by.andd3dfx.pravtor.model.BatchSearchResult;
import by.andd3dfx.pravtor.model.SearchCriteria;
import by.andd3dfx.pravtor.model.TorrentData;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class FileUtilTest {

    private final String TEST_PARAMS_FILE = "src/test/resources/test-params.txt";
    private final String GENERATED_XLS_FILE = "target/generated-test-file.xls";
    private final String EXPECTED_XLS_FILE = "src/test/resources/expected-test-file.xls";

    private FileUtil fileUtil;

    @Before
    public void setup() {
        fileUtil = new FileUtil();
    }

    @Test
    public void loadSearchCriteria() throws IOException {
        List<SearchCriteria> criteriaItems = fileUtil.loadSearchCriteria(TEST_PARAMS_FILE);

        assertThat("Wrong count of criteria items", criteriaItems.size(), is(2));
        assertThat("Wrong url of first item", criteriaItems.get(0).getTopic(), is("txt-molitvy"));
        assertThat("Wrong label of first item", criteriaItems.get(0).getUrl(), is("https://pravtor.ru/viewforum.php?f=184"));
        assertThat("Wrong label of second item", criteriaItems.get(1).getTopic(), is("txt-kanony"));
        assertThat("Wrong url of second item", criteriaItems.get(1).getUrl(), is("https://pravtor.ru/viewforum.php?f=183"));
    }

    @Ignore
    @Test
    public void writeIntoExcel() throws IOException {

        List<BatchSearchResult> searchItems = Arrays.asList(
            new BatchSearchResult("Sheet label", Arrays.asList(
                buildTorrentData("label 1", 23, 12, 234, "23 Mb", "link1"),
                buildTorrentData("label 2", 22, 2, 54, "13 Mb", "link2")
            )),
            new BatchSearchResult("Sheet label 2", Arrays.asList(
                buildTorrentData("label 3", 32, 3, 678, "55 Mb", "link3"),
                buildTorrentData("label 4", 45, 5, 434, "22 Mb", "link4")
            ))
        );

        fileUtil.writeIntoExcel(GENERATED_XLS_FILE, searchItems);

        File file1 = new File(GENERATED_XLS_FILE);
        File file2 = new File(EXPECTED_XLS_FILE);
        assertThat("Files are different", FileUtils.contentEquals(file1, file2), is(true));
    }

    private TorrentData buildTorrentData(String label, int seedsCount, int peersCount, int downloadedCount,
        String size, String linkUrl) {
        TorrentData torrentData = new TorrentData();
        torrentData.setLabel(label);
        torrentData.setSeedsCount(seedsCount);
        torrentData.setPeersCount(peersCount);
        torrentData.setDownloadedCount(downloadedCount);
        torrentData.setSize(size);
        torrentData.setLinkUrl(linkUrl);
        return torrentData;
    }
}
