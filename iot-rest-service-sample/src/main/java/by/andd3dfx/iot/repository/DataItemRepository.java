package by.andd3dfx.iot.repository;

import by.andd3dfx.iot.model.DataItem;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataItemRepository extends JpaRepository<DataItem, Long> {

  Collection<DataItem> findByChannelId(Long channelId);

  Optional<DataItem> findByChannelIdAndId(Long channelId, Long dataItemId);
}
