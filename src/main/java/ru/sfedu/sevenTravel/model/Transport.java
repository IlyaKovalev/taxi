package ru.sfedu.sevenTravel.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Transport implements Entity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private float price;
    private String info;
    private int numberOfSeats;
    private String model;

    @OneToMany(cascade = CascadeType.ALL)
    List<Review> reviews = new ArrayList<>();

    protected Transport(){}

    public Transport(float price, String info,
                     int numberOfSeats, String model) {
        this.price = price;
        this.info = info;
        this.numberOfSeats = numberOfSeats;
        this.model = model;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String[] toStringArray(){
        return new String[]{};
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
        return getModel();
    }

    @Override
    public void setName(String name) { }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + getId() +
                ", price=" + price +
                ", info='" + info + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", model='" + model + '\'' +
                '}';
    }
}
