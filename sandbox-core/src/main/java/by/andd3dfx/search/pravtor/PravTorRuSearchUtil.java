package by.andd3dfx.search.pravtor;

import static java.lang.Thread.sleep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Util to perform search on http://pravtor.ru torrent tracker
 */
public class PravTorRuSearchUtil {

    private static final String USER_AGENT = "Mozilla";
    private String URL_WITH_STARTING_INDEX_SUFFIX = "%s&start=%s";

    public List<TorrentData> search(SearchCriteria criteria) throws InterruptedException, IOException {
        List<TorrentData> result = new ArrayList<>();

        for (int startingIndex = criteria.getMinStartingIndex();
            startingIndex <= criteria.getMaxStartingIndex();
            startingIndex += criteria.getStep()) {
            result.addAll(search(buildSearchUrl(criteria.getStartingPageUrl(), startingIndex)));

            sleep(20);  // Some throttling
        }
        return result;
    }

    private List<TorrentData> search(String topicStartingPageUrl) throws IOException {
        Elements elements = Jsoup
            .connect(topicStartingPageUrl)
            .userAgent(USER_AGENT).get()
            .select("tr[id^=tr-]");

        List<TorrentData> result = new ArrayList<>();
        for (Element element : elements) {

            TorrentData data = new TorrentData();
            data.setLabel(element.select("div[class=torTopic]").select("a").text());
            data.setRelativeLink(element.select("a[class=torTopic]").attr("href"));
            data.setSeedsCount(convertToInteger(element.select("span[title=Seeders]").text()));
            data.setPeersCount(convertToInteger(element.select("span[title=Leechers]").text()));
            data.setSize(element.select("div[title=Скачать .torrent]").select("div[class=small]").text());
            data.setDownloadedCount(convertToInteger(element.select("p[title=Скачан]").text()));

            result.add(data);
        }

        return result;
    }

    private Integer convertToInteger(String value) {
        if (!StringUtils.isNumeric(value)) {
            return null;
        }
        return Integer.parseInt(value);
    }

    private String buildSearchUrl(String startingUrl, int startingIndex) {
        return String.format(URL_WITH_STARTING_INDEX_SUFFIX, startingUrl, startingIndex);
    }
}
