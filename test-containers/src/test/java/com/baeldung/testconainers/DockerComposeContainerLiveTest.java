package com.baeldung.testconainers;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.DockerComposeContainer;

public class DockerComposeContainerLiveTest {

    @ClassRule
    public static DockerComposeContainer compose =
            new DockerComposeContainer(getDockerComposeFile())
                    .withExposedService("simpleWebServer", 80);

    private static File getDockerComposeFile() {
        try {
            URL resource = DockerComposeContainerLiveTest.class
                    .getClassLoader()
                    .getResource("test-compose.yml");
            if (resource == null) {
                throw new IllegalStateException("test-compose.yml not found in resources");
            }
            String filePath = URLDecoder.decode(resource.getFile(), StandardCharsets.UTF_8);
            return new File(filePath);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to load test-compose.yml", e);
        }
    }

    @Test
    public void givenSimpleWebServerContainer_whenGetReuqest_thenReturnsResponse()
            throws Exception {
        String address = "http://" + compose.getServiceHost("simpleWebServer", 80)
                + ":" + compose.getServicePort("simpleWebServer", 80);
        String response = simpleGetRequest(address);

        assertEquals(response, "Hello World!");
    }

    private String simpleGetRequest(String address) throws Exception {
        URL url = new URL(address);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        return content.toString();
    }
}
