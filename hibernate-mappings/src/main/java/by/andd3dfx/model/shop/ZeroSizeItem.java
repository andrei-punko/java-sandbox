package by.andd3dfx.model.shop;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("0-size")
public class ZeroSizeItem extends BaseItem {

}
