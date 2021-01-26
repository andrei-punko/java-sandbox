package by.andd3dfx.model.shop;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ITEMS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BaseItem {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Double price;
    private Double weight;
    private String description;
}
