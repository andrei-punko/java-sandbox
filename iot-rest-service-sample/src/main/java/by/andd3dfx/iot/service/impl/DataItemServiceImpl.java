package by.andd3dfx.iot.service.impl;

import by.andd3dfx.iot.controller.ResourceNotFoundException;
import by.andd3dfx.iot.model.Channel;
import by.andd3dfx.iot.model.DataItem;
import by.andd3dfx.iot.repository.ChannelRepository;
import by.andd3dfx.iot.repository.DataItemRepository;
import by.andd3dfx.iot.service.DataItemService;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DataItemServiceImpl implements DataItemService {

  private final ChannelRepository channelRepository;
  private final DataItemRepository dataItemRepository;

  @Autowired
  public DataItemServiceImpl(ChannelRepository channelRepository,
      DataItemRepository dataItemRepository) {
    this.channelRepository = channelRepository;
    this.dataItemRepository = dataItemRepository;
  }

  @Override
  public DataItem addDataItem(Long channelId, DataItem dataItem) {
    Channel channel = retrieveChannel(channelId);
    dataItem.setChannel(channel);
    return dataItemRepository.save(dataItem);
  }

  @Override
  public DataItem readDataItem(Long channelId, Long dataItemId) {
    return retrieveDataItem(channelId, dataItemId);
  }

  @Override
  public Collection<DataItem> readDataItems(Long channelId) {
    return dataItemRepository.findByChannelId(channelId);
  }

  @Override
  public DataItem updateDataItem(Long channelId, Long dataItemId, DataItem dataItem) {
    retrieveDataItem(channelId, dataItemId);
    return dataItemRepository.save(dataItem);
  }

  @Override
  public void deleteDataItem(Long channelId, Long dataItemId) {
    DataItem dataItem = retrieveDataItem(channelId, dataItemId);
    dataItemRepository.delete(dataItem);
  }

  private Channel retrieveChannel(Long channelId) {
    return channelRepository.findById(channelId).orElseThrow(
        () -> new ResourceNotFoundException(String.format("Channel with id=%s was not found", channelId))
    );
  }

  private DataItem retrieveDataItem(Long channelId, Long dataItemId) {
    return dataItemRepository.findByChannelIdAndId(channelId, dataItemId).orElseThrow(
        () -> new ResourceNotFoundException(String.format("DataItem with id=%s was not found", dataItemId))
    );
  }
}
