package ru.sfedu.sevenTravel.model;

import ru.sfedu.sevenTravel.utils.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Order implements Entity{

    private String id;
    private User user;
    private Transport transport;
    private Driver driver;
    private double totalPrice;
    private String fromAddress;
    private String toAddress;
    private LocalDate date;

    public Order(User user, Transport transport, Driver driver,
                 double totalPrice, String fromAddress, String toAddress) {
        this.user = user;
        this.transport = transport;
        this.driver = driver;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.date = LocalDate.now();
        this.totalPrice = totalPrice;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String keyOrder = user.getId() + transport.getId() + driver.getId() + date;
        setId(Hash.hashCode(keyOrder));
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
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
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
        return new String[]{getId(), getUser().getId(), getTransport().getId(), getDriver().getId(),
                            String.valueOf(getTotalPrice()), getFromAddress(), getToAddress()};
    }


    public static Entity newElement(String[] args) {
        throw new UnsupportedOperationException();
    }

    public static Order makeOrder(User user, Transport transport, Driver driver,
                                  double total_price, String addressFrom, String addressTo){
            return new Order(user, transport, driver, total_price, addressFrom, addressTo);
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
