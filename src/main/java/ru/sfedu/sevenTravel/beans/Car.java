package ru.sfedu.sevenTravel.beans;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.sevenTravel.utils.*;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Root
public class Car extends Transport {

    @Element private String carNumber;
    @Element private Drive drive;

    public Car(@Element(name = "model") String model,@Element(name = "price") float price,
               @Element(name = "info") String info, @Element(name = "numberOfSeats") int numberOfSeats,
                @Element(name = "carNumber") String carNumber, @Element(name = "drive") Drive drive)
    {
        super(price, info, numberOfSeats, model);
        this.carNumber = carNumber;
        this.drive = drive;
        setId(Hash.hashCode(carNumber));
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public static Entity newElement(String[] args){
        return new Car(args[1], Float.valueOf(args[2]), args[3], Integer.valueOf(args[4]),
                args[5], Drive.getDrive(args[6]));
    }

    @Override
    public String[] toStringArray(){
        return new String[]{getId(), getModel(), String.valueOf(getPrice()),
                            getInfo(), String.valueOf(getNumberOfSeats()),
                             getCarNumber(), Drive.getString(this.drive)};
    }

    @Override
    public String toString() {
        return "Car{" +
                "carNumber='" + carNumber + '\'' +
                "id='" + getId() + '\'' +
                "model='" + getModel() + '\'' +
                '}';
    }

    public enum Drive{
        forwardDrive, backDrive, UNKNOWN;

        public static String getString(Drive drive){
            switch (drive){
                case backDrive:
                   return  "back drive";
                case forwardDrive:
                    return "forward drive";
                    default:
                        return "UNKNOWN";
            }
        }

        public static Drive getDrive(String str){
            switch (str){
                case "back drive":
                    return backDrive;
                case "forward drive":
                    return forwardDrive;
                default:
                    return UNKNOWN;
            }
        }
    }

}
