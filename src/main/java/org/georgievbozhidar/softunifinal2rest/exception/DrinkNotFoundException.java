package org.georgievbozhidar.softunifinal2rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Drink not found.")
public class DrinkNotFoundException extends RuntimeException {
    public DrinkNotFoundException() {
        super();
    }

    public DrinkNotFoundException(String message) {
        super(message);
    }

    public DrinkNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
