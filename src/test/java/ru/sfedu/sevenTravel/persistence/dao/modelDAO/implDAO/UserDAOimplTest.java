package ru.sfedu.sevenTravel.persistence.dao.modelDAO.implDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.sfedu.sevenTravel.Generator;
import ru.sfedu.sevenTravel.model.User.User;
import ru.sfedu.sevenTravel.model.User.User.Status;
import ru.sfedu.sevenTravel.persistence.dao.modelDAO.interfaceDAO.UserDAO;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class UserDAOimplTest {

    private static final int count = 10;
    static UserDAO userDAO;
    static List<User> users;

    @BeforeClass
    public static void setData(){
        userDAO = new UserDAOimpl();
        users = Generator.generateUser(0, count);
    }

    @Before
    public void saveData(){
        users = userDAO.makePersistent(users);
    }

    @After
    public void clear(){
        userDAO.makeTransient(users);
    }

    @Test
    public void makePersistent(){
        assertEquals(count, userDAO.getCount());
    }

    @Test
    public void selectByName() {
        String name = users.get(0).getFullName();
        List<User> selectedUsers = userDAO.selectByName(name);
        assertEquals(1, selectedUsers.size());
        assertEquals(name , selectedUsers.get(0).getFullName());
    }

    @Test
    public void selectByStatus() {
        users = users.stream()
                .peek(user -> user.setStatus(Status.VIP))
                .map(userDAO::makePersistent)
                .collect(Collectors.toList());
        List<User> selectedUsers = userDAO.selectByStatus(Status.VIP);
        assertEquals(count, selectedUsers.size());
        assertEquals(Status.VIP , selectedUsers.get(0).getStatus());
    }
}