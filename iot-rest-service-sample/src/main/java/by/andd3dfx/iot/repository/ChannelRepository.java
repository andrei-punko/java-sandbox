package by.andd3dfx.iot.repository;

import by.andd3dfx.iot.model.Channel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

  Optional<Channel> findByName(String name);
}
