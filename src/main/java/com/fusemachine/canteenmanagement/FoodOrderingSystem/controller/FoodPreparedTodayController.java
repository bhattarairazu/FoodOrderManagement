package com.fusemachine.canteenmanagement.FoodOrderingSystem.controller;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.FoodToPrepared;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/foodprepared")
public class FoodPreparedTodayController {

    @Autowired
    private FoodToBePreparedService foodToBePreparedService;

    @Autowired
    private MapValiationError mapValiationError;

    @Autowired
    private GlobalResponseService globalResponse;

    @Autowired
    private DateConversionService dateConversionService;

    @GetMapping("/todays")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllFoodTobePreparedForToday(){
        return new ResponseEntity<>(foodToBePreparedService.getAllFoodToBePreparedToday(dateConversionService.getTodaysdate(" 00:00:00.0"),dateConversionService.getTodaysdate(" 23:99:99.0")), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<?> insertFoodTobePrepared(@Valid @RequestBody FoodToPrepared foodToPrepared, BindingResult result){

        ResponseEntity<?> errorMap = mapValiationError.MapValidationError(result);
        if(errorMap!=null) return errorMap;

        FoodToPrepared foodToPrepared1= foodToBePreparedService.save(foodToPrepared);
        return new ResponseEntity<>(foodToPrepared1, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteOrder(@PathVariable int id){
        FoodToPrepared orders = foodToBePreparedService.findById(id);
        if(orders ==null)
            return globalResponse.globalResponse("Food not found to be prepared  with id "+id+" Not found",HttpStatus.NOT_FOUND.value());

        foodToBePreparedService.deleteById(id);
        return globalResponse.globalResponse("Success",HttpStatus.OK.value());
    }



    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateFoodToBePreparedById(@PathVariable int id, @Valid @RequestBody FoodToPrepared foodToPrepared, BindingResult result){

        ResponseEntity<?> errorMap = mapValiationError.MapValidationError(result);
        if(errorMap!=null) return errorMap;
        FoodToPrepared foodToPrepared1 = foodToBePreparedService.findById(id);

        if (foodToPrepared1==null)
            return globalResponse.globalResponse("Food to prepare  with id "+id+" Not found",HttpStatus.NOT_FOUND.value());

        foodToPrepared.setId(id);
        FoodToPrepared updatefoodtoprepare = foodToBePreparedService.save(foodToPrepared);
        return new ResponseEntity<>(updatefoodtoprepare,HttpStatus.CREATED);
    }

    @GetMapping("/popularfood")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findPopularFood(){
        return new ResponseEntity<>(foodToBePreparedService.findPopularFoodToPrepared(),HttpStatus.OK);
    }

}
