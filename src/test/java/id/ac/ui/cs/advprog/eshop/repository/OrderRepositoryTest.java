package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderRepositoryTest {
    OrderRepository orderRepository;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("6a48c142-207c-44bb-9278-f0cb2816ed97",
                products, 1708560000L, "Ruri Sinaga");
        orders.add(order1);
        Order order2 = new Order("0ad22a3a-3e61-47dd-95f0-d528d6d997d1",
                products, 1708560000L, "Ruri Sinaga");
        orders.add(order2);
        Order order3 = new Order("f50010a7-8747-4bae-ba07-f38f5e18d8f8",
                products, 1708560000L, "Bagas Dribble");
        orders.add(order3);
    }

    @Test
    void testSaveCreate() {
        Order order = orders.get(1);
        Order result = orderRepository.save(order);

        Order findResult = orderRepository.findById(orders.get(1).getId());
        assertEquals(order.getId(), result.getId());
        assertEquals(order.getId(), findResult.getId());
        assertEquals(order.getOrderTime(), findResult.getOrderTime());
        assertEquals(order.getAuthor(), findResult.getAuthor());
        assertEquals(order.getStatus(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate() {
        Order order = orders.get(1);
        orderRepository.save(order);
        Order newOrder = new Order(order.getId(), order.getProducts(), order.getOrderTime(),
                order.getAuthor(), OrderStatus.SUCCESS.getValue());
        Order result = orderRepository.save(newOrder);

        Order findResult = orderRepository.findById(orders.get(1).getId());
        assertEquals(order.getId(), result.getId());
        assertEquals(order.getId(), findResult.getId());
        assertEquals(order.getOrderTime(), findResult.getOrderTime());
        assertEquals(order.getAuthor(), findResult.getAuthor());
        assertEquals(OrderStatus.SUCCESS.getValue(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Order order : orders) {
            orderRepository.save(order);
        }

        Order findResult = orderRepository.findById(orders.get(1).getId());
        assertEquals(orders.get(1).getId(), findResult.getId());
        assertEquals(orders.get(1).getOrderTime(), findResult.getOrderTime());
        assertEquals(orders.get(1).getAuthor(), findResult.getAuthor());
        assertEquals(orders.get(1).getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Order order : orders) {
            orderRepository.save(order);
        }

        Order findResult = orderRepository.findById("zczc");
        assertNull(findResult);
    }

    @Test
    void testFindAllByAuthorIfAuthorCorrect() {
        for (Order order : orders) {
            orderRepository.save(order);
        }

        List<Order> orderList = orderRepository.findAllByAuthor(
                orders.get(1).getAuthor());
        assertEquals(2, orderList.size());
    }

    @Test
    void testFindAllByAuthorIfAllLowercase() {
        orderRepository.save(orders.get(1));

        List<Order> orderList = orderRepository.findAllByAuthor(
                orders.get(1).getAuthor().toLowerCase());
        assertTrue(orderList.isEmpty());
    }
}