package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order updateStatus(String orderId, String status);
    Order findById(String orderId);
    List<Order> findAllByAuthor(String author);
}