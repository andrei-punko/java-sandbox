package by.andd3dfx.jobsearch;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JobSearchUtil {
    private static final String userAgent = "Mozilla";
    private static final String searchUrl = "http://jobs.tut.by/search/vacancy?area=1002&text={}";

    public int search(String keyword) {
        if (keyword == null) {
            return 0;
        }
        keyword.replaceAll(" ", "+");
        try {
            Elements elements = Jsoup
                    .connect(searchUrl.replace("{}", keyword))
                    .userAgent(userAgent).get().select(".search-result-counter");
            if (elements.isEmpty()) {
                return 0;
            }
            String text = elements.get(0).text();
            return Integer.parseInt(text.substring(text.indexOf(" ") + 1, text.lastIndexOf(" ")));
        } catch (IOException e) {
            return 0;
        }
    }
}
