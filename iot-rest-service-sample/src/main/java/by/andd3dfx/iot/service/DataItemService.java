package by.andd3dfx.iot.service;

import by.andd3dfx.iot.model.DataItem;
import java.util.Collection;
import org.springframework.security.access.annotation.Secured;

@Secured({"ROLE_ADMIN", "ROLE_USER"})
public interface DataItemService {

  DataItem addDataItem(Long channelId, DataItem dataItem);

  DataItem readDataItem(Long channelId, Long dataItemId);

  Collection<DataItem> readDataItems(Long channelId);

  DataItem updateDataItem(Long channelId, Long dataItemId, DataItem dataItem);

  void deleteDataItem(Long channelId, Long dataItemId);
}
