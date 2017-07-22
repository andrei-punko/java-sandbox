package by.andd3dfx.pricesearch;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class PriceSearchUtilTest {
    String FIRST_CATALOG_SEARCH_URL = "https://komp.1k.by/utility-graphicscards/msi/" +
            "MSI_GeForce_GTX_1080_1632Mhz_PCI_E_30_8192Mb_10010Mhz_256_bit_DVI_HDMI_HDCP_GAMING-2575073.html";

    PriceSearchUtil priceSearchUtil;

    @Before
    public void setup() {
        priceSearchUtil = new PriceSearchUtil();
    }

    @Test
    public void testGetLowestFirstCatalogPriceByUrl() throws Exception {
        Double price = priceSearchUtil.getLowestFirstCatalogPriceByUrl(FIRST_CATALOG_SEARCH_URL);
        System.out.println("1k: " + price);
        assertThat("Price should be greater than 0", price, Matchers.greaterThan(0.0));
    }
}
