package mk.finki.ukim.wp.balloonShop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundExeption  extends  RuntimeException{

    public OrderNotFoundExeption(Long id) {
        super(String.format("Order with id: %d not found!", id));
    }
}

