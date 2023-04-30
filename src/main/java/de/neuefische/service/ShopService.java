package de.neuefische.service;

import de.neuefische.exceptions.OrderNotFoundException;
import de.neuefische.exceptions.ProductNotFoundException;
import de.neuefische.model.Order;
import de.neuefische.model.Product;
import de.neuefische.repository.OrderRepo;
import de.neuefische.repository.ProductRepo;

import java.util.Map;
import java.util.Optional;

public class ShopService {

    //FIELDS
    ProductRepo productRepo;

    OrderRepo orderRepo;

    //CONSTRUCTORS
    public ShopService() {
        this.productRepo = new ProductRepo();
    }

    //BUSINESS LOGIC METHODS
    public Optional<Product> getProduct(String productId) {
        Product product = null;
        try {
            product = productRepo.get(productId).get();
        } catch (ProductNotFoundException e) {
            System.out.println("Product with id " + productId + " not found");
        }
        System.out.println(product);
        return Optional.of(product);

    }

    public void listProducts() {
        StringBuilder sb = new StringBuilder();
        for (Product product : productRepo.list().values()) {
            sb.append(product + "\n");
        }
        System.out.println(sb.toString());
    }

    public Map<String, Order> addOrder(Order newOrder) {
        Map<String, Order> result = orderRepo.add(newOrder);
        System.out.println("Order added: " + newOrder);
        return result;
    }

    public Optional<Order> getOrder(String orderId) {
        Optional<Order> order = null;
        try {
            order = orderRepo.get(orderId);
        } catch (OrderNotFoundException e) {
            System.out.println("No order for orderId " + orderId + " found.");
            ;
        }
        System.out.println(order);
        return order;
    }

    public Map<String, Order> listOrders() {
        StringBuilder sb = new StringBuilder();
        Map<String, Order> mapOfOrders = orderRepo.list();
        for (Order order : mapOfOrders.values()) {
            sb.append(order + "\n");
        }
        System.out.println(sb.toString());
        return mapOfOrders;
    }

    //GETTERS & SETTERS
    public ProductRepo getProductRepo() {
        return productRepo;
    }

    public void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public void setOrderRepo(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }
}
