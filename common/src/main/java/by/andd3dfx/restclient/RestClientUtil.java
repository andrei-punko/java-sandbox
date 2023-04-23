package by.andd3dfx.restclient;

import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.CredentialsStore;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.util.Timeout;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RestClientUtil {

    public static RestTemplate buildRestTemplate(String username, String password, int timeout) {
        RestTemplate restTemplate = new RestTemplate(
            RestClientUtil.buildClientHttpRequestFactory(username, password, timeout)
        );

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>() {{
            add(new MappingJackson2HttpMessageConverter());
        }};
        restTemplate.setMessageConverters(messageConverters);

        return restTemplate;
    }

    private static HttpComponentsClientHttpRequestFactory buildClientHttpRequestFactory(
        final String username, final String password, final int timeout) {

        return new HttpComponentsClientHttpRequestFactory() {{
            setHttpClient(buildHttpClient(username, password, timeout));
            setConnectionRequestTimeout(timeout);
            setConnectTimeout(timeout);
        }};
    }

    private static HttpClient buildHttpClient(String username, String password, int timeout) {
        CredentialsStore credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope(null, -1),
                new UsernamePasswordCredentials(username, password.toCharArray()));

        try {
            SSLConnectionSocketFactory sslConnectionSocketFactory = buildSSLConnectionSocketFactory();

            SocketConfig socketConfig = SocketConfig.custom()
                    .setSoTimeout(Timeout.of(timeout, TimeUnit.MILLISECONDS))
                    .build();
            HttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                    .setSSLSocketFactory(sslConnectionSocketFactory)
                    .setDefaultSocketConfig(socketConfig)
                    .setMaxConnTotal(200)
                    .setMaxConnPerRoute(40)
                    .build();
            return HttpClientBuilder
                .create()
                .setConnectionManager(connectionManager)
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();
        } catch (Exception e) {
            throw new RuntimeException("Problems with https connection", e);
        }
    }

    private static SSLConnectionSocketFactory buildSSLConnectionSocketFactory() {
        return new SSLConnectionSocketFactory(
            new TLSSocketConnectionFactory(),
            new String[]{"TLSv1.2"},
            null,
                null
        );
    }
}
