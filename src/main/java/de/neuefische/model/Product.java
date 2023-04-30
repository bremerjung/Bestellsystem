package de.neuefische.model;

public abstract class Product implements Purchasable {

    //FIELDS
    private String id;

    private String name;


    //CONSTRUCTORS
    public Product() {

    }
    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }


    //METHODS
    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void purchase() {

    }


    //GETTERS & SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
