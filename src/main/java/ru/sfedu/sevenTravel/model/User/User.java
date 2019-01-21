package ru.sfedu.sevenTravel.model.User;

import javax.persistence.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@javax.persistence.Entity
public class User implements ru.sfedu.sevenTravel.model.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;
    private PhoneNumber phoneNumber;
    private Password password;
    private int numberOfTravels;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    private User(){}

    public User(String fullName,
                String phoneNumber,
                String password)
    {
        this.fullName = fullName;
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.password = new Password(password);
        this.numberOfTravels = 0;
        this.status = Status.STANDART;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.setPhoneNumber(phoneNumber);
    }

    public String getPassword() {
        return password.getPassword();
    }

    public void setPassword(String password) {
        this.password.setPassword(password);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
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
        return new String[]{String.valueOf(getId()), getFullName(), phoneNumber.getPhoneNumber(), password.getPassword(),
                String.valueOf(getNumberOfTravels()), getStatus().name()};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getFullName(), user.getFullName()) &&
                Objects.equals(getPhoneNumber(), user.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFullName(), getPhoneNumber());
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", PhoneNumber='" + phoneNumber + '\'' +
                ", id=" + id +
                ", numberOfTravels=" + numberOfTravels +
                ", status=" + status +
                '}';
    }

    public enum Status {
        VIP, STANDART, BAD_REPUTATION;

        public static Status getStatus(String status){
            switch (status){
                case "VIP":
                    return VIP;

                case "STANDART":
                    return STANDART;

                case "BAD_REPUTATION":
                    return BAD_REPUTATION;
            }
            return null;
        }
    }
}
