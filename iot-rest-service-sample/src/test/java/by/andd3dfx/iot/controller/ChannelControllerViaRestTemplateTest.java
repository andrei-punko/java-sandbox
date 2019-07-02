package by.andd3dfx.iot.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import by.andd3dfx.iot.model.Channel;
import by.andd3dfx.iot.model.DataItem;
import by.andd3dfx.iot.repository.ChannelRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/*
  Just prototype to show alternative way of creating tests for REST service
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ChannelControllerViaRestTemplateTest {

  private static final String ROOT_URL = "http://localhost:8989";
  private RestTemplate adminRestTemplate = RestTestUtil.buildRestTemplate("admin", "adminPass");
  private RestTemplate userRestTemplate = RestTestUtil.buildRestTemplate("user", "userPass");

  @Autowired
  ChannelRepository channelRepository;
  private List<Channel> channelsList = new ArrayList<>();

  @Before
  public void setup() {
    channelRepository.deleteAll();

    Channel temperatureChannel = new Channel("Temperature", "Outside temperature, ^C");
    temperatureChannel.getDataItems().add(new DataItem(temperatureChannel, new Date(), 20.5));
    channelRepository.save(temperatureChannel);

    Channel windChannel = new Channel("Wind speed", "Wind speed, km/h");
    windChannel.getDataItems().add(new DataItem(windChannel, new Date(), 0.5));
    channelRepository.save(windChannel);

    Collections.addAll(channelsList, new Channel[]{temperatureChannel, windChannel});
  }

  @Test
  public void getAllChannels() {
    getAllChannels(adminRestTemplate);
  }

  @Test
  public void getAllChannelsForUser() {
    getAllChannels(userRestTemplate);
  }

  private void getAllChannels(RestTemplate restTemplate) {
    ResponseEntity<Channel[]> responseEntity = restTemplate
        .getForEntity(ROOT_URL + "/channels", Channel[].class);
    List<Channel> channels = Arrays.asList(responseEntity.getBody());
    assertThat("Unexpected size", channels, hasSize(2));
  }

  @Test
  public void getChannelById() {
    getChannelById(adminRestTemplate);
  }

  @Test
  public void getChannelByIdForUser() {
    getChannelById(userRestTemplate);
  }

  private void getChannelById(RestTemplate restTemplate) {
    Channel expectedChannel = channelsList.get(0);
    Channel channel = restTemplate
        .getForObject(ROOT_URL + "/channels/" + expectedChannel.getId(), Channel.class);
    assertThat("Wrong channel", channel, is(expectedChannel));
  }

  @Test
  public void getChannelByIdForAbsent() {
    getChannelByIdForAbsent(adminRestTemplate);
  }

  @Test
  public void getChannelByIdForAbsentForUser() {
    getChannelByIdForAbsent(userRestTemplate);
  }

  private void getChannelByIdForAbsent(RestTemplate restTemplate) {
    try {
      restTemplate.getForObject(ROOT_URL + "/channels/9999", Channel.class);
      fail("Exception should be thrown");
    } catch (HttpClientErrorException hcee) {
      assertEquals("Wrong statusCode", hcee.getStatusCode(), HttpStatus.NOT_FOUND);
    }
  }

  @Test
  public void createChannel() {
    Channel channel = new Channel("Chan name", "Chan desc");
    channel.getDataItems().add(new DataItem(channel, new Date(), 324.656));
    ResponseEntity<Channel> postResponse = adminRestTemplate
        .postForEntity(ROOT_URL + "/channels", channel, Channel.class);
    assertNotNull("Response should not be null", postResponse);
    assertThat("Id should not be null", postResponse.getBody().getId(), notNullValue());
    assertThat("Wrong name", postResponse.getBody().getName(), is(channel.getName()));
    assertThat("Wrong description", postResponse.getBody().getDescription(),
        is(channel.getDescription()));
    assertThat("Wrong amount of data items", postResponse.getBody().getDataItems(), hasSize(1));
  }

  @Test
  public void createChannelForUser() {
    Channel channel = new Channel("Chan name", "Chan desc");
    channel.getDataItems().add(new DataItem(channel, new Date(), 324.656));
    try {
      userRestTemplate.postForEntity(ROOT_URL + "/channels", channel, Channel.class);
      fail("Exception should be thrown");
    } catch (HttpClientErrorException hcee) {
      assertEquals("Wrong statusCode", hcee.getStatusCode(), HttpStatus.FORBIDDEN);
    }
  }

  @Test
  public void updateChannel() {
    Channel channel = channelsList.get(0);
    channel.setName("Another name");
    channel.setDescription("Another desc");
    adminRestTemplate.put(ROOT_URL + "/channels/" + channel.getId(), channel);

    Optional<Channel> updatedChannel = channelRepository.findById(channel.getId());
    assertNotNull("Updated channel", updatedChannel.get());
    assertThat("Wrong name", updatedChannel.get().getName(), is(channel.getName()));
    assertThat("Wrong desc", updatedChannel.get().getDescription(), is(channel.getDescription()));
  }

  @Test
  public void updateChannelForUser() {
    Channel channel = channelsList.get(0);
    channel.setName("Another name");
    channel.setDescription("Another desc");
    try {
      userRestTemplate.put(ROOT_URL + "/channels/" + channel.getId(), channel);
      fail("Exception should be thrown");
    } catch (HttpClientErrorException hcee) {
      assertEquals("Wrong statusCode", hcee.getStatusCode(), HttpStatus.FORBIDDEN);
    }
  }

  @Test
  public void updateChannelForAbsent() {
    try {
      adminRestTemplate.put(ROOT_URL + "/channels/9999", new Channel(null, null));
      fail("Exception should be thrown");
    } catch (HttpClientErrorException hcee) {
      assertEquals("Wrong statusCode", hcee.getStatusCode(), HttpStatus.NOT_FOUND);
    }
  }

  @Test
  public void deleteChannel() {
    adminRestTemplate.delete(ROOT_URL + "/channels/" + channelsList.get(0).getId());
    try {
      adminRestTemplate
          .getForObject(ROOT_URL + "/channels/" + channelsList.get(0).getId(), Channel.class);
      fail("Exception should be thrown!");
    } catch (HttpClientErrorException hcee) {
      assertEquals("Wrong statusCode", hcee.getStatusCode(), HttpStatus.NOT_FOUND);
    }
  }

  @Test
  public void deleteChannelForUser() {
    try {
      userRestTemplate.delete(ROOT_URL + "/channels/" + channelsList.get(0).getId());
      fail("Exception should be thrown!");
    } catch (HttpClientErrorException hcee) {
      assertEquals("Wrong statusCode", hcee.getStatusCode(), HttpStatus.FORBIDDEN);
    }
  }

  @Test
  public void deleteChannelForAbsent() {
    try {
      adminRestTemplate.delete(ROOT_URL + "/channels/9999");
      fail("Exception should be thrown");
    } catch (HttpClientErrorException hcee) {
      assertEquals("Wrong statusCode", hcee.getStatusCode(), HttpStatus.NOT_FOUND);
    }
  }
}
