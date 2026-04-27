# Mask logs Spring Boot sample

Example of masking sensitive values in Spring Boot logs using custom Logback extensions:

- converter: `MaskingMessageConverter`
- appender: `MaskingConsoleAppender`

Masking is implemented in `LogMaskingSupport` (`password`, `token`, `authorization`, `Bearer ...`, long digit sequences).

## Run

```bash
../mvnw spring-boot:run -pl mask-logs-spring-boot-sample
```

## Try

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"john\",\"password\":\"secret123\",\"cardNumber\":\"4111111111111111\"}"
```

Expected log fragments:

```text
password=***
token=***
Authorization=***
cardNumber=************
```

## Switch converter/appender

`src/main/resources/logback-spring.xml` uses `MASKED_CONVERTER` by default.
To use appender-based masking, switch root appender reference to `MASKED_APPENDER`.

## Tests

```bash
../mvnw -pl mask-logs-spring-boot-sample test
```
