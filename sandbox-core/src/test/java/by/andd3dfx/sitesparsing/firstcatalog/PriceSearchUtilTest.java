package by.andd3dfx.sitesparsing.firstcatalog;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class PriceSearchUtilTest {

    private String FIRST_CATALOG_SEARCH_URL = "https://komp.1k.by/utility-graphicscards/msi/" +
        "MSI_GeForce_GTX_1050_Ti_1379Mhz_PCI_E_30_4096Mb_7108Mhz_128_bit_DVI_HDMI_HDCP_GAMING_X-2709799.html";

    private PriceSearchUtil priceSearchUtil;

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
