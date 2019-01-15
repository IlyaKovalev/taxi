package ru.sfedu.sevenTravel.model;

import ru.sfedu.sevenTravel.utils.Hash;

import java.util.Objects;


public class User implements Entity{

    private String id;
    private String fullName;
    private String phoneNumber;
    private String password;
    private int numberOfTravels;
    private Status status;

    public User(String fullName,
                String phoneNumber,
                String password)
    {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.numberOfTravels = 0;
        this.status = Status.STANDART;
        this.setId(Hash.hashCode(phoneNumber));
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return getFullName();
    }

    @Override
    public void setName(String name) {
        setFullName(name);
    }

    public int getNumberOfTravels() {
        return numberOfTravels;
    }

    public void setNumberOfTravels(int numberOfTravels) {
        this.numberOfTravels = numberOfTravels;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static User newElement(String[] data){
        return new User(data[1], data[2], data[3]);
    }

    public String[] toStringArray(){
        return new String[]{this.getId(), this.getFullName(), this.getPhoneNumber(), this.getPassword(),
                String.valueOf(this.getNumberOfTravels()), this.getStatus().name()};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", id=" + id +
                ", numberOfTravels=" + numberOfTravels +
                ", status=" + status +
                '}';
    }
}
