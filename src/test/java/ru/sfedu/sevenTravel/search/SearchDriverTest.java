package ru.sfedu.sevenTravel.search;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.sfedu.sevenTravel.model.*;

import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;

public class SearchDriverTest {

    public static List<Driver> drivers;
    public static List<Review> reviews;

    public static SearchDriver searchDriverEngine = new SearchDriver();

    private static Logger log = LogManager.getLogger(SearchTransport.class);

    @BeforeClass
    public static void setData() {
        log.debug("set data");
        reviews = new LinkedList<Review>();
        reviews.add(new Review("efre","pretty good", 10));

        drivers = new LinkedList<Driver>();
        drivers.add(new Driver("Магометов Сергей Корнеевич","6023 12453",4, 29, 7.8 ));
        drivers.add(new Driver("Бабочкин Пупсень Лунтекивич", "6023 14213", 7, 35, 9 ));
    }

    @Test
    public void searchDriverByRating(){
        log.debug("test search by rating");
        List<Driver> result = searchDriverEngine.searchByRating(drivers, 9);
        assertEquals(1, result.size());
        log.debug("test successfully finish");
    }
    @Test
    public void searchDriverByExperience(){
        log.debug("test search by Experience");
        List<Driver> result = searchDriverEngine.searchByExperience(drivers, 7);
        assertEquals("Бабочкин Пупсень Лунтекивич", result.get(0).getFullName());
        log.debug("test successfully finish");
    }
}