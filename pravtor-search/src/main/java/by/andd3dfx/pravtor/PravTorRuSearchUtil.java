package by.andd3dfx.pravtor;

import static java.lang.Thread.sleep;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Util to perform search on http://pravtor.ru torrent tracker
 */
public class PravTorRuSearchUtil {

    private static final String USER_AGENT = "Mozilla";
    private static final String PREFIX = "https://pravtor.ru/";

    public List<TorrentData> batchSearch(String startingPageUrl, int maxPagesCap, long throttlingDelay)
        throws InterruptedException, IOException {

        System.out.printf("Starting URL: %s, maxPagesCap=%d, delay=%dms%n",
            startingPageUrl, maxPagesCap, throttlingDelay);

        String nextPageUrl = startingPageUrl;
        int pagesCounter = 0;
        List<TorrentData> result = new ArrayList<>();

        while (nextPageUrl != null && (maxPagesCap == -1 || pagesCounter < maxPagesCap)) {

            SearchResult searchResult = search(nextPageUrl);
            System.out.printf("Hit %d, %d retrieved%n", pagesCounter, searchResult.getDataItems().size());
            pagesCounter++;
            nextPageUrl = searchResult.getNextPageUrl();
            result.addAll(searchResult.getDataItems());

            sleep(throttlingDelay);
        }
        System.out.println("Records retrieved: " + result.size());

        return result;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        assert 2 <= args.length && args.length <= 4;

        final String startingUrl = args[0];
        final String outputPath = args[1];
        int maxPagesCount = args.length >= 3 ? Integer.valueOf(args[2]) : -1;
        long delay = args.length == 4 ? Long.valueOf(args[3]) : 20;

        final PravTorRuSearchUtil searchUtil = new PravTorRuSearchUtil();

        List<TorrentData> result = searchUtil.batchSearch(startingUrl, maxPagesCount, delay);
        searchUtil.saveResultsSorted(result, outputPath);
    }

    private SearchResult search(String startingPageUrl) throws IOException {
        Document document = Jsoup
            .connect(startingPageUrl)
            .userAgent(USER_AGENT).get();

        Elements elements = document.select("tr[id^=tr-]");

        List<TorrentData> dataItems = new ArrayList<>();
        for (Element element : elements) {

            TorrentData data = new TorrentData();
            data.setLabel(element.select("div[class=torTopic]").select("a").text());
            data.setLinkUrl(extractLink(element.select("a[class=torTopic]").attr("href")));
            data.setSeedsCount(convertToInteger(element.select("span[title=Seeders]").text()));
            data.setPeersCount(convertToInteger(element.select("span[title=Leechers]").text()));
            data.setSize(element.select("div[title=Скачать .torrent]").select("div[class=small]").text());
            data.setDownloadedCount(convertToInteger(element.select("p[title=Скачан]").text()));

            dataItems.add(data);
        }

        String prevUrl = extractPrevOrNext(document, "Пред.");
        String nextUrl = extractPrevOrNext(document, "След.");
        return new SearchResult(dataItems, prevUrl, nextUrl);
    }

    private String extractLink(String href) {
        return StringUtils.isEmpty(href) ? href : PREFIX + href.substring(2);
    }

    private Integer convertToInteger(String value) {
        if (!StringUtils.isNumeric(value)) {
            return null;
        }
        return Integer.parseInt(value);
    }

    private String extractPrevOrNext(Document document, String value) {
        List<Element> pageItems = document.select("td[class=tRight vBottom nowrap small]")
            .select("a").stream()
            .filter(s -> s.text().contains(value))
            .collect(Collectors.toList());

        return pageItems.isEmpty() ? null : PREFIX + pageItems.get(0).attr("href");
    }

    private String convertTorrentDataToCsvLine(TorrentData data) {
        return String.join("\t", new String[]{
            data.getLabel(),
            String.valueOf(data.getSeedsCount()),
            String.valueOf(data.getPeersCount()),
            String.valueOf(data.getDownloadedCount()),
            data.getSize(),
            data.getLinkUrl()
        });
    }

    public void saveResultsSorted(List<TorrentData> result, String outputFolder) throws IOException {
        Files.write(Paths.get(outputFolder),
            result.stream()
                .filter(item -> item.getDownloadedCount() != null)
                .sorted((o1, o2) -> o2.getDownloadedCount() - o1.getDownloadedCount())
                .map(this::convertTorrentDataToCsvLine)
                .collect(Collectors.toList())/*,
            Charset.forName("UTF-8")*/);
    }
}
