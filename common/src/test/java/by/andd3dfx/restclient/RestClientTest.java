package by.andd3dfx.restclient;

import by.andd3dfx.restclient.dto.MoneyAmount;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RestClientTest {
    private final String URL = "http://localhost:8080/any-path";

    @InjectMocks
    private RestClient restClient;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void getForObjectWithoutQueryParamsNHeaders() {
        final String expectedUrl = buildExpectedUrl(URL, new HashMap<>());
        final MoneyAmount stubResult = new MoneyAmount(54.8, "BYN");
        final ResponseEntity<MoneyAmount> responseEntity = ResponseEntity.ok(stubResult);
        Mockito.when(restTemplate.exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class), ArgumentMatchers.eq(MoneyAmount.class)))
                .thenReturn(responseEntity);

        MoneyAmount result = restClient.getForObject(URL, MoneyAmount.class);

        assertThat(result.getAmount(), is(stubResult.getAmount()));
        assertThat(result.getCurrency(), is(stubResult.getCurrency()));

        ArgumentCaptor<HttpEntity> httpEntityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        Mockito.verify(restTemplate).exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.GET), httpEntityCaptor.capture(), ArgumentMatchers.eq(MoneyAmount.class));

        HttpEntity capturedHttpEntity = httpEntityCaptor.getValue();
        MatcherAssert.assertThat(capturedHttpEntity.getBody(), nullValue());
    }

    @Test
    public void getForObjectWithoutQueryParams() {
        final Map<String, String> headers = buildHeaders();
        final String expectedUrl = buildExpectedUrl(URL, new HashMap<>());
        final MoneyAmount stubResult = new MoneyAmount(54.8, "BYN");
        final ResponseEntity<MoneyAmount> responseEntity = ResponseEntity.ok(stubResult);
        Mockito.when(restTemplate.exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class), ArgumentMatchers.eq(MoneyAmount.class)))
                .thenReturn(responseEntity);

        MoneyAmount result = restClient.getForObject(URL, headers, MoneyAmount.class);

        assertThat(result.getAmount(), is(stubResult.getAmount()));
        assertThat(result.getCurrency(), is(stubResult.getCurrency()));

        ArgumentCaptor<HttpEntity> httpEntityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        Mockito.verify(restTemplate).exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.GET), httpEntityCaptor.capture(), ArgumentMatchers.eq(MoneyAmount.class));

        HttpEntity capturedHttpEntity = httpEntityCaptor.getValue();
        HttpHeaders capturedHeaders = capturedHttpEntity.getHeaders();
        MatcherAssert.assertThat(capturedHeaders.getAccept(), is(Arrays.asList(MediaType.APPLICATION_JSON)));
        headers.forEach((key, value) -> {
            MatcherAssert.assertThat(capturedHeaders.get(key), is(Arrays.asList(value)));
        });
        MatcherAssert.assertThat(capturedHttpEntity.getBody(), nullValue());
    }

    @Test
    public void getForObject() {
        final Map<String, String> queryParams = buildQueryParams();
        final Map<String, String> headers = buildHeaders();

        final String expectedUrl = buildExpectedUrl(URL, queryParams);
        final MoneyAmount stubResult = new MoneyAmount(54.8, "BYN");
        final ResponseEntity<MoneyAmount> responseEntity = ResponseEntity.ok(stubResult);
        Mockito.when(restTemplate.exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class), ArgumentMatchers.eq(MoneyAmount.class)))
                .thenReturn(responseEntity);

        MoneyAmount result = restClient.getForObject(URL, headers, queryParams, MoneyAmount.class);

        assertThat(result.getAmount(), is(stubResult.getAmount()));
        assertThat(result.getCurrency(), is(stubResult.getCurrency()));

        ArgumentCaptor<HttpEntity> httpEntityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        Mockito.verify(restTemplate).exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.GET), httpEntityCaptor.capture(), ArgumentMatchers.eq(MoneyAmount.class));

        HttpEntity capturedHttpEntity = httpEntityCaptor.getValue();
        HttpHeaders capturedHeaders = capturedHttpEntity.getHeaders();
        MatcherAssert.assertThat(capturedHeaders.getAccept(), is(Arrays.asList(MediaType.APPLICATION_JSON)));
        headers.forEach((key, value) -> {
            MatcherAssert.assertThat(capturedHeaders.get(key), is(Arrays.asList(value)));
        });
        MatcherAssert.assertThat(capturedHttpEntity.getBody(), nullValue());
    }

    @Test
    public void postForObjectWithoutQueryParamsNHeaders() {
        final String expectedUrl = buildExpectedUrl(URL, new HashMap<>());
        final MoneyAmount stubResult = new MoneyAmount(54.8, "BYN");
        final ResponseEntity<MoneyAmount> responseEntity = ResponseEntity.ok(stubResult);
        final String bodyJsonString = "{ \"name\": \"Axton\" }";
        Mockito.when(restTemplate.exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.POST), ArgumentMatchers.any(HttpEntity.class), ArgumentMatchers.eq(MoneyAmount.class)))
                .thenReturn(responseEntity);

        MoneyAmount result = restClient.postForObject(URL, bodyJsonString, MoneyAmount.class);

        assertThat(result.getAmount(), is(stubResult.getAmount()));
        assertThat(result.getCurrency(), is(stubResult.getCurrency()));

        ArgumentCaptor<HttpEntity> httpEntityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        Mockito.verify(restTemplate).exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.POST), httpEntityCaptor.capture(), ArgumentMatchers.eq(MoneyAmount.class));

        HttpEntity capturedHttpEntity = httpEntityCaptor.getValue();
        HttpHeaders capturedHeaders = capturedHttpEntity.getHeaders();
        MatcherAssert.assertThat(capturedHeaders.getAccept(), is(Arrays.asList(MediaType.APPLICATION_JSON)));
        MatcherAssert.assertThat(capturedHeaders.getContentType(), CoreMatchers.is(MediaType.APPLICATION_JSON));
        MatcherAssert.assertThat(capturedHttpEntity.getBody(), is(bodyJsonString));
    }

    @Test
    public void postForObjectWithoutQueryParams() {
        final Map<String, String> headers = buildHeaders();
        final String expectedUrl = buildExpectedUrl(URL, new HashMap<>());
        final MoneyAmount stubResult = new MoneyAmount(54.8, "BYN");
        final ResponseEntity<MoneyAmount> responseEntity = ResponseEntity.ok(stubResult);
        final String bodyJsonString = "{ \"name\": \"Axton\" }";
        Mockito.when(restTemplate.exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.POST), ArgumentMatchers.any(HttpEntity.class), ArgumentMatchers.eq(MoneyAmount.class)))
                .thenReturn(responseEntity);

        MoneyAmount result = restClient.postForObject(URL, headers, bodyJsonString, MoneyAmount.class);

        assertThat(result.getAmount(), is(stubResult.getAmount()));
        assertThat(result.getCurrency(), is(stubResult.getCurrency()));

        ArgumentCaptor<HttpEntity> httpEntityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        Mockito.verify(restTemplate).exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.POST), httpEntityCaptor.capture(), ArgumentMatchers.eq(MoneyAmount.class));

        HttpEntity capturedHttpEntity = httpEntityCaptor.getValue();
        HttpHeaders capturedHeaders = capturedHttpEntity.getHeaders();
        MatcherAssert.assertThat(capturedHeaders.getAccept(), is(Arrays.asList(MediaType.APPLICATION_JSON)));
        MatcherAssert.assertThat(capturedHeaders.getContentType(), CoreMatchers.is(MediaType.APPLICATION_JSON));
        headers.forEach((key, value) -> {
            MatcherAssert.assertThat(capturedHeaders.get(key), is(Arrays.asList(value)));
        });
        MatcherAssert.assertThat(capturedHttpEntity.getBody(), is(bodyJsonString));
    }

    @Test
    public void postForObjectWithQueryParams() {
        final Map<String, String> queryParams = buildQueryParams();
        final Map<String, String> headers = buildHeaders();
        final String expectedUrl = buildExpectedUrl(URL, queryParams);
        final MoneyAmount stubResult = new MoneyAmount(54.8, "BYN");
        final ResponseEntity<MoneyAmount> responseEntity = ResponseEntity.ok(stubResult);
        final String bodyJsonString = "{ \"name\": \"Axton\" }";
        Mockito.when(restTemplate.exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.POST), ArgumentMatchers.any(HttpEntity.class), ArgumentMatchers.eq(MoneyAmount.class)))
                .thenReturn(responseEntity);

        MoneyAmount result = restClient.postForObject(URL, headers, queryParams, bodyJsonString, MoneyAmount.class);

        assertThat(result.getAmount(), is(stubResult.getAmount()));
        assertThat(result.getCurrency(), is(stubResult.getCurrency()));

        ArgumentCaptor<HttpEntity> httpEntityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        Mockito.verify(restTemplate).exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.POST), httpEntityCaptor.capture(), ArgumentMatchers.eq(MoneyAmount.class));

        HttpEntity capturedHttpEntity = httpEntityCaptor.getValue();
        HttpHeaders capturedHeaders = capturedHttpEntity.getHeaders();
        MatcherAssert.assertThat(capturedHeaders.getAccept(), is(Arrays.asList(MediaType.APPLICATION_JSON)));
        MatcherAssert.assertThat(capturedHeaders.getContentType(), CoreMatchers.is(MediaType.APPLICATION_JSON));
        headers.forEach((key, value) -> {
            MatcherAssert.assertThat(capturedHeaders.get(key), is(Arrays.asList(value)));
        });
        MatcherAssert.assertThat(capturedHttpEntity.getBody(), is(bodyJsonString));
    }

    @Test
    public void putForObjectWithoutQueryParams() {
        final Map<String, String> headers = buildHeaders();
        final String expectedUrl = buildExpectedUrl(URL, new HashMap<>());
        final MoneyAmount stubResult = new MoneyAmount(54.8, "BYN");
        final ResponseEntity<MoneyAmount> responseEntity = ResponseEntity.ok(stubResult);
        Mockito.when(restTemplate.exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.PUT), ArgumentMatchers.any(HttpEntity.class), ArgumentMatchers.eq(MoneyAmount.class)))
                .thenReturn(responseEntity);

        MoneyAmount result = restClient.putForObject(URL, headers, MoneyAmount.class);

        assertThat(result.getAmount(), is(stubResult.getAmount()));
        assertThat(result.getCurrency(), is(stubResult.getCurrency()));

        ArgumentCaptor<HttpEntity> httpEntityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        Mockito.verify(restTemplate).exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.PUT), httpEntityCaptor.capture(), ArgumentMatchers.eq(MoneyAmount.class));

        HttpEntity capturedHttpEntity = httpEntityCaptor.getValue();
        HttpHeaders capturedHeaders = capturedHttpEntity.getHeaders();
        MatcherAssert.assertThat(capturedHeaders.getAccept(), is(Arrays.asList(MediaType.APPLICATION_JSON)));
        MatcherAssert.assertThat(capturedHeaders.getContentType(), CoreMatchers.is(MediaType.APPLICATION_JSON));
        headers.forEach((key, value) -> {
            MatcherAssert.assertThat(capturedHeaders.get(key), is(Arrays.asList(value)));
        });
    }

    @Test
    public void putForObject() {
        final Map<String, String> queryParams = buildQueryParams();
        final Map<String, String> headers = buildHeaders();
        final String expectedUrl = buildExpectedUrl(URL, queryParams);
        final MoneyAmount stubResult = new MoneyAmount(54.8, "BYN");
        final ResponseEntity<MoneyAmount> responseEntity = ResponseEntity.ok(stubResult);
        Mockito.when(restTemplate.exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.PUT), ArgumentMatchers.any(HttpEntity.class), ArgumentMatchers.eq(MoneyAmount.class)))
                .thenReturn(responseEntity);

        MoneyAmount result = restClient.putForObject(URL, headers, queryParams, MoneyAmount.class);

        assertThat(result.getAmount(), is(stubResult.getAmount()));
        assertThat(result.getCurrency(), is(stubResult.getCurrency()));

        ArgumentCaptor<HttpEntity> httpEntityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        Mockito.verify(restTemplate).exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.PUT), httpEntityCaptor.capture(), ArgumentMatchers.eq(MoneyAmount.class));

        HttpEntity capturedHttpEntity = httpEntityCaptor.getValue();
        HttpHeaders capturedHeaders = capturedHttpEntity.getHeaders();
        MatcherAssert.assertThat(capturedHeaders.getAccept(), is(Arrays.asList(MediaType.APPLICATION_JSON)));
        MatcherAssert.assertThat(capturedHeaders.getContentType(), CoreMatchers.is(MediaType.APPLICATION_JSON));
        headers.forEach((key, value) -> {
            MatcherAssert.assertThat(capturedHeaders.get(key), is(Arrays.asList(value)));
        });
    }

    @Test
    public void deleteForObject() {
        final Map<String, String> headers = buildHeaders();
        final String expectedUrl = buildExpectedUrl(URL, new HashMap<>());
        final MoneyAmount stubResult = new MoneyAmount(54.8, "BYN");
        final ResponseEntity<MoneyAmount> responseEntity = ResponseEntity.ok(stubResult);
        Mockito.when(restTemplate.exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.DELETE), ArgumentMatchers.any(HttpEntity.class), ArgumentMatchers.eq(MoneyAmount.class)))
                .thenReturn(responseEntity);

        MoneyAmount result = restClient.deleteForObject(URL, headers, MoneyAmount.class);

        assertThat(result.getAmount(), is(stubResult.getAmount()));
        assertThat(result.getCurrency(), is(stubResult.getCurrency()));

        ArgumentCaptor<HttpEntity> httpEntityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        Mockito.verify(restTemplate).exchange(ArgumentMatchers.eq(expectedUrl), ArgumentMatchers.eq(HttpMethod.DELETE), httpEntityCaptor.capture(), ArgumentMatchers.eq(MoneyAmount.class));

        HttpEntity capturedHttpEntity = httpEntityCaptor.getValue();
        HttpHeaders capturedHeaders = capturedHttpEntity.getHeaders();
        MatcherAssert.assertThat(capturedHeaders.getAccept(), is(Arrays.asList(MediaType.APPLICATION_JSON)));
        MatcherAssert.assertThat(capturedHeaders.getContentType(), CoreMatchers.is(MediaType.APPLICATION_JSON));
        headers.forEach((key, value) -> {
            MatcherAssert.assertThat(capturedHeaders.get(key), is(Arrays.asList(value)));
        });
    }

    private String buildExpectedUrl(String url, Map<String, String> queryParams) {
        if (queryParams.isEmpty()) {
            return url;
        }

        String params = queryParams.keySet().stream()
                .map(key -> key + "=" + queryParams.get(key))
                .collect(Collectors.joining("&"));
        return url + "?" + params;
    }

    private Map<String, String> buildHeaders() {
        return Map.of("Key1", "Value1");
    }

    private Map<String, String> buildQueryParams() {
        return Map.of("paramKey1", "paramValue1");
    }
}
