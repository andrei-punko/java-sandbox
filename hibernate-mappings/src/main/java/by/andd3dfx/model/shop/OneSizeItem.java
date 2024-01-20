package by.andd3dfx.model.shop;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("1-size")
public class OneSizeItem extends ZeroSizeItem {

    @Column
    private Double length;

    @Override
    public String toString() {
        return "OneSizeItem{" +
                super.toString() +
                ", length=" + length +
                '}';
    }
}
