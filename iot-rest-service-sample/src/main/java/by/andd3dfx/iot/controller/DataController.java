package by.andd3dfx.iot.controller;

import by.andd3dfx.iot.model.DataItem;
import by.andd3dfx.iot.service.DataItemService;
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
@RequestMapping("/channels/{channelId}/data")
public class DataController {

  private final DataItemService dataItemService;

  @Autowired
  public DataController(DataItemService dataItemService) {
    this.dataItemService = dataItemService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public DataItem addDataItem(@PathVariable Long channelId, @RequestBody DataItem dataItem) {
    return dataItemService.addDataItem(channelId, dataItem);
  }

  @GetMapping("/{dataItemId}")
  public DataItem readDataItem(@PathVariable Long channelId, @PathVariable Long dataItemId) {
    return dataItemService.readDataItem(channelId, dataItemId);
  }

  @GetMapping
  public Collection<DataItem> readChannelDataItems(@PathVariable Long channelId) {
    return dataItemService.readDataItems(channelId);
  }

  @PutMapping("/{dataItemId}")
  public DataItem updateDataItem(@PathVariable Long channelId, @PathVariable Long dataItemId,
      @RequestBody DataItem dataItem) {
    return dataItemService.updateDataItem(channelId, dataItemId, dataItem);
  }

  @DeleteMapping("/{dataItemId}")
  public void deleteDataItem(@PathVariable Long channelId, @PathVariable Long dataItemId) {
    dataItemService.deleteDataItem(channelId, dataItemId);
  }
}
