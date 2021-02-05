package mk.finki.ukim.wp.balloonShop.repository.JPARepository;

import mk.finki.ukim.wp.balloonShop.model.Order;
import mk.finki.ukim.wp.balloonShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepoJPA extends JpaRepository<Order,Long> {
    List<Order> findAllByUser(User user);
}
