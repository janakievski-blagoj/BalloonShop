package mk.finki.ukim.wp.balloonShop.model.exceptions;

public class InvalidOrderException extends RuntimeException{
    public InvalidOrderException() {
        super("Invalid attributes!");
    }
}
