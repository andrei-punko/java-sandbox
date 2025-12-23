package by.andd3dfx.java8.optional;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class OptionalTest {

    @Test
    public void test() {
        Optional<String> optional = Optional.of("bam");

        assertThat("True expected", optional.isPresent(), is(true));
        assertThat("bam expected", optional.get(), is("bam"));
        assertThat("bam expected", optional.orElse("fallback"), is("bam"));

        // Demonstrating ifPresent - result is "b"
        optional.ifPresent((s) -> log.debug("Character at index 0: {}", s.charAt(0)));
    }
}
