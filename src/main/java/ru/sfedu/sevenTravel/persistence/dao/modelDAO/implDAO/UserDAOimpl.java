package ru.sfedu.sevenTravel.persistence.dao.modelDAO.implDAO;

import ru.sfedu.sevenTravel.model.User;
import ru.sfedu.sevenTravel.persistence.dao.genericDAO.GenericDAOImpl;
import ru.sfedu.sevenTravel.persistence.dao.modelDAO.interfaceDAO.UserDAO;

public class UserDAOimpl extends GenericDAOImpl<User, Long> implements UserDAO {

    protected UserDAOimpl() {
        super(User.class);
    }
}
