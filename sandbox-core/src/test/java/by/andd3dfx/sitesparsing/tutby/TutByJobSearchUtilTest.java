package by.andd3dfx.sitesparsing.tutby;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.LinkedHashMap;
import org.junit.Before;
import org.junit.Test;

public class TutByJobSearchUtilTest {

    private TutByJobSearchUtil util;

    @Before
    public void setup() {
        util = new TutByJobSearchUtil();
    }

    @Test
    public void search() {
        final SingleSearchResult result = util.singleSearch(util.buildSearchUrl("java"));

        assertThat("Next url should be present", result.getNextPageUrl(), is(
            "http://jobs.tut.by/search/vacancy"
                + "?L_is_autosearch=false&area=1002"
                + "&clusters=true"
                + "&currency_code=BYR"
                + "&enable_snippets=true"
                + "&text=java&page=1"));
        assertThat("Wrong expected size", result.getDataItems(), hasSize(20));

        LinkedHashMap<String, Integer> statisticsSortedMap = util.collectStatistics(result.getDataItems());
        System.out.println(statisticsSortedMap);
    }
}
