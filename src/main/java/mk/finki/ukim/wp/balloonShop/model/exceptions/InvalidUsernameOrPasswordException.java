package mk.finki.ukim.wp.balloonShop.model.exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException{
    public InvalidUsernameOrPasswordException(){
        super("Your username or password is invalid.");
    }
}
