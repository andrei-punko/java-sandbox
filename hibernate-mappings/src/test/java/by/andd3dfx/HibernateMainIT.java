package by.andd3dfx;

import by.andd3dfx.model.library.Address;
import by.andd3dfx.model.library.Author;
import by.andd3dfx.model.library.Book;
import by.andd3dfx.model.library.Publisher;
import by.andd3dfx.model.shop.OneSizeItem;
import by.andd3dfx.model.shop.ThreeSizeItem;
import by.andd3dfx.model.shop.TwoSizeItem;
import by.andd3dfx.model.shop.ZeroSizeItem;
import lombok.SneakyThrows;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HibernateMainIT {

    @SneakyThrows
    @Test
    public void checkRecordsAmount() {
        try (var sessionFactory = new Configuration().configure().buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                var emFactory = session.getEntityManagerFactory();
                var em = emFactory.createEntityManager();

                getFromDbAndCheck(em, Book.class, 4);
                getFromDbAndCheck(em, Address.class, 2);
                getFromDbAndCheck(em, Author.class, 3);
                getFromDbAndCheck(em, Publisher.class, 2);

                getFromDbAndCheck_ForEntityWithDiscriminator(em, ZeroSizeItem.class, 1);
                getFromDbAndCheck_ForEntityWithDiscriminator(em, OneSizeItem.class, 1);
                getFromDbAndCheck_ForEntityWithDiscriminator(em, TwoSizeItem.class, 2);
                getFromDbAndCheck_ForEntityWithDiscriminator(em, ThreeSizeItem.class, 1);
            }
        }
    }

    private <T> void getFromDbAndCheck(EntityManager em, Class<T> clazz, int expectedItemsCount) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(clazz);
        Root<T> variableRoot = query.from(clazz);
        query.select(variableRoot);
        var items = em.createQuery(query).getResultList();

        System.out.println("Retrieved next items: " + items);
        assertThat("Wrong items count", items.size(), is(expectedItemsCount));
    }

    private <T> void getFromDbAndCheck_ForEntityWithDiscriminator(EntityManager em, Class<T> clazz, int expectedItemsCount) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(clazz);
        Root<T> variableRoot = query.from(clazz);
        query.where(builder.equal(variableRoot.type(), clazz));
        query.select(variableRoot);
        var items = em.createQuery(query).getResultList();

        System.out.println("Retrieved next items: " + items);
        assertThat("Wrong items count", items.size(), is(expectedItemsCount));
    }
}
