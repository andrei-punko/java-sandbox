package by.andd3dfx.search;

import by.andd3dfx.search.TutByJobSearchUtil;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class TutByJobSearchUtilTest {

    TutByJobSearchUtil util;

    @Before
    public void setup() {
        util = new TutByJobSearchUtil();
    }

    @Test
    public void search() throws Exception {
        int count = util.search("java");

        assertThat("Should be positive", count, greaterThan(200));
    }
}
