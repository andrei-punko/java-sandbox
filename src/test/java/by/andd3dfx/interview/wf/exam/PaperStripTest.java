package by.andd3dfx.interview.wf.exam;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class PaperStripTest {

  @Ignore
  @Test
  public void minPieces() {
    int[] original = new int[]{1, 4, 3, 2};
    int[] desired = new int[]{1, 2, 4, 3};

    assertThat("", PaperStrip.minPieces(original, desired), is(true));
  }
}
