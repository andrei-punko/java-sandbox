package by.andd3dfx.iot.service.impl;

import by.andd3dfx.iot.controller.ResourceNotFoundException;
import by.andd3dfx.iot.model.Channel;
import by.andd3dfx.iot.repository.ChannelRepository;
import by.andd3dfx.iot.service.ChannelService;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ChannelServiceImpl implements ChannelService {

  private final ChannelRepository channelRepository;

  @Autowired
  public ChannelServiceImpl(ChannelRepository channelRepository) {
    this.channelRepository = channelRepository;
  }

  @Override
  public Channel createChannel(Channel channel) {
    return channelRepository.save(channel);
  }

  @Override
  public Channel readChannel(Long channelId) {
    return retrieveChannel(channelId);
  }

  @Override
  public Collection<Channel> readChannels() {
    return channelRepository.findAll();
  }

  @Override
  public Channel updateChannel(Long channelId, Channel channel) {
    retrieveChannel(channelId);
    return channelRepository.save(channel);
  }

  @Override
  public void deleteChannel(Long channelId) {
    Channel channel = retrieveChannel(channelId);
    channelRepository.delete(channel);
  }

  private Channel retrieveChannel(Long channelId) {
    return channelRepository.findById(channelId).orElseThrow(
        () -> new ResourceNotFoundException(String.format("Channel with id=%s was not found", channelId))
    );
  }
}
