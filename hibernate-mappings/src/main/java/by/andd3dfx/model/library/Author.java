package by.andd3dfx.model.library;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "AUTHOR")
public class Author {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "authors")
    private Collection<Book> books;
}
