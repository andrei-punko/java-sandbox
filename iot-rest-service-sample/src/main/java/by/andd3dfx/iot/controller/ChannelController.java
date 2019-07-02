package by.andd3dfx.iot.controller;

import by.andd3dfx.iot.model.Channel;
import by.andd3dfx.iot.service.ChannelService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/channels")
public class ChannelController {

  private final ChannelService channelService;

  @Autowired
  public ChannelController(ChannelService channelService) {
    this.channelService = channelService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Channel createChannel(@RequestBody Channel channel) {
    return channelService.createChannel(channel);
  }

  @GetMapping("/{channelId}")
  public Channel readChannel(@PathVariable Long channelId) {
    return channelService.readChannel(channelId);
  }

  @GetMapping
  public Collection<Channel> readChannels() {
    return channelService.readChannels();
  }

  @PutMapping("/{channelId}")
  public Channel updateChannel(@PathVariable Long channelId, @RequestBody Channel channel) {
    return channelService.updateChannel(channelId, channel);
  }

  @DeleteMapping("/{channelId}")
  public void deleteChannel(@PathVariable Long channelId) {
    channelService.deleteChannel(channelId);
  }
}
