package by.andd3dfx.sitesparsing.onlinerby;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import by.andd3dfx.sitesparsing.onlinerby.dto.CpuSearchResult;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class CpuSearchUtilTest {

    private CpuSearchUtil cpuSearchUtil;

    @Before
    public void setup() {
        cpuSearchUtil = new CpuSearchUtil();
    }

    @Test
    public void getLowestFirstCatalogPriceByUrl() throws IOException {
        CpuSearchResult cpuItems = cpuSearchUtil.extractPage(1);
        System.out.println(cpuItems);

        assertThat(cpuItems.getCpuItems().size(), is(30));
        assertThat(cpuItems.getPagesAmount(), greaterThan(0));
    }
}
