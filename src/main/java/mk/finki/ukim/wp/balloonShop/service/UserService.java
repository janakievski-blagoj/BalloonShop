package mk.finki.ukim.wp.balloonShop.service;

import mk.finki.ukim.wp.balloonShop.model.User;

import java.util.Optional;


public interface UserService {
    Optional<User> findByName(String name);
    User login(String username,String password);
    User register(String username, String email, String password, String repeatPassword, String name, String surname);
}
