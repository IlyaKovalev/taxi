package ru.sfedu.sevenTravel.utils;

import ru.sfedu.sevenTravel.beans.Car;
import ru.sfedu.sevenTravel.beans.Driver;
import ru.sfedu.sevenTravel.beans.Helicopter;
import ru.sfedu.sevenTravel.beans.User;

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
        return Stream.iterate(start, (i)->i+1).
                map((i)->new Car("BMW X"+i, start+i,"some info", 4, "CG-"+i, Car.Drive.forwardDrive)).limit(count)
                .collect(Collectors.toList());
    }

    public static List<Helicopter> generateHelicopter(int start, int count){
        return Stream.iterate(start, (i)->i+1).
                map((i)->new Helicopter("MK-X"+i, "XX-CVM-"+i,start + i, "some info", 4,20, 10, 100, 4 )).limit(count)
                .collect(Collectors.toList());
    }

}
