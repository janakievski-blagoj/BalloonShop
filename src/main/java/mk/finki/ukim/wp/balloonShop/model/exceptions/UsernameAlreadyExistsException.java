package mk.finki.ukim.wp.balloonShop.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String username){
        super("User with username: "+ username +" already exists!");
    }
}
