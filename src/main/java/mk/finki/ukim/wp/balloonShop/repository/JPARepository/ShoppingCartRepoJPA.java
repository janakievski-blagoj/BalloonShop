package mk.finki.ukim.wp.balloonShop.repository.JPARepository;

import mk.finki.ukim.wp.balloonShop.model.ShoppingCart;
import mk.finki.ukim.wp.balloonShop.model.User;
import mk.finki.ukim.wp.balloonShop.model.enums.OrdersStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepoJPA extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, OrdersStatus ordersStatus);

}
