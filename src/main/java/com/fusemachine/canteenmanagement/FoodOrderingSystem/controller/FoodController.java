package com.fusemachine.canteenmanagement.FoodOrderingSystem.controller;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Food;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.response.GlobalResponse;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.FoodService;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.GlobalResponseService;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.MapValiationError;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/food")
public class FoodController {
    @Autowired
    private MapValiationError mapValiationError;

    @Autowired
    private FoodService foodService;

    @Autowired
    private GlobalResponseService globalResponse;

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> insertFoodItem(@Valid @RequestBody Food food, BindingResult result, Principal principal){
        ResponseEntity<?> errorMap = mapValiationError.MapValidationError(result);
        if(errorMap!=null) return errorMap;

        Food foods = foodService.save(food);
        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllFood(){
        List<Food> allFoods = foodService.getAllFood();
        return new ResponseEntity<>(allFoods,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFoodById(@PathVariable int id){
        Food foods = foodService.findById(id);
        if(foods == null){
            return globalResponse.globalResponse("Food with id "+id+" Not found",HttpStatus.NOT_FOUND.value());
        }
        return new ResponseEntity<>(foods,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteFoodById(@PathVariable int id){

        if(foodService.findById(id) == null)
            return globalResponse.globalResponse("Food with id "+id+" Not found",HttpStatus.NOT_FOUND.value());


        foodService.deleteById(id);
        return globalResponse.globalResponse("Success",HttpStatus.OK.value());

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateFoodById(@PathVariable int id,@Valid @RequestBody Food food,BindingResult result){

        ResponseEntity<?> errorMap = mapValiationError.MapValidationError(result);
        if(errorMap!=null) return errorMap;
        Food newFood = foodService.findById(id);

        if (newFood==null)
            return globalResponse.globalResponse("Food with id "+id+" Not found",HttpStatus.NOT_FOUND.value());

        food.setId(id);
        Food foods = foodService.save(food);
        return new ResponseEntity<>(foods,HttpStatus.CREATED);
    }
}
