package mk.finki.ukim.wp.balloonShop.service.Impl;

import mk.finki.ukim.wp.balloonShop.model.Order;
import mk.finki.ukim.wp.balloonShop.model.ShoppingCart;
import mk.finki.ukim.wp.balloonShop.model.User;
import mk.finki.ukim.wp.balloonShop.model.enums.OrdersStatus;
import mk.finki.ukim.wp.balloonShop.model.exceptions.OrderNotFoundExeption;
import mk.finki.ukim.wp.balloonShop.model.exceptions.ShoppingCartExp;
import mk.finki.ukim.wp.balloonShop.model.exceptions.UserNotFoundException;
import mk.finki.ukim.wp.balloonShop.model.exceptions.UserWithNameNotFoundException;
import mk.finki.ukim.wp.balloonShop.repository.JPARepository.OrderRepoJPA;
import mk.finki.ukim.wp.balloonShop.repository.JPARepository.ShoppingCartRepoJPA;
import mk.finki.ukim.wp.balloonShop.repository.JPARepository.UserRepoJPA;
import mk.finki.ukim.wp.balloonShop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepoJPA shoppingCartRepoJPA;
    private final UserRepoJPA userRepoJPA;
    private final OrderRepoJPA orderRepoJPA;

    public ShoppingCartServiceImpl(ShoppingCartRepoJPA shoppingCartRepoJPA, UserRepoJPA userRepoJPA, OrderRepoJPA orderRepoJPA) {
        this.shoppingCartRepoJPA = shoppingCartRepoJPA;
        this.userRepoJPA = userRepoJPA;
        this.orderRepoJPA = orderRepoJPA;
    }

    @Override
    public List<Order> listAllShoppingCartOrders(Long id) {
        if(this.shoppingCartRepoJPA.findById(id).isPresent()){
            throw new ShoppingCartExp(id);
        }
        return this.shoppingCartRepoJPA.findById(id).get().getOrders();
    }

    @Override
    @Transactional
    public ShoppingCart getActiveUserProduct(Long id) {
        User user=this.userRepoJPA.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        return this.shoppingCartRepoJPA.findByUserAndStatus(user, OrdersStatus.CREATED).orElseGet(()-> {
            ShoppingCart cart=new ShoppingCart(user);
            return this.shoppingCartRepoJPA.save(cart);
        });
    }

    @Override
    @Transactional
    public ShoppingCart addProductToCart(String username, Long orderId) {
        if (!userRepoJPA.findByUsername(username).isPresent()){
            throw new UserWithNameNotFoundException(username);
        }
        User user = userRepoJPA.findByUsername(username).orElseThrow(() -> new UserWithNameNotFoundException(username));
        ShoppingCart orderCart = this.getActiveUserProduct(user.getId());
        Order order = orderRepoJPA.findById(orderId).orElseThrow(() -> new OrderNotFoundExeption(orderId));
        orderCart.getOrders().add(order);
        return this.shoppingCartRepoJPA.save(orderCart);
    }
}
