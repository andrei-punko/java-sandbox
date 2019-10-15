package by.andd3dfx.interview.goldmansachs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class LongestDictWordsFromCharactersTest {

    @Test
    public void find() {
        assertThat(
            LongestDictWordsFromCharacters.find(
                new char[]{'e', 'o', 't', 's'},
                new String[]{"otse", "tse", "eo", "stoe"}
            ), is(new String[]{"otse", "stoe"})
        );

        assertThat(
            LongestDictWordsFromCharacters.find(
                new char[]{'a', 's', 'd', 'd'},
                new String[]{"asd", "dasd", "sadd", "adsdt", "asdd"}
            ), is(new String[]{"dasd", "sadd", "asdd"})
        );
    }
}
