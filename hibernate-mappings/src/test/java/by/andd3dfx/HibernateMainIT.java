package by.andd3dfx;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import by.andd3dfx.model.library.Address;
import by.andd3dfx.model.library.Author;
import by.andd3dfx.model.library.Book;
import by.andd3dfx.model.library.Publisher;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateMainIT {

    @Test
    public void main() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();
        EntityManager entityManager = session.getEntityManagerFactory().createEntityManager();

        getFromDbAndCheck(session, Book.class, 3);
        getFromDbAndCheck(session, Address.class, 3);
        getFromDbAndCheck(session, Author.class, 3);
        getFromDbAndCheck(session, Publisher.class, 3);

        getFromDbAndCheck(entityManager, "ItemWithoutSize", 1);
        getFromDbAndCheck(entityManager, "ItemWithLengthAndDiameter", 2);
        getFromDbAndCheck(entityManager, "ItemWithThreeSizes", 1);

        session.close();
    }

    private void getFromDbAndCheck(Session session, Class clazz, int expectedItemsCount) {
        List items = session.createCriteria(clazz).list();
        System.out.println("Retrieved next items: " + items);

        assertThat("Wrong items count", items.size(), is(expectedItemsCount));
    }

    private void getFromDbAndCheck(EntityManager entityManager, String entityName, int expectedItemsCount) {
        Query query = entityManager.createQuery("SELECT c FROM " + entityName + " c");
        List items = query.getResultList();
        System.out.println("Retrieved next items: " + items);

        assertThat("Wrong items count", items.size(), is(expectedItemsCount));
    }
}
