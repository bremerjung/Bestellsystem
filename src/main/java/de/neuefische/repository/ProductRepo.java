package de.neuefische.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import de.neuefische.exceptions.OrderNotFoundException;
import de.neuefische.exceptions.ProductNotFoundException;
import de.neuefische.model.Product;

public class ProductRepo {

    //FIELDS
    private Map<String, Product> products = new HashMap<>();


    //METHODS
    public Map<String, Product> list() {
        return products;
    } // TODO question: what to return? the map? or a list of products?

    public Optional<Product> get(String productId) throws ProductNotFoundException {

        if (products.containsKey(productId))
            return Optional.of(products.get(productId));

        throw new ProductNotFoundException("No product with id" + productId + " found.");
    }


    //GETTER & SETTER
    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }
}
