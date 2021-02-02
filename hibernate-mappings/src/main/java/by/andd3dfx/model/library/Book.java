package by.andd3dfx.model.library;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @ManyToMany
    @JoinTable(name = "BOOK_AUTHOR",
        joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    @OrderBy("name")
    private Set<Author> authors;

    @ManyToOne(optional = false)
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;
}
