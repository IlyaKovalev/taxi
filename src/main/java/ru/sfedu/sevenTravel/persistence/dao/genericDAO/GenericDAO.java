package ru.sfedu.sevenTravel.persistence.dao.genericDAO;

import javax.persistence.LockModeType;
import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {
    public T findById(ID id);
    public T findById(ID id, LockModeType type);
    public T findReferenceById(ID id);
    public List<T> findAll();
    public long getCount();
    public T makePersistent(T entity);
    public void makeTransient(T entity);
    public void checkVersion(T entity, boolean forceUpdate);
}
