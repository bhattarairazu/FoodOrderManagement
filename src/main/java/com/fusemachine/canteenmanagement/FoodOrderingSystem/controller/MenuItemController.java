package com.fusemachine.canteenmanagement.FoodOrderingSystem.controller;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Food;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.MenuItem;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.FoodService;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.GlobalResponseService;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.MapValiationError;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.MenuItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/menuitem")
public class MenuItemController {
    @Autowired
    private MapValiationError mapValiationError;

    @Autowired
    private FoodService foodService;

    @Autowired
    private GlobalResponseService globalResponse;

    @Autowired
    private MenuItemsService menuItemsService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> insertMenuItem(@Valid @RequestBody MenuItem menuItem, BindingResult result){

        ResponseEntity<?> errorMap = mapValiationError.MapValidationError(result);
        if(errorMap!=null) return errorMap;

        MenuItem menuitems = menuItemsService.save(menuItem);
        return new ResponseEntity<>(menuitems, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteMenuItem(@PathVariable int id){
        MenuItem menuItem = menuItemsService.findById(id);
        if(menuItem ==null)
            return globalResponse.globalResponse("MenuItem with id "+id+" Not found",HttpStatus.NOT_FOUND.value());

        menuItemsService.deleteById(id);
        return globalResponse.globalResponse("Success",HttpStatus.OK.value());
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllMenuItem(){
        return new ResponseEntity<>(menuItemsService.getAllMenuItem(),HttpStatus.OK);
    }

}
