package ru.sfedu.sevenTravel.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.sfedu.sevenTravel.model.Driver;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.Assert.*;
import static ru.sfedu.sevenTravel.Generator.*;
import static ru.sfedu.sevenTravel.utils.Schema.*;

public class JdbcServiceTest {

    private final int start = 0;
    private final int count = 10;

    private List<Driver> drivers;
    private static JdbcService<Driver> driverJdbcService;

    @BeforeClass
    public static void setData(){
        driverJdbcService = new JdbcService<>(DRIVER_TABLE.getTable(), Driver.class, DRIVER_TABLE.getColumns());
    }

    @Before
    public void generateData(){
        drivers = generateDriver(start, count);
    }

    @After
    public void clean(){
        drivers.forEach(driverJdbcService::remove);
    }

    @Test
    public void save() {
        drivers.forEach(driverJdbcService::save);
        assertEquals(count, driverJdbcService.selectAll().size());
    }

    @Test
    public void remove() {
        save();
        clean();
        assertEquals(0, driverJdbcService.selectAll().size());
    }

    @Test
    public void update() {
        save();
        List<Driver> updatedDrivers = generateDriver(start+100, count);
        Supplier<Driver> driverSupplier = updatedDrivers.iterator()::next;
        drivers.forEach(driver -> driverJdbcService.update(driver, driverSupplier.get()));
        drivers = updatedDrivers;
        assertEquals(count, driverJdbcService.selectAll().stream()
                                                    .filter(driver -> driver.getExperience()>100)
                                                    .count());
    }

    @Test
    public void selectByID() {
        save();
        assertEquals(count, drivers.stream().map(Driver::getId).map(driverJdbcService::selectByID).count());
    }

    @Test
    public void selectByName() {
        save();
        assertEquals(count, drivers.stream().map(Driver::getFullName).map(driverJdbcService::selectByName).flatMap(List::stream).count());
    }

    @Test
    public void selectAll() {
        save();
        assertEquals(count, driverJdbcService.selectAll().size());
    }
}