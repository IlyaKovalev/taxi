package ru.sfedu.sevenTravel.search;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.sfedu.sevenTravel.model.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class SearchTransportTest {

    public static List<Transport> transport;
    public static List<Review> reviews;

    public static SearchTransport searchTransportEngine = new SearchTransport();

    private static Logger log = LogManager.getLogger(SearchTransport.class);

    @BeforeClass
    public static void setData() {
        reviews = new LinkedList<Review>();
        reviews.add(new Review("esdf","pretty good", "good service", 10));
        transport = generate(0, 100);
    }

    public static List<Transport> generate(int start, int count){
        return Stream.iterate(start, i->i+1).limit(count)
                .map(i-> new Helicopter("MK-"+i,"90-"+start+i, 100+i,
                        "some info", 4, 20, 10, 100, 4))
                .collect(Collectors.toList());
    }

    @Test
    public void sSearchByTransport() {
        log.debug("test Search by transport");
        List<Transport>list = searchTransportEngine.searchByTransport(transport,transport.get(0));
        assertEquals(100,list.size());
        log.debug("test successfully finish");
    }
    @Test
    public void searchByModel(){
        log.debug("test Search by model");
        List<Transport> result = searchTransportEngine.searchByModel(transport,"MK-3");
        assertEquals(11, result.size());
        log.debug("test successfully finish");
    }
    @Test
    public void searchByPrice(){
        log.debug("test Search by price");
        List<Transport> result = searchTransportEngine.searchByPrice(transport, 110, 15000);
        assertEquals(89, result.size());
        log.debug("test successfully finish");
    }

}