package by.andd3dfx.model.library;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Collection;

@Data
@Entity
@Table(name = "PUBLISHER")
public class Publisher {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToOne(optional = false)
    @JoinColumn(name = "address_id")
    private Address address;

    @ToString.Exclude
    @OneToMany(mappedBy = "publisher")
    private Collection<Book> books;
}
