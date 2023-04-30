package de.neuefische.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    //FIELDS
    static int counter;
    private String id;
    private List<Product> products = new ArrayList<>();
    private OrderStatus orderstatus;
    private LocalDateTime dateTime;

    public LocalDateTime getDateTime() {
        return dateTime;
    }


    //CONSTRUCTORS
    public Order() {
        counter++;
        id = "O" + counter;

    }
    public Order(String id) {
        this.id = id;
        counter++;
    }

    public Order(String id, List<Product> products) {
        this.id = id;
        this.products = products;
    }

    public String getId() {
        //METHODS
        return id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", products=" + products +
                ", orderstatus=" + orderstatus +
                ", dateTime=" + dateTime +
                '}';
    }

    //GETTERS & SETTERS
    public void setId(String id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public OrderStatus getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(OrderStatus orderstatus) {
        this.orderstatus = orderstatus;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
