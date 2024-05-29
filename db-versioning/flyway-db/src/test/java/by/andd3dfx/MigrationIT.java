package by.andd3dfx;

import by.andd3dfx.model.Person;
import lombok.SneakyThrows;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MigrationIT {

    @SneakyThrows
    @Test
    public void checkRecordsAmount() {
        try (var sessionFactory = new Configuration().configure().buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                var emFactory = session.getEntityManagerFactory();
                var em = emFactory.createEntityManager();

                getFromDbAndCheck(em, Person.class, 3);
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
}
