package com.minty.salesinventorymgt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class NotFoundException extends RuntimeException{

    private String message;

    public NotFoundException(){
        super("Entity Not Found");
    }

    public NotFoundException(String message){
        super(message);
        this.message = message;
    }

}
