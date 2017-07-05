package by.andd3dfx.sorting;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class AbstractSortTest {

    private AbstractSort sorterClass;

    @Before
    public void setup() {
        sorterClass = createSorterClass();
    }

    protected abstract AbstractSort createSorterClass();

    @Test
    public void sort() {
        for (long item : new long[]{10, 9, 8, 7, 6, 5, 4, 1, 3, 2}) {
            sorterClass.insert(item);
        }

        sorterClass.sort();

        assertThat("Wrong elements count", sorterClass.getElementsCount(), is(10));
        assertThat("Wrong elements order", sorterClass.getItems(), is(new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }
}
