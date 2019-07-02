package by.andd3dfx.iot.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import by.andd3dfx.iot.IotApplication;
import by.andd3dfx.iot.model.Channel;
import by.andd3dfx.iot.model.DataItem;
import by.andd3dfx.iot.repository.ChannelRepository;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IotApplication.class)
@WebAppConfiguration
public class ChannelControllerViaMockMvcTest {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
  private MockMvc mockMvc;
  private HttpMessageConverter mappingJackson2HttpMessageConverter;

  private List<Channel> channelsList = new ArrayList<>();

  @Autowired
  private ChannelRepository channelRepository;
  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  void setConverters(HttpMessageConverter<?>[] converters) {
    mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
        .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
        .findAny()
        .orElse(null);

    assertNotNull("the JSON message converter must not be null",
        mappingJackson2HttpMessageConverter);
  }

  @Before
  public void setup() {
    mockMvc = webAppContextSetup(webApplicationContext).apply(springSecurity()).build();

    channelRepository.deleteAll();

    Channel channel = new Channel("Chan name", "Chan desc");
    channel.getDataItems().add(buildDataItem(channel, new Date(), 12.5));
    channel.getDataItems().add(buildDataItem(channel, new Date(), 345.2));
    channelRepository.save(channel);

    Channel anotherChannel = new Channel("Chan 2 name", "Chan 2 desc");
    anotherChannel.getDataItems().add(buildDataItem(anotherChannel, new Date(), 4512.5));
    anotherChannel.getDataItems().add(buildDataItem(anotherChannel, new Date(), 345.1));
    channelRepository.save(anotherChannel);

    Collections.addAll(channelsList, new Channel[]{channel, anotherChannel});
  }

  private DataItem buildDataItem(Channel channel, Date date, double v) {
    return new DataItem(channel, date, v);
  }

  @Test
  public void createChannel() throws Exception {
    Channel channel = new Channel("Name 1", "Desc 1");
    channel.getDataItems().add(new DataItem(channel, new Date(), 435.45));
    channel.getDataItems().add(new DataItem(channel, new Date(), 235.35));

    mockMvc.perform(post("/channels")
        .contentType(contentType)
        .content(json(channel))
        .with(user("admin").password("adminPass").roles("ADMIN"))
    )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", notNullValue()))
        .andExpect(jsonPath("$.name", is(channel.getName())))
        .andExpect(jsonPath("$.description", is(channel.getDescription())))
        .andExpect(jsonPath("$.dataItems", hasSize(2)));

  }

  @Test
  public void readChannel() throws Exception {
    mockMvc.perform(get("/channels/" + channelsList.get(0).getId())
        .contentType(contentType)
        .with(user("admin").password("adminPass").roles("ADMIN"))
    )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(channelsList.get(0).getId().intValue())))
        .andExpect(jsonPath("$.name", is(channelsList.get(0).getName())))
        .andExpect(jsonPath("$.description", is(channelsList.get(0).getDescription())))
        .andExpect(jsonPath("$.dataItems", hasSize(2)));
  }

  @Test
  public void readAbsentChannel() throws Exception {
    mockMvc.perform(get("/channels/9999")
        .contentType(contentType)
        .with(user("admin").password("adminPass").roles("ADMIN"))
    ).andExpect(status().isNotFound());
  }

  @Test
  public void readChannels() throws Exception {
    mockMvc.perform(get("/channels")
        .contentType(contentType)
        .with(user("admin").password("adminPass").roles("ADMIN"))
    )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(channelsList.get(0).getId().intValue())))
        .andExpect(jsonPath("$[1].id", is(channelsList.get(1).getId().intValue())));
  }

  @Test
  public void updateChannel() throws Exception {
    mockMvc.perform(put("/channels/" + channelsList.get(0).getId())
        .contentType(contentType)
        .content(json(new Channel("Changed name", "Changed desc")))
        .with(user("admin").password("adminPass").roles("ADMIN"))
    )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Changed name")))
        .andExpect(jsonPath("$.description", is("Changed desc")));

  }

  @Test
  public void updateAbsentChannel() throws Exception {
    mockMvc.perform(put("/channels/9999")
        .contentType(contentType)
        .content(json(new Channel("Changed name", "Changed desc")))
        .with(user("admin").password("adminPass").roles("ADMIN"))
    ).andExpect(status().isNotFound());
  }

  @Test
  public void deleteChannel() throws Exception {
    mockMvc.perform(delete("/channels/" + channelsList.get(0).getId())
        .contentType(contentType)
        .with(user("admin").password("adminPass").roles("ADMIN"))
    )
        .andExpect(status().isOk());
  }

  @Test
  public void deleteAbsentChannel() throws Exception {
    mockMvc.perform(delete("/channels/9999")
        .contentType(contentType)
        .with(user("admin").password("adminPass").roles("ADMIN"))
    ).andExpect(status().isNotFound());
  }

  protected String json(Object o) throws IOException {
    MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
    mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
    return mockHttpOutputMessage.getBodyAsString();
  }
}
