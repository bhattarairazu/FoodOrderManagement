package com.fusemachine.canteenmanagement.FoodOrderingSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserExceptions extends RuntimeException {

public UserExceptions(String message){
    super(message);
}

}
