package by.andd3dfx.interview.epam;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MathTest {

    @Test
    public void reverse() {
        assertThat("Wrong result", Math.reverse(12369), is(96321));
    }

    @Test
    public void binarySearch() {
        assertThat("3 expected", Math.binarySearch(new int[]{1, 2, 3, 4, 5}, 4), is(3));
        assertThat("4 expected", Math.binarySearch(new int[]{1, 2, 3, 4, 5}, 5), is(4));
    }

    @Test
    public void binarySearchWhenNothingFound() {
        assertThat("Wrong result", Math.binarySearch(new int[]{1, 2, 3, 4, 5}, 89), is(-1));
    }
}
