package mk.finki.ukim.wp.balloonShop.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException(){
        super("The passwords do not match!");
    }
}

