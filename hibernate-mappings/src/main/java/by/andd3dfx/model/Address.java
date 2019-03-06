package by.andd3dfx.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private long id;
    private String country;
    private String town;

    @Column(name = "street_and_building")
    private String streetAndBuilding;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreetAndBuilding() {
        return streetAndBuilding;
    }

    public void setStreetAndBuilding(String streetAndBuilding) {
        this.streetAndBuilding = streetAndBuilding;
    }

    @Override
    public String toString() {
        return "Address{" +
            "id=" + id +
            ", country='" + country + '\'' +
            ", town='" + town + '\'' +
            ", streetAndBuilding='" + streetAndBuilding + '\'' +
            '}';
    }
}
