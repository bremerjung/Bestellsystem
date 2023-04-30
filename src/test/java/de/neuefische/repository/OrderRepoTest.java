package de.neuefische.repository;

import de.neuefische.exceptions.OrderNotFoundException;
import de.neuefische.model.Order;
import de.neuefische.model.Product;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepoTest {

    @Test
    void testList_empty_map() {
        // given
        Map<String, Order> expected = new HashMap<>();
        OrderRepo orderRepo = new OrderRepo();
        orderRepo.setOrders(expected);

        // when
        Map<String, Order> actual = orderRepo.list();

        // then
        assertEquals(expected, actual);
    }

    @Test
    void testList_filled_map() {
        // given
        Map<String, Order> expected = new HashMap<>();
        Order order1 = new Order("o1");
        Order order2 = new Order("o2");
        expected.put(order1.getId(), order1);
        expected.put(order2.getId(), order2);
        OrderRepo orderRepo = new OrderRepo();
        orderRepo.setOrders(expected);

        // when
        Map<String, Order> actual = orderRepo.list();

        // then
        assertEquals(expected, actual);
    }

    @Test
    void testGet_positive_case() {
        // given
        Order expected = new Order("o1");

        OrderRepo orderRepo = new OrderRepo();
        Map<String, Order> orderMap = new HashMap<>();
        orderMap.put(expected.getId(), expected);
        orderRepo.setOrders(orderMap);

        // when
        Order actual = null;
        try {
            actual = orderRepo.get("o1").get();
        } catch (OrderNotFoundException e) {
            System.out.println("Order not found");
        }

        // then
        assertEquals(expected, actual);
    }

    @Test
    void testGet_negative_case() {
        // given
        Order expected = new Order("o1");

        OrderRepo orderRepo = new OrderRepo();
        Map<String, Order> orderMap = new HashMap<>();
        orderMap.put(expected.getId(), expected);
        orderRepo.setOrders(orderMap);

        // when & then
        assertThrows(OrderNotFoundException.class, () -> orderRepo.get("o2"));
    }

    @Test
    void testAdd() {
        // given
        OrderRepo orderRepo = new OrderRepo();
        Order order = new Order("o1");
        Map<String, Order> expected = new HashMap<>();
        expected.put(order.getId(), order);

        // when
        Map<String, Order> actual = orderRepo.add(order);

        // then
        // TODO how to compare two HashMaps?
        // assertEquals(expected, actual);

        assertTrue(expected.size() == actual.size());

    }

}