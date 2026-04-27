package by.andd3dfx.masklogs.api;

import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> login(@RequestBody AuthRequest request) {
        LOG.info("Received login request: {}", request);

        String token = UUID.randomUUID().toString().replace("-", "");
        LOG.info("Generated token={} for user={}", token, request.username());
        LOG.info("Authorization=Bearer {}", token);

        return Map.of(
                "status", "OK",
                "token", token
        );
    }
}
