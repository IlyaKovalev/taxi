package ru.sfedu.sevenTravel.model;

import ru.sfedu.sevenTravel.utils.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.persistence.Entity
public class Order implements Entity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRANSPORT_ID", nullable = false)
    private Transport transport;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DRIVER_ID", nullable = false)
    private Driver driver;

    @Column(precision=10, scale=2, nullable = false)
    private double totalPrice;
    private String fromAddress;
    private String toAddress;


    private LocalDate date = LocalDate.now();

    public Order(User user, Transport transport, Driver driver,
                 double totalPrice, String fromAddress, String toAddress) {
        this.user = user;
        this.transport = transport;
        this.driver = driver;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.date = LocalDate.now();
        this.totalPrice = totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        toAddress = toAddress;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName(){
        throw new UnsupportedOperationException();
    }

    @Override
    public void setName(String name) {

    }

    public String[] toStringArray(){
        return new String[]{String.valueOf(getId()), String.valueOf(getUser().getId()), String.valueOf(getTransport().getId()),
                            String.valueOf(getDriver().getId()), String.valueOf(getTotalPrice()),
                            getFromAddress(), getToAddress()};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getUser(), order.getUser()) &&
                Objects.equals(getTransport(), order.getTransport()) &&
                Objects.equals(getDriver(), order.getDriver()) &&
                Objects.equals(getDate(), order.getDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUser(), getTransport(), getDriver(), getDate());
    }

    @Override
    public String toString() {
        return "Order{\n" +
                "user=" + user +
                ",\n transport=" + transport +
                ",\n driver=" + driver +
                ",\n fromAddress='" + fromAddress + '\'' +
                ",\n toAddress='" + toAddress + '\'' +
                '}';
    }
}