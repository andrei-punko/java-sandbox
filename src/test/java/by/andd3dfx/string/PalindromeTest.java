package by.andd3dfx.string;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import by.andd3dfx.string.Palindrome;
import org.junit.Test;

public class PalindromeTest {

  @Test
  public void isPalindrome() {
    assertThat("ertglgtre is palindrom", Palindrome.isPalindrome("ertglgtre"), is(true));
    assertThat("Anna is palindrom (case insensitive)", Palindrome.isPalindrome("Anna"), is(true));
    assertThat("Dumb is not palindrom", Palindrome.isPalindrome("Dumb"), is(false));
  }
}
