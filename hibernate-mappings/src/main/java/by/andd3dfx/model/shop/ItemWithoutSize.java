package by.andd3dfx.model.shop;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("ITEM")
public class ItemWithoutSize extends BaseItem {
}
