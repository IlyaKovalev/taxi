package ru.sfedu.sevenTravel.cli;

import ru.sfedu.sevenTravel.model.BeanFactory;
import ru.sfedu.sevenTravel.model.Driver;
import ru.sfedu.sevenTravel.model.Entity;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI {

    public static Entity entity;

/*    public String execute(String[] args){
        if (args==null || args.length==0)return "command is null";
        DataProvider dataProvider;
        try {
            dataProvider = Command.getDataProvider(args[1], args[2]);
        }catch (Exception e){
            return "Invalid command";
        }
        switch (args[0]){
            case "CREATE":
                try {
                    entity = BeanFactory.getBean(args[1], Arrays.stream(args).skip(2).collect(Collectors.toList()));
                    if(dataProvider.save(BeanFactory.getBean(args[1], Arrays.stream(args).skip(2).collect(Collectors.toList())))){
                        return "Your entity successfully saved \n "+ entity;
                    }

                }catch (Exception e){
                    return "Invalid command check number of parameters and syntax";
                }
                break;
            case "GET":

                Stream<Character> stream = args[4].chars().mapToObj(c-> (char) c);

                if (stream.count()==40) {
                    return dataProvider.selectByID(args[4]).toString();
                }
                return dataProvider.selectByName(args[4]).toString();

            case "REMOVE":
                Stream<Character> stream1 = args[4].chars().mapToObj(c-> (char) c);
                Driver driver;
                if (stream1.count()==40) {
                    driver = (Driver) dataProvider.selectByID(args[4]);

                }else {
                    driver = (Driver) dataProvider.selectByName(args[4]).get(0);
                }
                dataProvider.remove(driver);
                return "your entity successfully removed";
            default:
                return "Invalid command: "+args[0];
        }
        return "Invalid command: "+args[0];
    }*/
}
