package ru.sfedu.sevenTravel.model;


import ru.sfedu.sevenTravel.utils.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@javax.persistence.Entity
public class Car extends Transport {

    @Column(nullable = false, name = "CAR_NUMBER", length = 15)
    private String carNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private Drive drive;

    private Car(){}

    public Car(String model, float price,
               String info, int numberOfSeats,
               String carNumber, Drive drive)
    {
        super(price, info, numberOfSeats, model);
        this.carNumber = carNumber;
        this.drive = drive;
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
        return new String[]{String.valueOf(getId()), getModel(), String.valueOf(getPrice()),
                            getInfo(), String.valueOf(getNumberOfSeats()),
                             getCarNumber(), Drive.getString(this.drive)};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(getCarNumber(), car.getCarNumber());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCarNumber());
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
