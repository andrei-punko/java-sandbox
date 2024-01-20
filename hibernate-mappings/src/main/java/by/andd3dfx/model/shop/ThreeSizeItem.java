package by.andd3dfx.model.shop;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("3-size")
public class ThreeSizeItem extends TwoSizeItem {

    @Column
    private Double height;

    @Override
    public String toString() {
        return "ThreeSizeItem{" +
                super.toString() +
                ", height=" + height +
                '}';
    }
}
