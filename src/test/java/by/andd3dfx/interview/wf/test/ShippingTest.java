package by.andd3dfx.interview.wf.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ShippingTest {

  @Test
  public void minimalNumberOfPackages() {
    assertThat("8 expected", Shipping.minimalNumberOfPackages(16, 2, 10), is(8));
    assertThat("4 expected", Shipping.minimalNumberOfPackages(16, 10, 10), is(4));
    assertThat("16 expected", Shipping.minimalNumberOfPackages(16, 0, 20), is(16));
    assertThat("-1 expected", Shipping.minimalNumberOfPackages(16, 0, 10), is(-1));
  }
}
