package by.andd3dfx.pricesearch;

import org.jsoup.Jsoup;

import java.io.IOException;

public class PriceSearchUtil {
    private static final String USER_AGENT = "Mozilla";

    public Double getLowestFirstCatalogPriceByUrl(String firstCatalogUrl) throws IOException {
        String text = Jsoup
                .connect(firstCatalogUrl)
                .userAgent(USER_AGENT).get().select("[class=pr-price_cash c-green]").text();
        String priceValue = text.replaceAll("\u2014.*", "").replaceAll(" ", "");
        return Double.parseDouble(priceValue.replaceAll(",", "."));
    }
}
