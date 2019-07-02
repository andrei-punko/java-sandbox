package by.andd3dfx.iot.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import by.andd3dfx.iot.model.Channel;
import by.andd3dfx.iot.repository.ChannelRepository;
import by.andd3dfx.iot.service.ChannelService;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;

public class ChannelServiceImplTest {

  private ChannelService service;
  private ChannelRepository channelRepositoryMock = mock(ChannelRepository.class);

  @Before
  public void setup() {
    service = new ChannelServiceImpl(channelRepositoryMock);
  }

  @Test
  public void createChannel() {
    Channel channelToCreate = new Channel("Chan name", "Chan desc");
    Channel createdChannel = buildChannel(123L, "Chan name", "Chan desc");
    when(channelRepositoryMock.save(channelToCreate)).thenReturn(createdChannel);

    Channel result = service.createChannel(channelToCreate);

    verify(channelRepositoryMock).save(channelToCreate);
    assertThat("Wrong result", result, is(createdChannel));
  }

  @Test
  public void readChannel() {
    Long channelId = 123L;
    Channel channel = buildChannel(channelId, "Chan name", "Chan desc");
    when(channelRepositoryMock.findById(channelId)).thenReturn(Optional.of(channel));

    Channel result = service.readChannel(channelId);

    verify(channelRepositoryMock).findById(channelId);
    assertThat("Wrong result", result, is(channel));
  }

  @Test
  public void readChannels() {
    Channel channel1 = new Channel("name 1", "desc 1");
    Channel channel2 = new Channel("name 2", "desc 2");
    when(channelRepositoryMock.findAll()).thenReturn(Arrays.asList(channel1, channel2));

    Collection<Channel> result = service.readChannels();

    verify(channelRepositoryMock).findAll();
    assertThat("Wrong size of result list", result, hasSize(2));
    assertThat("Unexpected items", result, hasItems(channel1, channel2));
  }

  @Test
  public void updateChannel() {
    Long channelId = 123L;
    Channel channel = buildChannel(channelId, "Chan name", "Chan desc");
    Channel updatedChannel = buildChannel(channelId, "Chan another name", "Chan another desc");
    when(channelRepositoryMock.findById(channelId)).thenReturn(Optional.of(channel));
    when(channelRepositoryMock.save(updatedChannel)).thenReturn(updatedChannel);

    Channel result = service.updateChannel(channelId, updatedChannel);

    verify(channelRepositoryMock).findById(channelId);
    verify(channelRepositoryMock).save(updatedChannel);
    assertThat("Wrong result", result, is(updatedChannel));
  }

  @Test
  public void deleteChannel() {
    Long channelId = 123L;
    Channel channel = buildChannel(channelId, "Chan name", "Chan desc");
    when(channelRepositoryMock.findById(channelId)).thenReturn(Optional.of(channel));

    service.deleteChannel(channelId);

    verify(channelRepositoryMock).findById(channelId);
    verify(channelRepositoryMock).delete(channel);
  }

  private Channel buildChannel(Long channelId, String channelName, String channelDesc) {
    Channel result = new Channel(channelName, channelDesc);
    result.setId(channelId);
    return result;
  }
}
