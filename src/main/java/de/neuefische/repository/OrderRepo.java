package de.neuefische.repository;

import de.neuefische.exceptions.OrderNotFoundException;
import de.neuefische.model.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class OrderRepo {

    //FIELDS
    private Map<String, Order> orders = new HashMap<>();


    //CONSTRUCTORS
    public OrderRepo() {
    }

    public OrderRepo(Map<String, Order> orders) {
        this.orders = orders;
    }


    // METHODS
    public Map<String, Order> list() {
        return orders;
    }

    public Optional<Order> get(String orderId) throws OrderNotFoundException {
        if (orders.containsKey(orderId))
            return Optional.of(orders.get(orderId));

        throw new OrderNotFoundException("No order with id" + orderId + " found.");
    }

    public Map<String, Order> add(Order newOrder) {
        orders.put(newOrder.getId(), newOrder);
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRepo orderRepo = (OrderRepo) o;
        return Objects.equals(orders, orderRepo.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orders);
    }


    // GETTERS & SETTERS
    public Map<String, Order> getOrders() {
        return orders;
    }
    public void setOrders(Map<String, Order> orders) {
        this.orders = orders;
    }
}
