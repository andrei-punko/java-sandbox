package by.andd3dfx.iot;

import by.andd3dfx.iot.model.Channel;
import by.andd3dfx.iot.model.DataItem;
import by.andd3dfx.iot.repository.ChannelRepository;
import java.util.Date;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IotApplication {

  public static void main(String[] args) {
    SpringApplication.run(IotApplication.class, args);
  }

  @Bean
  CommandLineRunner init(ChannelRepository channelRepository) {
    Channel temperatureChannel = new Channel("Temperature", "Outside temperature, ^C");
    temperatureChannel.getDataItems().add(new DataItem(temperatureChannel, new Date(), 20.5));
    temperatureChannel.getDataItems().add(new DataItem(temperatureChannel, new Date(), 22.3));
    channelRepository.save(temperatureChannel);

    Channel windChannel = new Channel("Wind speed", "Wind speed, km/h");
    windChannel.getDataItems().add(new DataItem(windChannel, new Date(), 0.5));
    windChannel.getDataItems().add(new DataItem(windChannel, new Date(), 0.2));
    channelRepository.save(windChannel);

    return (String... args) -> {
    };
  }
}
