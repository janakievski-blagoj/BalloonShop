package mk.finki.ukim.wp.balloonShop.service.Impl;

import mk.finki.ukim.wp.balloonShop.model.User;
import mk.finki.ukim.wp.balloonShop.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.wp.balloonShop.model.exceptions.InvalidCredentialException;
import mk.finki.ukim.wp.balloonShop.repository.JPARepository.UserRepoJPA;
import mk.finki.ukim.wp.balloonShop.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepoJPA userRepository;

    public AuthServiceImpl(UserRepoJPA userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidCredentialException::new);
    }

}
