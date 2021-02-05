package mk.finki.ukim.wp.balloonShop.service.Impl;

import mk.finki.ukim.wp.balloonShop.model.User;
import mk.finki.ukim.wp.balloonShop.model.exceptions.*;
import mk.finki.ukim.wp.balloonShop.repository.JPARepository.UserRepoJPA;
import mk.finki.ukim.wp.balloonShop.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepoJPA userRepoJPA;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepoJPA userRepoJPA, PasswordEncoder passwordEncoder) {
        this.userRepoJPA = userRepoJPA;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByName(String name) {
        return this.userRepoJPA.findByUsername(name);
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw  new UserWithNameNotFoundException(username);
        }
        return  userRepoJPA.findByUsernameAndPassword(username,password).orElseThrow(InvalidCredentialException::new);
    }

    @Override
    public User register(String username, String email, String password, String repeatPassword, String name, String surname) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidUsernameOrPasswordException();
        }
        if (email == null || email.isEmpty()){
            throw new InvalidEmailException();
        }
        if (!password.equals(repeatPassword)){
            throw new PasswordsDoNotMatchException();
        }
        if (this.userRepoJPA.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExistsException(username);
        }
        User user = new User(username, email, passwordEncoder.encode(password), name, surname);
        return userRepoJPA.save(user);

    }
}
