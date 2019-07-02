package by.andd3dfx.iot.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import by.andd3dfx.iot.model.Channel;
import by.andd3dfx.iot.model.DataItem;
import by.andd3dfx.iot.repository.ChannelRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DataControllerTest {

    private static final String ROOT_URL = "http://localhost:8989";
    private RestTemplate adminRestTemplate = RestTestUtil.buildRestTemplate("admin", "adminPass");
    private RestTemplate userRestTemplate = RestTestUtil.buildRestTemplate("user", "userPass");

    @Autowired
    ChannelRepository channelRepository;
    private List<Channel> channelsList = new ArrayList<>();
    private List<DataItem> dataItemsList = new ArrayList<>();

    @Before
    public void setup() {
        channelRepository.deleteAll();

        Channel temperatureChannel = new Channel("Temperature", "Outside temperature, ^C");
        DataItem temperatureDataItem = new DataItem(temperatureChannel, new Date(), 20.5);
        temperatureChannel.getDataItems().add(temperatureDataItem);
        DataItem temperatureDataItem2 = new DataItem(temperatureChannel, new Date(), 21.5);
        temperatureChannel.getDataItems().add(temperatureDataItem2);
        channelRepository.save(temperatureChannel);

        Channel windChannel = new Channel("Wind speed", "Wind speed, km/h");
        DataItem windDataItem = new DataItem(windChannel, new Date(), 0.5);
        windChannel.getDataItems().add(windDataItem);
        channelRepository.save(windChannel);

        Collections.addAll(channelsList, new Channel[]{temperatureChannel, windChannel});
        Collections.addAll(dataItemsList, new DataItem[]{temperatureDataItem, temperatureDataItem2, windDataItem});
    }

    /**
     * Generally all empty test should have the same logic as ChannelControllerViaRestTemplateTest already has
     */
    @Test
    public void addDataItem() {
    }

    @Test
    public void readDataItem() {
        readDataItem(adminRestTemplate);
    }

    @Test
    public void readDataItemForUser() {
        readDataItem(userRestTemplate);
    }

    private void readDataItem(RestTemplate restTemplate) {
        Channel channel = channelsList.get(0);
        DataItem dataItem = channel.getDataItems().iterator().next();
        String url = String.format("%s/channels/%d/data/%d", ROOT_URL, channel.getId(), dataItem.getId());

        DataItem result = restTemplate.getForObject(url, DataItem.class);

        assertThat("Wrong dataItem", result, is(dataItem));
    }

    @Test
    public void readChannelDataItems() {
        readChannelDataItems(adminRestTemplate);
    }

    @Test
    public void readChannelDataItemsForUser() {
        readChannelDataItems(userRestTemplate);
    }

    private void readChannelDataItems(RestTemplate restTemplate) {
        Channel channel = channelsList.get(0);

        DataItem[] result = restTemplate
            .getForObject(String.format("%s/channels/%d/data", ROOT_URL, channel.getId()), DataItem[].class);

        assertThat("Unexpected size", result.length, is(2));
    }

    @Test
    public void updateDataItem() {
    }

    @Test
    public void deleteDataItem() {
    }
}
