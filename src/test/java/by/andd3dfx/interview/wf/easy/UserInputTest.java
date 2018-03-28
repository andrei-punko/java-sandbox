package by.andd3dfx.interview.wf.easy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import by.andd3dfx.interview.wf.easy.UserInput.NumericInput;
import by.andd3dfx.interview.wf.easy.UserInput.TextInput;
import org.junit.Test;

public class UserInputTest {

  @Test
  public void test() {
    TextInput input = new NumericInput();
    input.add('1');
    input.add('a');
    input.add('0');

    assertThat("Unexpected string", input.getValue(), is("10"));
  }
}