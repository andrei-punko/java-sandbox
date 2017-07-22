package by.andd3dfx.jobsearch;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class JobSearchUtilTest {

    JobSearchUtil util;

    @Before
    public void setup() {
        util = new JobSearchUtil();
    }

    @Test
    public void search() throws Exception {
        int count = util.search("java");

        assertThat("Should be positive", count, greaterThan(200));
    }
}
