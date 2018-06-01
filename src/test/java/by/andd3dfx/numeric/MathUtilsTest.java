package by.andd3dfx.numeric;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MathUtilsTest {

    @Test
    public void reverse() {
        assertThat("Wrong result", MathUtils.reverse(12369), is(96321));
    }

    @Test
    public void binarySearch() {
        assertThat("3 expected", MathUtils.binarySearch(new int[]{1, 2, 3, 4, 5}, 4), is(3));
        assertThat("4 expected", MathUtils.binarySearch(new int[]{1, 2, 3, 4, 5}, 5), is(4));
    }

    @Test
    public void binarySearchWhenNothingFound() {
        assertThat("Wrong result", MathUtils.binarySearch(new int[]{1, 2, 3, 4, 5}, 89), is(-1));
    }
}
