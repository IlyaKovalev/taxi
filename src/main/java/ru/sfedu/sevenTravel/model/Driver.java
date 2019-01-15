package ru.sfedu.sevenTravel.model;

import ru.sfedu.sevenTravel.utils.*;

import java.util.Objects;

public class Driver implements Entity{

    private String id;
    private String fullName;
    private String passportNumber;
    private int experience;
    private int age;
    private double rating;

    public Driver(Driver driver){
        this.passportNumber = driver.getPassportNumber();
        this.setId(driver.getId());
        this.fullName = driver.getFullName();
        this.experience = driver.getExperience();
        this.age = driver.getAge();
        this.rating = driver.getRating();
    }

    public Driver(String fullName, String passportNumber,
                  int experience, int age, double rating) {
        this.passportNumber = passportNumber;
        this.fullName = fullName;
        this.experience = experience;
        this.age = age;
        this.rating = rating;
        this.setId(Hash.hashCode(passportNumber));
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String[] toStringArray(){
        return new String[]{getId(),getFullName(), getPassportNumber(), String.valueOf(getExperience()),
                            String.valueOf(getAge()), String.valueOf(rating)};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;
        Driver driver = (Driver) o;
        return Objects.equals(getId(), driver.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }

    public static Entity newElement(String[] args) {
        return new Driver(args[1], args[2], Integer.valueOf(args[3]),
                Integer.valueOf(args[4]), Double.valueOf(args[5]));
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName(){
        return this.getFullName();
    }

    @Override
    public void setName(String name) { }

    @Override
    public String toString() {
        return "Driver{" +
                "fullName='" + fullName + '\'' +
                ", experience=" + experience +
                ", age=" + age +
                ", rating=" + rating +
                '}';
    }
}
