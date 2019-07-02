package by.andd3dfx.iot.service;

import by.andd3dfx.iot.model.Channel;
import java.util.Collection;
import org.springframework.security.access.annotation.Secured;

public interface ChannelService {

  @Secured("ROLE_ADMIN")
  Channel createChannel(Channel channel);

  @Secured({"ROLE_ADMIN", "ROLE_USER"})
  Channel readChannel(Long channelId);

  @Secured({"ROLE_ADMIN", "ROLE_USER"})
  Collection<Channel> readChannels();

  @Secured("ROLE_ADMIN")
  Channel updateChannel(Long channelId, Channel channel);

  @Secured("ROLE_ADMIN")
  void deleteChannel(Long channelId);
}
