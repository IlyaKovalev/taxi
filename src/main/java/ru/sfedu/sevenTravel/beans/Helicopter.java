package ru.sfedu.sevenTravel.beans;

import org.simpleframework.xml.Element;
import ru.sfedu.sevenTravel.utils.*;

public class Helicopter extends Transport {

    @Element private String helicopterNumber;
    @Element private double length;
    @Element private double width;
    @Element private double maxSpeed;
    @Element private int numberOfScrews;

    public Helicopter(@Element(name = "model") String model, @Element(name = "helicopterNumber") String helicopterNumber,
                      @Element(name = "price") float price,@Element(name = "info") String info,
                      @Element(name = "numberOfSeats") int numberOfSeats, @Element(name = "length") double length,
                      @Element(name = "width") double width,@Element(name = "maxSpeed") double maxSpeed,
                      @Element(name = "numberOfScrews") int numberOfScrews)
    {
        super(price, info, numberOfSeats, model);
        this.length = length;
        this.width = width;
        this.maxSpeed = maxSpeed;
        this.helicopterNumber = helicopterNumber;
        this.numberOfScrews = numberOfScrews;
        setId(Hash.hashCode(helicopterNumber));
    }

    public String getHelicopterNumber() {
        return helicopterNumber;
    }

    public void setHelicopterNumber(String helicopterNumber) {
        this.helicopterNumber = helicopterNumber;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getNumberOfScrews() {
        return numberOfScrews;
    }

    public void setNumberOfScrews(int numberOfScrews) {
        this.numberOfScrews = numberOfScrews;
    }

    @Override
    public String[] toStringArray(){
        return new String[]{getId(), getModel(), getHelicopterNumber(),String.valueOf(getPrice()), getInfo(), String.valueOf(getNumberOfSeats()),
                             String.valueOf(getLength()), String.valueOf(getWidth()), String.valueOf(getMaxSpeed()), String.valueOf(getNumberOfScrews())};
    }

    public static Entity newElement(String[] args){
        return new Helicopter(args[1], args[2], Float.valueOf(args[3]), args[4],
                Integer.valueOf(args[5]), Double.valueOf(args[6]), Double.valueOf(args[7]),
                Double.valueOf(args[8]), Integer.valueOf(args[9]));
    }

    @Override
    public String toString() {
        return "Helicopter{" +
                "id=" + getId() +
                "model=" + getModel() +
                "length=" + length +
                ", width=" + width +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}
