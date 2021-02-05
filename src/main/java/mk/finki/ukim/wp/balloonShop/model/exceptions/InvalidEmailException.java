package mk.finki.ukim.wp.balloonShop.model.exceptions;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(){
        super("Invalid Email");
    }
}
