package com.fusemachine.canteenmanagement.FoodOrderingSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalExceptions extends RuntimeException {

public GlobalExceptions(String message){
    super(message);
}

}
