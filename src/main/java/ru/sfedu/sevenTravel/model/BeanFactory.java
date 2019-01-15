package ru.sfedu.sevenTravel.model;

import java.util.List;

public class BeanFactory<T extends Entity> {

    private BeanFactory(){}

    public static Entity getBean(String cls, String[] args){
        if (cls.contains("Car"))return Car.newElement(args);
        if (cls.contains("Helicopter"))return Helicopter.newElement(args);
        if (cls.contains("Driver"))return Driver.newElement(args);
        if (cls.contains("User"))return User.newElement(args);
        throw new NullPointerException();
    }

    public static Entity getBean(String cls, List<String> list){
        return getBean(cls, list.toArray(new String[list.size()]));
    }

    public static Entity getBean(Class cls, String[] data){
        return getBean(cls.getName(), data);
    }
}
