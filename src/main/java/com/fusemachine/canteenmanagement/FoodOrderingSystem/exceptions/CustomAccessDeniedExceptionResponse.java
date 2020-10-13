package com.fusemachine.canteenmanagement.FoodOrderingSystem.exceptions;

public class CustomAccessDeniedExceptionResponse {
    private String message;

    public CustomAccessDeniedExceptionResponse() {
        this.message = "Unauthorized access.You do not have permission to access this resource.";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
