package by.andd3dfx.interview.ya;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class UrlShortenerTest {

    @Test
    public void testEncodeNDecodeStrings() {
        assertThat(UrlShortener.buildShortUrl("tut.by"), is("b"));
        assertThat(UrlShortener.buildShortUrl("dev.by"), is("c"));
        assertThat(UrlShortener.buildShortUrl("thg.ru"), is("d"));

        assertThat(UrlShortener.restoreLongUrl("b"), is("tut.by"));
        assertThat(UrlShortener.restoreLongUrl("c"), is("dev.by"));
        assertThat(UrlShortener.restoreLongUrl("d"), is("thg.ru"));
    }

    @Test
    public void encodePrimaryKeyToShortString() {
        assertThat("Wrong short string for PK=1", UrlShortener.encodePrimaryKeyToShortString(1L), is("b"));
        assertThat("Wrong short string for PK=100", UrlShortener.encodePrimaryKeyToShortString(100L), is("bM"));
        assertThat("Wrong short string for PK=1000", UrlShortener.encodePrimaryKeyToShortString(1000L), is("qi"));
    }

    @Test
    public void decodeShortStringToPrimaryKey() {
        assertThat("Wrong PK for shortString=b", UrlShortener.decodeShortStringToPrimaryKey("b"), is(1L));
        assertThat("Wrong PK for shortString=bM", UrlShortener.decodeShortStringToPrimaryKey("bM"), is(100L));
        assertThat("Wrong PK for shortString=qi", UrlShortener.decodeShortStringToPrimaryKey("qi"), is(1000L));
    }
}
