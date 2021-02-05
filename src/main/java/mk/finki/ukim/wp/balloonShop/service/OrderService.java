package mk.finki.ukim.wp.balloonShop.service;

import mk.finki.ukim.wp.balloonShop.model.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(String balloonColor, String balloonSize, String user);
    List<Order> getAllOrders();
    List<Order> getAllOrdersByUsername(String username);
}
