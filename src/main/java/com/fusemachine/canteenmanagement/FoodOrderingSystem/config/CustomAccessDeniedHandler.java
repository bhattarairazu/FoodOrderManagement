package com.fusemachine.canteenmanagement.FoodOrderingSystem.config;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.exceptions.CustomAccessDeniedExceptionResponse;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.exceptions.InvalidLoginResponse;
import com.google.gson.Gson;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
    *Custom Access Denied Error Handler
     * It handles the error and show the custom error message to the user
     */
    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException exc) throws IOException, ServletException {

        CustomAccessDeniedExceptionResponse loginResponse = new CustomAccessDeniedExceptionResponse();
        String jsonLoginResponse = new Gson().toJson(loginResponse);

        response.setContentType("application/json");
        response.setStatus(403);
        response.getWriter().print(jsonLoginResponse);
    }
}

