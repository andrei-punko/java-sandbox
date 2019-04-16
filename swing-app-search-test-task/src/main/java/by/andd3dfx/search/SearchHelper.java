package by.andd3dfx.search;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchHelper {

    private final String SEARCH_URL = "https://www.google.com/search?q=%s&start=%d";
    private final String CHARSET = "UTF-8";
    private final String USER_AGENT = "Mozilla";

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchHelper.class);

    /**
     * For search implementation next link was useful: http://stackoverflow.com/questions/3727662/how-can-you-search-google-programmatically-java-api
     */
    public List<SearchResultItem> search(String searchString, int maxResults) {
        List<SearchResultItem> result = new ArrayList<SearchResultItem>();
        try {
            int offset = 0;
            while (result.size() < maxResults) {
                Elements elements = searchLinks(searchString, offset);
                if (elements.isEmpty()) {
                    break;
                }
                List<SearchResultItem> searchResultItems = batchSearch(elements);
                result.addAll(searchResultItems);
                offset += 10;
            }
            while (result.size() > maxResults) {
                result.remove(result.size() - 1);
            }
        } catch (Exception e) {
            LOGGER.error("Error during search occurs", e);
        }

        return result;
    }

    List<SearchResultItem> batchSearch(Elements elements) throws Exception {
        List<SearchResultItem> result = new ArrayList<SearchResultItem>();
        for (Element link : elements) {
            SearchResultItem searchResultItem = extractSearchResultItemFromLink(link);
            if (searchResultItem != null) {
                result.add(searchResultItem);
            }
        }
        return result;
    }

    Elements searchLinks(String searchString, int offset) throws Exception {
        return Jsoup
            .connect(String.format(SEARCH_URL, URLEncoder.encode(searchString, CHARSET), offset))
            .userAgent(USER_AGENT)
            .get().select(".g>.r>a");
    }

    SearchResultItem extractSearchResultItemFromLink(Element link) throws Exception {
        String title = link.text();

        String absoluteUrl = link
            .absUrl("href");   // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
        String url = URLDecoder
            .decode(absoluteUrl.substring(absoluteUrl.indexOf('=') + 1, absoluteUrl.indexOf('&')), CHARSET);
        if (!url.startsWith("http")) {
            return null;   // Ads/news/etc.
        }

        return new SearchResultItem(url, title);
    }
}
