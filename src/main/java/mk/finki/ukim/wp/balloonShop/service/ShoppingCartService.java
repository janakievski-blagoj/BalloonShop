package mk.finki.ukim.wp.balloonShop.service;

import mk.finki.ukim.wp.balloonShop.model.Order;
import mk.finki.ukim.wp.balloonShop.model.ShoppingCart;
import mk.finki.ukim.wp.balloonShop.model.User;

import java.util.List;
public interface ShoppingCartService {
    List<Order> listAllShoppingCartOrders(Long id);
    ShoppingCart getActiveUserProduct(Long id);
    ShoppingCart addProductToCart(String username, Long orderId);
}
