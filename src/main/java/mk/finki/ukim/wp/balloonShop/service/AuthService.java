package mk.finki.ukim.wp.balloonShop.service;

import mk.finki.ukim.wp.balloonShop.model.User;

public interface AuthService {
    User login(String username, String password);
}
