package by.andd3dfx.model.shop;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Box")
public class ItemWithThreeSizes extends AbstractItem {

    @Column(name = "size_1")
    private Double length;

    @Column(name = "size_2")
    private Double width;

    @Column(name = "size_3")
    private Double height;

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ItemWithThreeSizes{" +
            super.toString() +
            ", length=" + length +
            ", width=" + width +
            ", height=" + height +
            '}';
    }
}
