package ru.sfedu.sevenTravel.search;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.sfedu.sevenTravel.model.Transport;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchTransport {

    private static Logger log = LogManager.getLogger(SearchTransport.class);

    public SearchTransport(){
        log.debug("--------------------SearchTransport instance was created--------------------");
    }

    public List<Transport> searchByTransport(List<Transport> Collection, Transport transport){
        List<Transport> result = new LinkedList<Transport>();
        for(Transport tran:Collection){
            if(tran.getClass().getName().equals(transport.getClass().getName())){
                log.debug("[searchByTransport] adding to result of search "+transport.toString());
                result.add(tran);
            }
        }
        return result;
    }

    public List<Transport> searchByModel(List<Transport> Collection, String model){
        List<Transport> result = new LinkedList<>();
        Pattern pattern = Pattern.compile(model, Pattern.CASE_INSENSITIVE);
        for (Transport transport:Collection){
            Matcher matcher = pattern.matcher(transport.getModel());
            if (matcher.find()){
                log.debug("[searchByModel] adding to result of search "+transport.toString());
                result.add(transport);
            }
        }
        return result;
    }

    public List<Transport> searchByPrice(List<Transport> Collection, double startPrice, double endPrice){
        if(!(startPrice<endPrice)){
            log.error(String.format("[searchByPrice] wrong price range" +
                                " \n start price: %f \n end price: %f",startPrice ,endPrice ));
            return null;
        }
        List<Transport> result = new LinkedList<Transport>();
        for (Transport transport:Collection){
            double transportPrice = transport.getPrice();
            if (transportPrice>startPrice && transportPrice<endPrice){
                log.debug("[searchByPrice] adding to result of search"+transport.toString());
                result.add(transport);
            }
        }
        return result;
    }

}