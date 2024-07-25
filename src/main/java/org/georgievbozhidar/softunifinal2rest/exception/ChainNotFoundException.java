package org.georgievbozhidar.softunifinal2rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Chain not found.")
public class ChainNotFoundException extends RuntimeException {
    public ChainNotFoundException(){
        super();
    }

    public ChainNotFoundException(String message){
        super(message);
    }

    public ChainNotFoundException(String message, Throwable err){
        super(message, err);
    }
}
