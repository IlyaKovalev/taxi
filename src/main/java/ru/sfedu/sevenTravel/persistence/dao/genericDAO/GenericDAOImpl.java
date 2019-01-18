package ru.sfedu.sevenTravel.persistence.dao.genericDAO;

import ru.sfedu.sevenTravel.utils.JPAUtil;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    protected EntityManager em;

    private final Class<T> entityClass;

    protected GenericDAOImpl(Class<T> entityClass){
        this.entityClass = entityClass;
        this.em = JPAUtil.getSessionFactory().createEntityManager();
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
        if (entity==null) throw new NullPointerException();
        open();
        em.getTransaction().begin();
        entity = em.merge(entity);
        em.getTransaction().commit();
        return entity;
    }

    public List<T> makePersistent(List<T> entities){
        if (entities==null) throw new NullPointerException();
        if (entities.size()==0) throw new NullPointerException();
        open();
        em.getTransaction().begin();
        entities = entities.stream().map(em::merge).collect(Collectors.toList());
        em.getTransaction().commit();
        return entities;
    }

    @Override
    public void makeTransient(T entity){
        if (entity==null)throw new NullPointerException();
        open();
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    public void makeTransient(List<T> entities){
        if (entities==null) throw new NullPointerException();
        if (entities.size()==0) throw new NullPointerException();
        open();
        em.getTransaction().begin();
        entities.forEach(em::remove);
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
        if (em!=null && em.isOpen())
            this.em.close();
    }

    public void open(){
        if (em!=null && em.isOpen()) return;
        em = JPAUtil.getSessionFactory().createEntityManager();
    }
}
