package by.andd3dfx.interview.workfusion.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class EntryTest {

  @Test
  public void test() {
    Entry entry = new Entry();
    entry.enter("AB54321");
    entry.enter("UK32032");
    assertThat("First element expected", entry.exit(), is("AB54321"));
    assertThat("Second element expected", entry.exit(), is("UK32032"));
    assertThat("Null expected", entry.exit(), nullValue());
  }
}
