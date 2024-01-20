package by.andd3dfx.model.shop;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("2-size")
public class TwoSizeItem extends OneSizeItem {

    @Column
    private Double width;

    @Override
    public String toString() {
        return "TwoSizeItem{" +
                super.toString() +
                ", width=" + width +
                '}';
    }
}
