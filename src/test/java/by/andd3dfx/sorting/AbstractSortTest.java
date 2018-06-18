package by.andd3dfx.sorting;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class AbstractSortTest {

    protected final int ITEMS_COUNT = 12;
    private final Long TOO_LOW_VALUE = Long.MIN_VALUE + 52;
    private final Long TOO_HIGH_VALUE = Long.MAX_VALUE - 34;
    private AbstractSort sorterClass;

    @Before
    public void setup() {
        sorterClass = createSorterClass();
    }

    protected abstract AbstractSort createSorterClass();

    @Test
    public void sort() {
        for (long item : new long[]{10, TOO_HIGH_VALUE, 9, 8, TOO_LOW_VALUE, 7, 6, 5, 4, 1, 3, 2}) {
            sorterClass.insert(item);
        }

        sorterClass.sort();

        assertThat("Wrong elements count", sorterClass.getElementsCount(), is(ITEMS_COUNT));
        assertThat("Wrong elements order", sorterClass.getItems(), is(new long[]{TOO_LOW_VALUE, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, TOO_HIGH_VALUE}));
    }
}
