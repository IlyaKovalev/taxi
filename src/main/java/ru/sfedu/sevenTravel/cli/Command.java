package ru.sfedu.sevenTravel.cli;

import ru.sfedu.sevenTravel.persistence.CsvService;
import ru.sfedu.sevenTravel.persistence.DataProvider;
import ru.sfedu.sevenTravel.persistence.JdbcService;
import ru.sfedu.sevenTravel.model.*;
import ru.sfedu.sevenTravel.utils.ConfigurationUtil;

import static ru.sfedu.sevenTravel.utils.Constants.*;
import static ru.sfedu.sevenTravel.utils.Schema.*;

public class Command {

    private DataProvider dataProvider;
    private Entity entity;

    private static String csvDir = ConfigurationUtil.readConfig("csvDir");
    private static String xmlDir = ConfigurationUtil.readConfig("xmlDir");

    public Command(DataProvider dataProvider){
        this.dataProvider = dataProvider;
    }


    public static DataProvider getDataProvider(String className, String source){

        switch (className){
            case "User":
                switch (source){
                    case "Csv":
                        return new CsvService<User>(userCsv.getPath(), User.class);
                    case "Xml":
                        return new XmlService<User>(userXml.getPath());
                    case "DB":
                        return new JdbcService<User>(USER_TABLE.getTable(), User.class, USER_TABLE.getColumns());
                }
                break;
            case "Driver":
                switch (source){
                    case "Csv":
                        return new CsvService<Driver>(driverCsv.getPath(), Driver.class);
                    case "Xml":
                        return new XmlService<Driver>(driverXml.getPath());
                    case "DB":
                        return new JdbcService<Driver>(DRIVER_TABLE.getTable(), Driver.class, DRIVER_TABLE.getColumns());
                }
                break;
            case "Car":
                switch (source){
                    case "Csv":
                        return new CsvService<Car>(carCsv.getPath(), Car.class);
                    case "Xml":
                        return new XmlService<Car>(carXml.getPath());
                    case "DB":
                        return new JdbcService<Car>(CAR_TABLE.getTable(), Car.class, CAR_TABLE.getColumns());
                }
                break;
            case "Helicopter":
                switch (source){
                    case "Csv":
                        return new CsvService<Helicopter>(helicopterCsv.getPath(), Helicopter.class);
                    case "Xml":
                        return new XmlService<Helicopter>(helicopterXml.getPath());
                    case "DB":
                        return new JdbcService<Helicopter>(HELICOPTER_TABLE.getTable(), Helicopter.class, HELICOPTER_TABLE.getColumns());
                }
                break;
        }
        return null;
    }

}
