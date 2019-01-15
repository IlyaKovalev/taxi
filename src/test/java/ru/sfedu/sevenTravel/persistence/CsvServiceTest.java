package ru.sfedu.sevenTravel.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.sfedu.sevenTravel.model.*;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static org.junit.Assert.*;
import static ru.sfedu.sevenTravel.utils.Constants.*;
import static ru.sfedu.sevenTravel.Generator.*;

public class CsvServiceTest {

    private static CsvService<User> userCsvService;
    private static CsvService<Driver> driverCsvService;

    private List<User> users;
    private List<Driver> drivers;

    private final int count = 10;

    @BeforeClass
    public static void setData(){
        userCsvService = new CsvService<User>(userCsv.getPath(), User.class);
        driverCsvService = new CsvService<Driver>(driverCsv.getPath(), Driver.class);
    }

    @Before
    public void generateData(){
        users = generateUser(0, count);
        drivers = generateDriver(0, count);
    }

    @After
    public void clean(){
        users.forEach(user -> userCsvService.remove(user));
        drivers.forEach(driver -> driverCsvService.remove(driver));
    }
    
    @Test
    public void save() {
        users.forEach(user -> userCsvService.save(user));
        drivers.forEach(driver -> driverCsvService.save(driver));
        assertEquals(count, userCsvService.readAll().size());
        assertEquals(count, driverCsvService.readAll().size());
    }

    @Test
    public void remove() {
        save();
        clean();
        assertEquals(0, userCsvService.readAll().size());
        assertEquals(0, driverCsvService.readAll().size());
    }

    @Test
    public void update() {
        users.forEach(user -> userCsvService.save(user));
        drivers.forEach(driver -> driverCsvService.save(driver));
        List<User> updatedUsers = generateUser(100,count);
        List<Driver> updatedDrivers = generateDriver(100, count);
        Supplier<User> userSupplier = updatedUsers.iterator()::next;
        Supplier<Driver> driverSupplier = updatedDrivers.iterator()::next;
        users.forEach(user->userCsvService.update(user, userSupplier.get()));
        users = updatedUsers;
        drivers.forEach(driver -> driverCsvService.update(driver, driverSupplier.get()));
        drivers = updatedDrivers;
        assertEquals(count, driverCsvService.readAll().stream()
                                .filter(driver -> driver.getExperience() >= 100)
                                .count());
        assertEquals(count, userCsvService.readAll().stream()
                .map(user -> Integer.valueOf(user.getPhoneNumber()))
                .filter(num -> num >= 100)
                .count());
    }

    @Test
    public void selectByID() {
        save();
        assertEquals(count, users.stream().map(user -> userCsvService.selectByID(user.getId()))
                                .filter(Objects::nonNull)
                                .count());
        assertEquals(count, drivers.stream().map(driver -> driverCsvService.selectByID(driver.getId()))
                .filter(Objects::nonNull)
                .count());
    }

    @Test
    public void selectByName() {
        save();
        assertEquals(count, users.stream().map(user -> userCsvService.selectByName(user.getFullName()))
                                .filter(list -> !list.isEmpty())
                                .count());
        assertEquals(count, drivers.stream().map(driver -> driverCsvService.selectByName(driver.getFullName()))
                                .filter(list -> !list.isEmpty())
                                .count());

    }
}