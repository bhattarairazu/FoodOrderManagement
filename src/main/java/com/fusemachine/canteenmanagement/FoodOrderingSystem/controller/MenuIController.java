package com.fusemachine.canteenmanagement.FoodOrderingSystem.controller;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Menu;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuIController {
    @Autowired
    private MapValiationError mapValiationError;

    @Autowired
    private FoodService foodService;

    @Autowired
    private GlobalResponseService globalResponse;

    @Autowired
    private MenuService menuService;

    @Autowired
    private DateConversionService dateConversionService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> insertMenu(@Valid @RequestBody Menu menu, BindingResult result){

        ResponseEntity<?> errorMap = mapValiationError.MapValidationError(result);
        if(errorMap!=null) return errorMap;

        Menu menus= menuService.save(menu);
        return new ResponseEntity<>(menus, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteMenuItem(@PathVariable int id){
        Menu menus = menuService.findById(id);
        if(menus ==null)
            return globalResponse.globalResponse("Menu with id "+id+" Not found",HttpStatus.NOT_FOUND.value());

        menuService.deleteById(id);
        return globalResponse.globalResponse("Success",HttpStatus.OK.value());
    }

    @GetMapping("/todays")
    public ResponseEntity<?> getMenuForToday(){
        return new ResponseEntity<>(menuService.findMenuByDate(dateConversionService.getTodaysdate(" 00:00:00.0"),dateConversionService.getTodaysdate(" 23:99:99.0")),HttpStatus.OK);

    }

}
