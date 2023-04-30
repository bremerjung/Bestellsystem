package de.neuefische.model;

public class PhysicalProduct extends Product {

    private Colour colour;

    public PhysicalProduct(String id, String name, Colour colour) {
        super(id, name);
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "PhysicalProduct{" +
                "colour=" + colour +
                "} " + super.toString();
    }
}
