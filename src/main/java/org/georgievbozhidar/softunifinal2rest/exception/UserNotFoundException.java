package org.georgievbozhidar.softunifinal2rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found.")
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super();
    }

    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(String message, Throwable err){
        super(message, err);
    }
}
