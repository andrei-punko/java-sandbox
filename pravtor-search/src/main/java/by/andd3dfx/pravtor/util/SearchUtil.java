package by.andd3dfx.pravtor.util;

import static java.lang.Thread.sleep;

import by.andd3dfx.pravtor.model.BatchSearchResult;
import by.andd3dfx.pravtor.model.SearchCriteria;
import by.andd3dfx.pravtor.model.SingleSearchResult;
import by.andd3dfx.pravtor.model.TorrentData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Util to perform singleSearch on http://pravtor.ru torrent tracker
 */
public class SearchUtil {

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

            SingleSearchResult singleSearchResult = singleSearch(nextPageUrl);
            System.out.printf("Hit %d, %d retrieved%n", pagesCounter, singleSearchResult.getDataItems().size());
            pagesCounter++;
            nextPageUrl = singleSearchResult.getNextPageUrl();
            result.addAll(singleSearchResult.getDataItems());

            sleep(throttlingDelay);
        }
        System.out.println("Records retrieved: " + result.size());

        return result;
    }

    SingleSearchResult singleSearch(String startingPageUrl) throws IOException {
        Document document = Jsoup
            .connect(startingPageUrl)
            .userAgent(USER_AGENT).get();

        Elements elements = document.select("tr[id^=tr-]");

        List<TorrentData> dataItems =
            elements.stream().map(element ->
                new TorrentData() {{
                    setLabel(element.select("div[class=torTopic]").select("a").text());
                    setLinkUrl(extractLink(element.select("a[class=torTopic]").attr("href")));
                    setSeedsCount(convertToInteger(element.select("span[title=Seeders]").text()));
                    setPeersCount(convertToInteger(element.select("span[title=Leechers]").text()));
                    setSize(element.select("div[title=Скачать .torrent]").select("div[class=small]").text());
                    setDownloadedCount(convertToInteger(element.select("p[title=Скачан]").text()));
                }}
            ).collect(Collectors.toList());

        String prevUrl = extractPrevOrNext(document, "Пред.");
        String nextUrl = extractPrevOrNext(document, "След.");
        return new SingleSearchResult(dataItems, prevUrl, nextUrl);
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

    public static void main(String[] args) throws IOException, InterruptedException {
        assert args.length == 2;
        String paramsFileName = args[0];
        String excelFileName = args[1];

        final SearchUtil searchUtil = new SearchUtil();
        final FileUtil fileUtil = new FileUtil();

        List<BatchSearchResult> searchItems = new ArrayList<>();
        for (SearchCriteria searchCriteria : fileUtil.loadSearchCriteria(paramsFileName)) {
            String startingUrl = searchCriteria.getUrl();
            String label = searchCriteria.getTopic();

            List<TorrentData> result = searchUtil.batchSearch(startingUrl, -1, 20)
                .stream()
                .filter(torrentData -> torrentData.getDownloadedCount() != null)
                .sorted(Comparator.comparingInt(TorrentData::getDownloadedCount).reversed())
                .collect(Collectors.toList());
            searchItems.add(new BatchSearchResult(label, result));
        }

        fileUtil.writeIntoExcel(excelFileName, searchItems);
    }
}
