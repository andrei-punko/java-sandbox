package by.andd3dfx.search;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TutByJobSearchUtil {
    private static final String userAgent = "Firefox";
    private static final String searchUrl = "http://jobs.tut.by/search/vacancy?area=1002&text={}";

    public int search(String keyword) {
        if (keyword == null) {
            return 0;
        }
        keyword.replaceAll(" ", "+");
        try {
            Elements elements = Jsoup
                    .connect(searchUrl.replace("{}", keyword))
                    .userAgent(userAgent).get().select("[data-qa=page-title]");
            if (elements.isEmpty()) {
                return 0;
            }
            String text = elements.get(0).text();
            return Integer.parseInt(text.substring(0, text.indexOf(' ')));
        } catch (IOException e) {
            return 0;
        }
    }
}
