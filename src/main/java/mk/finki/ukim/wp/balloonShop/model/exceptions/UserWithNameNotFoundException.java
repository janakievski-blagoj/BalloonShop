package mk.finki.ukim.wp.balloonShop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserWithNameNotFoundException extends RuntimeException{
    public UserWithNameNotFoundException(String name){
        super(String.format("User with name: %s not found!", name));
    }
}
