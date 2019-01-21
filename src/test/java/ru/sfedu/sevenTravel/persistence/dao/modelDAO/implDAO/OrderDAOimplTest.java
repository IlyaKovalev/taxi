package ru.sfedu.sevenTravel.persistence.dao.modelDAO.implDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.sfedu.sevenTravel.Generator;
import ru.sfedu.sevenTravel.model.Order;
import ru.sfedu.sevenTravel.persistence.dao.modelDAO.interfaceDAO.OrderDAO;

import java.util.List;

import static org.junit.Assert.*;

public class OrderDAOimplTest {

    private static final int count = 10;
    private static List<Order> orders;
    private static OrderDAO orderDAO;


    @BeforeClass
    public static void setData(){
        orders = Generator.generateOrder(0, count);
        orderDAO = new OrderDAOimpl();
    }

    @Before
    public void save(){
        orders = orderDAO.makePersistent(orders);
    }

    @After
    public void clear(){
        orderDAO.makeTransient(orders);
    }

    @Test
    public void makePersistent(){
        assertEquals(count, orderDAO.getCount());
        orderDAO.findAll().forEach(System.out::println);
    }
}