package by.andd3dfx.model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PUBLISHER")
public class Publisher {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToOne(optional = false)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "publisher")
    private Collection<Book> books;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBook(Collection<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Publisher{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", address=" + address +
            '}';
    }
}
