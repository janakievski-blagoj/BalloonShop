package mk.finki.ukim.wp.balloonShop.model;

import lombok.Data;
import mk.finki.ukim.wp.balloonShop.model.enums.OrdersStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDateTime dateCreated;

    @Enumerated(EnumType.STRING)
    private OrdersStatus status;

    @ManyToMany
    private List<Order> orders;

    public ShoppingCart(){
    }

    public ShoppingCart(User user) {
        this.user = user;
        this.status=OrdersStatus.CREATED;
        this.orders = new ArrayList<>();
        this.dateCreated=LocalDateTime.now();

    }
}
