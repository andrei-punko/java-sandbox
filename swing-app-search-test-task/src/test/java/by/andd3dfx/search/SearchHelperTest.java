package by.andd3dfx.search;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class SearchHelperTest {

    private SearchHelper searchHelper;

    @Before
    public void setUp() {
        searchHelper = new SearchHelper();
    }

    @Test
    public void search() {
        List<SearchResultItem> resultItems = searchHelper.search("Andrei", 10);
        assertThat(resultItems.size(), is(10));
    }
}
