package ru.sfedu.sevenTravel.model;

import ru.sfedu.sevenTravel.utils.*;

public class Helicopter extends Transport {

    private String helicopterNumber;
    private double length;
    private double width;
    private double maxSpeed;
    private int numberOfScrews;

    public Helicopter(String model, String helicopterNumber,
                      float price, String info,
                      int numberOfSeats, double length,
                      double width, double maxSpeed,
                      int numberOfScrews)
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
