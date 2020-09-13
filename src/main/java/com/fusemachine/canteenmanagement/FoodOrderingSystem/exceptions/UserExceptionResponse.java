package com.fusemachine.canteenmanagement.FoodOrderingSystem.exceptions;

public class UserExceptionResponse {
    private String message;

    public UserExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


