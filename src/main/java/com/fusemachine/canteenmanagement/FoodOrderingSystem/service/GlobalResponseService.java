package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.response.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalResponseService {
	
	public ResponseEntity<GlobalResponse> globalResponse(String message, int statuscode){
		GlobalResponse respone = new GlobalResponse(message, statuscode, System.currentTimeMillis());
		return ResponseEntity.ok(respone);
	}




}
