package mk.finki.ukim.wp.balloonShop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ManufacturerNotFoundException extends RuntimeException{

    public ManufacturerNotFoundException(Long id) {
        super(String.format("Manufacturer with id: %d not found!", id));
    }
}
