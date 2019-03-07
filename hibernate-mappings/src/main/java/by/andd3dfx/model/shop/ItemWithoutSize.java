package by.andd3dfx.model.shop;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Item")
public class ItemWithoutSize extends AbstractItem {

    @Override
    public String toString() {
        return "ItemWithoutSize{" +
            super.toString() +
            "}";
    }
}
