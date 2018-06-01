package by.andd3dfx.search;

import org.jsoup.Jsoup;

import java.io.IOException;

public class PriceSearchUtil {
    private static final String USER_AGENT = "Mozilla";

    public Double getLowestFirstCatalogPriceByUrl(String firstCatalogUrl) throws IOException {
        String text = Jsoup
                .connect(firstCatalogUrl)
                .userAgent(USER_AGENT).get().select("[class=pr-price_cash]").text();
        String priceValue = text.replaceAll("\u2014.*", "").replaceAll(" ", "").replaceAll(",", ".");
        priceValue = priceValue.substring(0, priceValue.indexOf("."));
        return Double.parseDouble(priceValue);
    }
}
