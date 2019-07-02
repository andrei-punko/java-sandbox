package by.andd3dfx.iot.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class Channel {

  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private String description;

  @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
  private Set<DataItem> dataItems = new HashSet<>();

  private Channel() {
  }

  public Channel(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
