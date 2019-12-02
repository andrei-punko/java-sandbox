package by.andd3dfx.model.library;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
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

    @ToString.Exclude
    @OneToMany(mappedBy = "publisher")
    private Collection<Book> books;
}
