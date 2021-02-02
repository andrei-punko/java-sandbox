package by.andd3dfx.model.shop;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("TUBE")
public class ItemWithLengthAndDiameter extends BaseItem {

    @Column(name = "size_1")
    private Double length;

    @Column(name = "size_2")
    private Double diameter;

    @Override
    public String toString() {
        return "ItemWithLengthAndDiameter{" +
                super.toString() +
                "length=" + length +
                ", diameter=" + diameter +
                '}';
    }
}
