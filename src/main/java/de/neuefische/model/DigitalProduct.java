package de.neuefische.model;

public class DigitalProduct extends Product {

    private String downloadLink;

    public DigitalProduct(String id, String name, String downloadLink) {
        super(id, name);
        this.downloadLink = downloadLink;
    }

    @Override
    public String toString() {
        return "DigitalProduct{" +
                "downloadLink='" + downloadLink + '\'' +
                "} " + super.toString();
    }
}
