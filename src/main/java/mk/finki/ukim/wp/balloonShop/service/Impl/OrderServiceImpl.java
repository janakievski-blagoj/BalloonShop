package mk.finki.ukim.wp.balloonShop.service.Impl;

import mk.finki.ukim.wp.balloonShop.model.Order;
import mk.finki.ukim.wp.balloonShop.model.User;
import mk.finki.ukim.wp.balloonShop.model.exceptions.UsernameNotFound;
import mk.finki.ukim.wp.balloonShop.repository.JPARepository.OrderRepoJPA;
import mk.finki.ukim.wp.balloonShop.repository.JPARepository.UserRepoJPA;
import mk.finki.ukim.wp.balloonShop.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepoJPA orderRepository;
    private final UserRepoJPA userRepoJPA;

    public OrderServiceImpl(OrderRepoJPA orderRepository, UserRepoJPA userRepoJPA) {
        this.userRepoJPA=userRepoJPA;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(String balloonColor, String balloonSize, String username) {
        User user = userRepoJPA.findByUsername(username)
             .orElseGet(() -> {
                 throw new UsernameNotFound(username);
             });
        return orderRepository.save(new Order(balloonColor, balloonSize, user));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllOrdersByUsername(String username) {
        User user = userRepoJPA.findByUsername(username)
                .orElseGet(() -> {
                    throw new UsernameNotFound(username);
                });
        return orderRepository.findAllByUser(user);
    }


//    @Override
//    public List<Order> getAllOrdersByNameAndAddress(String name) {
//        return orderRepository.findByName(name);
//    }

}
