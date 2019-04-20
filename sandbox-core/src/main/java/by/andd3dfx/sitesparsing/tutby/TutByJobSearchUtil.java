package by.andd3dfx.sitesparsing.tutby;

import static java.lang.Thread.sleep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TutByJobSearchUtil {

    private final String URL_PREFIX = "http://jobs.tut.by";
    private final String USER_AGENT = "Mozilla";
    private final String searchUrlFormat = URL_PREFIX + "/search/vacancy?area=1002&text=%s&page=%d";

    public List<VacancyData> batchSearch(String searchString) {
        List<VacancyData> result = new ArrayList<>();

        String nextPageUrl = buildSearchUrl(searchString);
        while (nextPageUrl != null) {
            SingleSearchResult singleSearchResult = singleSearch(nextPageUrl);
            result.addAll(singleSearchResult.getDataItems());
            nextPageUrl = singleSearchResult.getNextPageUrl();
        }

        return result;
    }

    SingleSearchResult singleSearch(String searchUrl) {
        try {
            Document document = Jsoup
                .connect(searchUrl)
                .userAgent(USER_AGENT).get();

            Elements elements = document
                .select("div[class=resume-search-item__name]");

            List<VacancyData> vacancyDataList = new ArrayList<>();
            for (Element element : elements) {
                String vacancyDetailsUrl = element.select("a").attr("href");
                vacancyDataList.add(retrieveVacancyDetails(vacancyDetailsUrl));
            }

            final Elements nextPageItem = document.select("a[data-qa=pager-next]");
            String nextPageUrl = nextPageItem.isEmpty() ? null : URL_PREFIX + nextPageItem.attr("href");
            return new SingleSearchResult(vacancyDataList, nextPageUrl);
        } catch (IOException e) {
            throw new RuntimeException("Single search failed", e);
        }
    }

    private VacancyData retrieveVacancyDetails(String searchUrl) throws IOException {
        System.out.println("Retrieve vacancy details for " + searchUrl);
        Document document = Jsoup
            .connect(searchUrl)
            .userAgent(USER_AGENT).get();

        VacancyData vacancyData = new VacancyData();
        vacancyData.setUrl(document.baseUri());
        vacancyData.setCompanyName(document.select("a[class=vacancy-company-name]").text());
        vacancyData.setTextContent(document.select("div[data-qa=vacancy-description]").text());
        vacancyData.setKeywords(document.select("span[data-qa=skills-element]")
            .stream()
            .map(Element::text)
            .collect(Collectors.toSet())
        );
        vacancyData.setAddressString(document.select("div[class^=vacancy-address-text]").text());
        return vacancyData;
    }

    String buildSearchUrl(String searchString) {
        return String.format(searchUrlFormat, searchString, 0);
    }
}
