package somepackage;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class SomeAnotherIT {

    @Test
    public void someMethod() {
        log.debug("Run integration test...");
    }

    @Test
    public void someAnotherMethod() {
        log.debug("Run integration test...");
    }
}
