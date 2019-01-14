package ru.sfedu.sevenTravel.api;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.sfedu.sevenTravel.beans.Driver;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static org.junit.Assert.*;
import static ru.sfedu.sevenTravel.utils.Constants.*;
import static ru.sfedu.sevenTravel.utils.Generator.*;

public class XmlServiceTest {

    private final int start = 0;
    private final int count = 10;

    private List<Driver> drivers;
    private static XmlService<Driver> driverXmlService;

    @BeforeClass
    public static void setData(){
        driverXmlService = new XmlService<>(driverXml.getPath());
    }

    @Before
    public void generateData(){
        drivers = generateDriver(start, count);
    }

    @After
    public void clean(){
        drivers.forEach(driverXmlService::remove);
    }

    @Test
    public void save() {
        drivers.forEach(driverXmlService::save);
        assertEquals(count, driverXmlService.readAll().size());
    }

    @Test
    public void remove() {
        save();
        clean();
        assertEquals(0, driverXmlService.readAll().size());
    }

    @Test
    public void update() {
        save();
        List<Driver> updatedDrivers = generateDriver(start+100, count);
        Supplier<Driver> driverSupplier = updatedDrivers.iterator()::next;
        drivers.forEach(driver -> driverXmlService.update(driver, driverSupplier.get()));
        drivers = updatedDrivers;
        assertEquals(count, driverXmlService.readAll().stream()
                .filter(driver -> driver.getExperience() >= start+100)
                .count());
    }

    @Test
    public void selectByID() {
        save();
        assertEquals(count, drivers.stream()
                .map(Driver::getId)
                .map(driverXmlService::selectByID)
                .filter(Objects::nonNull)
                .count());
    }

    @Test
    public void selectByName() {
        save();
        assertEquals(count, drivers.stream()
                .map(Driver::getFullName)
                .map(driverXmlService::selectByName)
                .mapToInt(List::size)
                .sum());
    }

    @Test
    public void readAll() {
        save();
        assertEquals(count, driverXmlService.readAll().size());
    }
}