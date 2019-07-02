package by.andd3dfx.sitesparsing.firstcatalog;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class PriceSearchUtilTest {

    private String FIRST_CATALOG_SEARCH_URL = "https://komp.1k.by/utility-graphicscards/msi/" +
        "MSI_GeForce_GTX_1660_Ti_Gaming_X_1500MHz_PCI_E_30_6144MB_3000MHz_192_bit_HDMI_HDCP_3xDP-3556609.html";

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
