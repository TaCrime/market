package ta_bluespurs.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public class BaseHibernateRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;
    protected Class<T> entityClass;

    public BaseHibernateRepository() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] args = genericSuperclass.getActualTypeArguments();
        this.entityClass = (Class<T>) args[0];
    }

    protected final Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public List<T> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityClass);
        Root<T> from = query.from(entityClass);
        return entityManager.createQuery(query.select(from)).getResultList();
    }

    public T getOne(String id) {
        return entityManager.find(entityClass, id);
    }

    public T save(T entity) {
        Object id = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
        if (id == null) {
            entityManager.persist(entity);
            return entity;
        } else {
            return entityManager.merge(entity);
        }
    }

    public void flushAndClear() {
        flush();
        clear();
    }

    public void flush() {
        entityManager.flush();
    }

    public void clear() {
        entityManager.clear();
    }


}
