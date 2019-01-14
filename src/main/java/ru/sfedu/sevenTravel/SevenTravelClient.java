package ru.sfedu.sevenTravel;

import org.apache.log4j.*;
import ru.sfedu.sevenTravel.beans.Car;
import ru.sfedu.sevenTravel.beans.Driver;
import ru.sfedu.sevenTravel.beans.Order;
import ru.sfedu.sevenTravel.beans.User;
import ru.sfedu.sevenTravel.cli.CLI;
import org.junit.runner.JUnitCore;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SevenTravelClient {

    private static Logger log = LogManager.getLogger(SevenTravelClient.class);

    private int i =0;

    public SevenTravelClient(){
        log.debug("<Peshkariki>[0]: starting application.........");
    }

    public void logBasicSystemInfo(){
        log.info("Launching application");
        log.info("Operating System:"+System.getProperty("os.name")+" "
                + System.getProperty("os.version"));
        log.info("JRE: " + System.getProperty("java.version"));
        log.info("Java Launched From: " + System.getProperty("java.home"));
        log.info("Class Path: " + System.getProperty("java.class.path"));
        log.info("Library Path: " + System.getProperty("java.library.path"));
        log.info("User Home Directory: " + System.getProperty("user.home"));
        log.info("User Working Directory: " + System.getProperty("user.dir"));
        log.info("Test INFO logging.");
    }

    public static List<Car> generate(int start, int count){
        return Stream.iterate(start, (i) -> i + 1).
                map((i)->new Car("BMW X"+i, 100+i,"some info", 4, "CG-"+i, Car.Drive.forwardDrive)).limit(count)
                .collect(Collectors.toList());
    }

    public static void main(String... args) {

        User user = new User("Alena Belyavtseva", "88005553535", "qwerty");
        List<Car> cars = generate(0, 10);
        CLI cli = new CLI();
        String str;
        while (true) {
            log.info("...\nInput key for desired action and press enter\n" +
                    "-a   go to CRUD methods\n" +
                    "-e   to exit");

            Scanner scanner = new Scanner(System.in);
            str = scanner.nextLine();

            switch (str) {
                case "-a":
                    log.info("\ninput desired command: CREATE | GET | REMOVE \n" +
                            "       desired data source: Csv | Xml | DB \n" +
                            "       desired entity: Driver | Car | Helicopter | User \n" +
                            "       GET and REMOVE work only with `id` like select by id or remove by id \n" +
                            "       CREATE command requires additionally values for create entity \n" +
                            "------------------------------------------------------------------------------\n" +
                            "Example: CREATE Driver Xml Brad_Pitt 3456 5 34 10 \n" +
                            "       where Brad_Pitt is full name \n" +
                            "             3456 - passport number \n" +
                            "             5 - years of experience \n" +
                            "             34 - age \n" +
                            "             10 - rating");
                    while (true) {
                        String command = scanner.nextLine();
                        String[] arrayCommand = command.split(" ");

                        String mainCom = arrayCommand[0];
                        if (mainCom.equals("CREATE")){
                            String result = cli.execute(arrayCommand);
                            log.info(result);
                            if (!result.contains("Invalid")) {
                                log.info("Try again....");
                                log.info("\n Select transport (number from 1 to 10)\n" +
                                        cars.stream().map(Car::toString).reduce(" cars: \n",(s1, s2)-> s1+"\n"+s2));
                                int number = Integer.valueOf(scanner.nextLine());

                                Order order = new Order(user, cars.get(number),(Driver) CLI.entity, cars.get(number).getPrice(), "street Pushkina", "Street Kolotushkina" );
                                log.info("Your order was created:\n" + order.toString());
                                break;

                            }

                        }else if (mainCom.equals("GET") || mainCom.equals("REMOVE")){
                            String result = cli.execute(arrayCommand);
                            log.info(result);
                            if (!result.contains("Invalid")) {
                                break;
                            }
                        }
                    }
                    break;

                case "-e":
                    log.info("good bay!!!");
                    System.exit(0);
            }
        }
    }

}
