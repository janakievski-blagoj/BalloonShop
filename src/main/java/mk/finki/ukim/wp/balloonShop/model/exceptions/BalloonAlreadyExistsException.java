package mk.finki.ukim.wp.balloonShop.model.exceptions;

public class BalloonAlreadyExistsException extends RuntimeException{

    public BalloonAlreadyExistsException(String message) {
        super(String.format("Balloon with name: %s already exists!!", message));
    }
}
