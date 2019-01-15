package ru.sfedu.sevenTravel.persistence;

import java.util.List;

public interface DataProvider<T> {

    public boolean save(T element);
    public void remove(T element);
    public void update(T oldElement, T newElement);
    public T selectByID(String id);
    public List<T> selectByName(String name);
}
