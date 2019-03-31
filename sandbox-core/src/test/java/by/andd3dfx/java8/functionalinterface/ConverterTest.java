package by.andd3dfx.java8.functionalinterface;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConverterTest {

    @Test
    public void convert() throws Exception {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");

        assertThat("Right number expected", converted, is(123));
    }
}