package com.minty.salesinventorymgt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class BadRequestException extends RuntimeException{

    private String message;

    public BadRequestException(){
        super("Invalid Request Data");
    }

    public BadRequestException(String message){
        super(message);
        this.message = message;
    }

}
