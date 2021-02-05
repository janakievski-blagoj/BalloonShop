package mk.finki.ukim.wp.balloonShop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "shop_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    private String name;

    private String surname;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;

    public User(){
    }

    public User(String username, String email, String password, String name, String surname){
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public User( String username, String email, String password, String name, String surname, List<ShoppingCart> carts) {
        this.username = username;
        this.email=email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.carts = carts;
    }
}
