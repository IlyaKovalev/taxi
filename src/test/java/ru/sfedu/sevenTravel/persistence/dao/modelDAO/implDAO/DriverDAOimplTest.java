package ru.sfedu.sevenTravel.persistence.dao.modelDAO.implDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import ru.sfedu.sevenTravel.Generator;
import ru.sfedu.sevenTravel.model.Driver;
import ru.sfedu.sevenTravel.persistence.dao.genericDAO.GenericDAO;
import ru.sfedu.sevenTravel.persistence.dao.modelDAO.interfaceDAO.DriverDAO;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class DriverDAOimplTest {

    private static List<Driver> drivers;
    private static DriverDAO driverDAO;

    private static final int count = 10;

    @BeforeClass
    public static void setData(){
        drivers = Generator.generateDriver(0,count);
        driverDAO = new DriverDAOimpl();
    }

    @Before
    public void save(){
        drivers = drivers.stream().map(driverDAO::makePersistent).collect(Collectors.toList());
    }

    @After
    public void clean(){
        drivers.forEach(driverDAO::makeTransient);
    }

    @Test
    public void persist(){
        assertEquals(count, driverDAO.getCount());
    }
}