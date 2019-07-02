package by.andd3dfx.iot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "channel")
@EqualsAndHashCode(exclude = "channel")
@Entity
public class DataItem {

  @Id
  @GeneratedValue
  private Long id;

  private Date time;
  private Double value;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "channel_id")
  private Channel channel;

  private DataItem() {
  }

  public DataItem(Channel channel, Date time, Double value) {
    this.channel = channel;
    this.time = time;
    this.value = value;
  }
}
