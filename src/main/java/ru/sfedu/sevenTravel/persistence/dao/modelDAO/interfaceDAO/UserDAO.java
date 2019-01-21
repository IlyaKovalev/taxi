package ru.sfedu.sevenTravel.persistence.dao.modelDAO.interfaceDAO;

import ru.sfedu.sevenTravel.model.User.User;
import ru.sfedu.sevenTravel.model.User.User.Status;
import ru.sfedu.sevenTravel.persistence.dao.genericDAO.GenericDAO;

import java.util.List;

public interface UserDAO extends GenericDAO<User, Long> {

    public List<User> selectByName(String name);
    public List<User> selectByStatus(Status status);
}
