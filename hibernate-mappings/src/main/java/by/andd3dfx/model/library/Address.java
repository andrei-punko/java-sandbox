package by.andd3dfx.model.library;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String country;
    @Column
    private String town;

    @Column(name = "street_and_building")
    private String streetAndBuilding;
}
