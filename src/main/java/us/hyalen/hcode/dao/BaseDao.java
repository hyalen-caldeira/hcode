package us.hyalen.hcode.dao;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseDao {
    @Autowired
    @Qualifier("mainSessionFactory")
    protected SessionFactory mainSessionFactory;

    @PersistenceContext
    @Getter
    private EntityManager entityManager;

    public BaseDao(SessionFactory sessionFactory) {
        this.setMainSessionFactory(sessionFactory);
    }

    protected <E> void mergeAssociation(Set<E> updated, Set<E> existing) {
        // Delete collection elements that were in existing collection but not the updated one
        Set<E> elementsToDelete = Sets.difference(existing, updated);
        elementsToDelete.forEach(e -> mainSessionFactory.getCurrentSession().delete(e));

        // Insert collection elements that were in update collection but not the existing one
        Set<E> elementsToCreate = Sets.difference(updated, existing);
        elementsToCreate.forEach(e -> mainSessionFactory.getCurrentSession().save(e));

        // The rest of collection elements need to be merged
        Set<E> elementsToMerge = new HashSet<E>(updated);
        elementsToMerge.removeAll(elementsToDelete);
        elementsToMerge.removeAll(elementsToCreate);
        elementsToMerge.forEach(e -> mainSessionFactory.getCurrentSession().merge(e));
    }
}
