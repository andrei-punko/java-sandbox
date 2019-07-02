package by.andd3dfx.iot.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import by.andd3dfx.iot.model.Channel;
import by.andd3dfx.iot.model.DataItem;
import by.andd3dfx.iot.repository.ChannelRepository;
import by.andd3dfx.iot.repository.DataItemRepository;
import by.andd3dfx.iot.service.DataItemService;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;

public class DataItemServiceImplTest {

  private DataItemService service;
  private ChannelRepository channelRepositoryMock = mock(ChannelRepository.class);
  private DataItemRepository dataItemRepositoryMock = mock(DataItemRepository.class);

  @Before
  public void setup() {
    service = new DataItemServiceImpl(channelRepositoryMock, dataItemRepositoryMock);
  }

  @Test
  public void addDataItem() {
    Long channelId = 123L;
    Channel channel = buildChannel(channelId, "Chan name", "Chan desc");
    DataItem dataItem = buildDataItem(324L, channel, new Date(), 34.54);
    when(channelRepositoryMock.findById(channelId)).thenReturn(Optional.of(channel));
    when(dataItemRepositoryMock.save(dataItem)).thenReturn(dataItem);

    DataItem result = service.addDataItem(channelId, dataItem);

    verify(dataItemRepositoryMock).save(dataItem);
    assertThat("Wrong result", result, is(dataItem));
    assertThat("Wrong channel", result.getChannel(), is(channel));
  }

  @Test
  public void readDataItem() {
    Long dataItemId = 1232L;
    Long channelId = 3455L;
    DataItem dataItem = buildDataItem(dataItemId, buildChannel(channelId, "chanName", "chanDesc"), new Date(), 21.23);
    when(dataItemRepositoryMock.findByChannelIdAndId(channelId, dataItemId)).thenReturn(Optional.of(dataItem));

    DataItem result = service.readDataItem(channelId, dataItemId);

    verify(dataItemRepositoryMock).findByChannelIdAndId(channelId, dataItemId);
    assertThat("Wrong result", result, is(dataItem));
  }

  @Test
  public void readDataItems() {
    Long channelId = 123L;
    Channel channel = buildChannel(channelId, "Chan name", "Chan desc");
    DataItem dataItem1 = buildDataItem(12L, channel, new Date(), 12.3);
    DataItem dataItem2 = buildDataItem(13L, channel, new Date(), 45.1);
    when(channelRepositoryMock.findById(channelId)).thenReturn(Optional.of(channel));
    when(dataItemRepositoryMock.findByChannelId(channelId)).thenReturn(Arrays.asList(dataItem1, dataItem2));

    Collection<DataItem> result = service.readDataItems(channelId);

    verify(dataItemRepositoryMock).findByChannelId(channelId);
    assertThat("Wrong size of result list", result, hasSize(2));
    assertThat("Unexpected items", result, hasItems(dataItem1, dataItem2));
  }

  @Test
  public void updateDataItem() {
    Long dataItemId = 123L;
    Long channelId = 3454L;
    DataItem dataItem = buildDataItem(dataItemId, buildChannel(channelId, "chanName", "chanDesc"), new Date(), 21.23);
    DataItem updatedDataItem = buildDataItem(dataItemId, new Channel("New chanName", "New chanDesc"), new Date(), 11.3);
    when(dataItemRepositoryMock.findByChannelIdAndId(channelId, dataItemId)).thenReturn(Optional.of(dataItem));
    when(dataItemRepositoryMock.save(updatedDataItem)).thenReturn(updatedDataItem);

    DataItem result = service.updateDataItem(channelId, dataItemId, updatedDataItem);

    verify(dataItemRepositoryMock).findByChannelIdAndId(channelId, dataItemId);
    verify(dataItemRepositoryMock).save(updatedDataItem);
    assertThat("Wrong result", result, is(updatedDataItem));
  }

  @Test
  public void deleteDataItem() {
    Long dataItemId = 1232L;
    Long channelId = 234L;
    DataItem dataItem = buildDataItem(dataItemId, buildChannel(channelId, "chanName", "chanDesc"), new Date(), 21.23);
    when(dataItemRepositoryMock.findByChannelIdAndId(channelId, dataItemId)).thenReturn(Optional.of(dataItem));

    service.deleteDataItem(channelId, dataItemId);

    verify(dataItemRepositoryMock).findByChannelIdAndId(channelId, dataItemId);
    verify(dataItemRepositoryMock).delete(dataItem);
  }

  private Channel buildChannel(Long channelId, String channelName, String channelDesc) {
    Channel result = new Channel(channelName, channelDesc);
    result.setId(channelId);
    return result;
  }

  private DataItem buildDataItem(Long dataItemId, Channel channel, Date time, Double value) {
    DataItem dataItem = new DataItem(channel, time, value);
    dataItem.setId(dataItemId);
    return dataItem;
  }
}
