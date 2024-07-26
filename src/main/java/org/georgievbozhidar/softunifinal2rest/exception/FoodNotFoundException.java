package org.georgievbozhidar.softunifinal2rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Food not found.")
public class FoodNotFoundException extends RuntimeException {
    public FoodNotFoundException() {
        super();
    }

    public FoodNotFoundException(String message) {
        super(message);
    }

    public FoodNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
