package ru.sfedu.sevenTravel;

import ru.sfedu.sevenTravel.model.*;
import ru.sfedu.sevenTravel.model.User.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {

    public static List<User> generateUser(int start, int limit){
        return Stream.iterate(start, i->i+1).
                map(i-> new User("user"+(start+i), "223553"+String.valueOf(i), "qwerty"+i)).
                limit(limit).
                collect(Collectors.toList());
    }

    public static List<Driver> generateDriver(int start, int count){
        return Stream.iterate(start, (i)->i+1).
                map((i)->new Driver("driver"+i,"4566"+i,1+ start +i,21, 9)).limit(count)
                .collect(Collectors.toList());
    }

    public static List<Car> generateCar(int start, int count){
        return Stream.iterate(start, (i)->i+1)
                .map((i)->new Car("BMW X"+i, start+i,"some info", 4, "CG-"+i, Car.Drive.forwardDrive))
                .limit(count)
                .collect(Collectors.toList());
    }

    public static List<Helicopter> generateHelicopter(int start, int count){
        return Stream.iterate(start, (i)->i+1)
                .map((i)->new Helicopter("MK-X"+i, "XX-CVM-"+i,start + i, "some info", 4,20, 10, 100, 4 ))
                .limit(count)
                .collect(Collectors.toList());
    }

    public static List<Order> generateOrder(int start, int count){
        List<User> users = generateUser(start, count);
        List<Car> cars = generateCar(start, count);
        List<Driver> drivers = generateDriver(start, count);
        return Stream.iterate(start, i->i+1)
                .limit(count)
                .map(i -> new Order(users.get(i), cars.get(i), drivers.get(i), 200, "A", "B"))
                .peek(order -> System.out.println(order.getDriver().getFullName()))
                .collect(Collectors.toList());
    }

}
