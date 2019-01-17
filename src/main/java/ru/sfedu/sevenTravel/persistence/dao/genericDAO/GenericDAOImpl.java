package ru.sfedu.sevenTravel.persistence.dao.genericDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sfedu.sevenTravel.utils.HibernateUtil;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    //public SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private EntityManager em;

    private final Class<T> entityClass;

    protected GenericDAOImpl(Class<T> entityClass){
        this.entityClass = entityClass;
        this.em = HibernateUtil.getSessionFactory().createEntityManager();
    }

    public void setEntityManager(EntityManager em){
        this.em = em;
    }

    @Override
    public T findById(ID id){
        return findById(id, LockModeType.NONE);
    }

    @Override
    public T findById(ID id, LockModeType type){
        return em.find(entityClass, id, type);
    }

    @Override
    public T findReferenceById(ID id){
        return em.getReference(entityClass, id);
    }

    @Override
    public List<T> findAll(){
        CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(entityClass);
        query.select(query.from(entityClass));
        return em.createQuery(query).getResultList();
    }

    @Override
    public long getCount() {
        CriteriaQuery<Long> query =
                em.getCriteriaBuilder().createQuery(Long.class);
        query.select(em.getCriteriaBuilder().count(query.from(entityClass)));
        long count = em.createQuery(query).getSingleResult();
        return count;

    }

    @Override
    public T makePersistent(T entity){

        em.getTransaction().begin();
        entity = em.merge(entity);
        em.getTransaction().commit();

        return entity;
    }

    @Override
    public void makeTransient(T entity){
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public void checkVersion(T entity, boolean forceUpdate) {
        em.lock(
                entity,
                forceUpdate
                        ? LockModeType.OPTIMISTIC_FORCE_INCREMENT
                        : LockModeType.OPTIMISTIC
        );
    }
    public void close(){
        this.em.close();
    }
    public void open(){
        em = HibernateUtil.getSessionFactory().createEntityManager();
    }
}
