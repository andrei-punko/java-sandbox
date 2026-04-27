package by.andd3dfx.masklogs.api;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerLogMaskingIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldMaskSensitiveValuesInLogs(CapturedOutput output) {
        Map<String, String> body = Map.of(
                "username", "john",
                "password", "secret123",
                "cardNumber", "4111111111111111"
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity("/api/auth/login", request, Map.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        String logs = output.getOut();
        Assertions.assertFalse(logs.contains("secret123"));
        Assertions.assertFalse(logs.contains("4111111111111111"));
        Assertions.assertFalse(logs.contains("Bearer "));
        Assertions.assertTrue(logs.contains("password=***") || logs.contains("password\":\"***\""));
        Assertions.assertTrue(logs.contains("************"));
    }
}
