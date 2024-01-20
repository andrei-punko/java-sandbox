package by.andd3dfx;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import by.andd3dfx.model.library.Address;
import by.andd3dfx.model.library.Author;
import by.andd3dfx.model.library.Book;
import by.andd3dfx.model.library.Publisher;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateMainIT {

    @SneakyThrows
    @Test
    public void checkRecordsExistence() {
        try (var sessionFactory = new Configuration().configure().buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                getFromDbAndCheck(session, Book.class, 4);
                getFromDbAndCheck(session, Address.class, 2);
                getFromDbAndCheck(session, Author.class, 3);
                getFromDbAndCheck(session, Publisher.class, 2);

                EntityManager entityManager = session.getEntityManagerFactory().createEntityManager();
                getFromDbAndCheck(entityManager, "ZeroSizeItem", 1);
                getFromDbAndCheck(entityManager, "OneSizeItem", 1);
                getFromDbAndCheck(entityManager, "TwoSizeItem", 2);
                getFromDbAndCheck(entityManager, "ThreeSizeItem", 1);
            }
        }
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
