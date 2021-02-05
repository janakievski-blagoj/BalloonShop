package mk.finki.ukim.wp.balloonShop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidCredentialException extends RuntimeException {
    public InvalidCredentialException(){
        super("Invalid user credentials exception");
    }
}
