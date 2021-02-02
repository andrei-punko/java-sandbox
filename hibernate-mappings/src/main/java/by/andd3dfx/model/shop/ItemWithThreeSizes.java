package by.andd3dfx.model.shop;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("BOX")
public class ItemWithThreeSizes extends BaseItem {

    @Column(name = "size_1")
    private Double length;

    @Column(name = "size_2")
    private Double width;

    @Column(name = "size_3")
    private Double height;

    @Override
    public String toString() {
        return "ItemWithThreeSizes{" +
                super.toString() +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
