package by.andd3dfx.common;

import by.andd3dfx.common.BrickPyramidUtil;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class BrickPyramidUtilTest {

    @Test
    public void weight() {
        assertThat("w(0,0) = 0.0", BrickPyramidUtil.pressure(0, 0), is(0.0));

        assertThat("w(1,0) = 0.5", BrickPyramidUtil.pressure(1, 0), is(0.5));
        assertThat("w(1,1) = 0.5", BrickPyramidUtil.pressure(1, 1), is(0.5));

        assertThat("w(2,0) = 0.75", BrickPyramidUtil.pressure(2, 0), is(0.75));
        assertThat("w(2,1) = 1.5", BrickPyramidUtil.pressure(2, 1), is(1.5));
        assertThat("w(2,2) = 0.75", BrickPyramidUtil.pressure(2, 2), is(0.75));

        assertThat("w(3,0) = 0.875", BrickPyramidUtil.pressure(3, 0), is(0.875));
        assertThat("w(3,1) = 2.125", BrickPyramidUtil.pressure(3, 1), is(2.125));
        assertThat("w(3,2) = 2.125", BrickPyramidUtil.pressure(3, 2), is(2.125));
        assertThat("w(3,3) = 0.875", BrickPyramidUtil.pressure(3, 3), is(0.875));

        assertThat("w(322,156) = 306.48749781747574", BrickPyramidUtil.pressure(322, 156),
                is(306.48749781747574));
    }

    @Test
    public void weightWithWrongParams() {
        checkWeightWithWrongParams(-1, 1);
        checkWeightWithWrongParams(1, -1);
        checkWeightWithWrongParams(-1, -1);
        checkWeightWithWrongParams(2, 3);
    }

    private void checkWeightWithWrongParams(int row, int pos) {
        try {
            BrickPyramidUtil.pressure(row, pos);
            fail("IllegalArgumentException should be thrown");
        } catch (IllegalArgumentException iae) {
            assertThat("Wrong exception message", iae.getMessage(),
                    is("row and pos should satisfy conditions: row>=0, pos>=0, row>=pos"));
        }
    }
}
