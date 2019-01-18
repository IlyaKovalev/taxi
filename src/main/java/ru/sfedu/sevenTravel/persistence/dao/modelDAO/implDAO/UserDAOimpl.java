package ru.sfedu.sevenTravel.persistence.dao.modelDAO.implDAO;

import ru.sfedu.sevenTravel.model.User;
import ru.sfedu.sevenTravel.persistence.dao.genericDAO.GenericDAOImpl;
import ru.sfedu.sevenTravel.persistence.dao.modelDAO.interfaceDAO.UserDAO;
import ru.sfedu.sevenTravel.model.User.Status;
import java.util.List;

public class UserDAOimpl extends GenericDAOImpl<User, Long> implements UserDAO {

    protected UserDAOimpl() {
        super(User.class);
    }

    private Class<User> entityClass = User.class;

    public List<User> selectByName(String name){
        return em.createQuery("SELECT u from User u where u.fullName=:username", User.class)
                .setParameter("username", name)
                .getResultList();
    }

    public List<User> selectByStatus(Status status){
        return em.createQuery("SELECT u from User u where u.status=:userStatus", User.class)
                .setParameter("userStatus", status)
                .getResultList();
    }
}
