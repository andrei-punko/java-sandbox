package by.andd3dfx.sorting;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BubbleSortTest {

    @Test
    public void sort() {
        BubbleSort bubbleSort = new BubbleSort(10);
        for (long item : new long[]{2, 4, 1, 6, 10, 3}) {
            bubbleSort.insert(item);
        }

        bubbleSort.sort();

        bubbleSort.display();
        assertThat("Wrong elements count", bubbleSort.getElementsCount(), is(6));
        assertThat("Wrong elements order", bubbleSort.getItems(), is(new long[]{1, 2, 3, 4, 6, 10}));
    }
}
