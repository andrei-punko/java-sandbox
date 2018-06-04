package by.andd3dfx.numeric;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MathUtilsTest {

    @Test
    public void reverse() {
        assertThat("Wrong result", MathUtils.reverse(12369), is(96321));
    }
}
