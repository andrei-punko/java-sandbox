package by.andd3dfx.masklogs.logging;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LogMaskingSupportTest {

    @Test
    void shouldMaskKnownSensitivePatterns() {
        String input = """
                payload={"username":"john","password":"secret123"}
                token=abc12345
                Authorization=Bearer qwerty0987
                cardNumber=4111111111111111
                """;

        String masked = LogMaskingSupport.mask(input);

        Assertions.assertFalse(masked.contains("secret123"));
        Assertions.assertFalse(masked.contains("abc12345"));
        Assertions.assertFalse(masked.contains("qwerty0987"));
        Assertions.assertFalse(masked.contains("4111111111111111"));
        Assertions.assertTrue(masked.contains("password\":\"***\""));
        Assertions.assertTrue(masked.contains("token=***"));
    }
}
