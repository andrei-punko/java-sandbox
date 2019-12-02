package by.andd3dfx.model.shop;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("Item")
public class ItemWithoutSize extends AbstractItem {

}
