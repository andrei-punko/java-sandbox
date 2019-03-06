package by.andd3dfx;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import by.andd3dfx.model.Address;
import by.andd3dfx.model.Author;
import by.andd3dfx.model.Book;
import by.andd3dfx.model.Publisher;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateMainIT {

    @Test
    public void main() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();

        List<Book> books = session.createCriteria(Book.class).list();
        System.out.println(books);
        assertThat("Wrong books count", books.size(), is(3));

        List<Address> addresses = session.createCriteria(Address.class).list();
        System.out.println(addresses);
        assertThat("Wrong addresses count", addresses.size(), is(3));

        List<Author> authors = session.createCriteria(Author.class).list();
        System.out.println(authors);
        assertThat("Wrong authors count", authors.size(), is(3));

        List<Publisher> publishers = session.createCriteria(Publisher.class).list();
        System.out.println(publishers);
        assertThat("Wrong publishers count", publishers.size(), is(3));

        session.close();
    }
}
