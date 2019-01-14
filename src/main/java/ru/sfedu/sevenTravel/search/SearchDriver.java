package ru.sfedu.sevenTravel.search;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.sfedu.sevenTravel.beans.Driver;

import java.util.LinkedList;
import java.util.List;

public class SearchDriver {

    private static Logger log = LogManager.getLogger(SearchTransport.class);

    public SearchDriver(){
        log.debug("-----------------SearchDriver instance was created-----------------");
    }

    public List<Driver> searchByExperience(List<Driver>Collection, int experience){
        List<Driver> result = new LinkedList<Driver>();

        for (Driver driver:Collection){
            if (driver.getExperience()>=experience){
                log.debug("[searchByExperience]: adding driver "+driver.toString());
                result.add(driver);
            }
        }
        return result;
    }

    public List<Driver> searchByRating(List<Driver>Collection, double rating){
        List<Driver> result = new LinkedList<Driver>();
        for (Driver driver:Collection){
            if (driver.getRating()>=rating){
                log.debug("[searchByRating]: adding driver "+driver.toString());
                result.add(driver);
            }
        }
        return result;
    }

}
