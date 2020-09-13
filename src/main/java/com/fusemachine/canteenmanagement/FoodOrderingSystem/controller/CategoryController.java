package com.fusemachine.canteenmanagement.FoodOrderingSystem.controller;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Category;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.exceptions.GlobalExceptions;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.CategoryService;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.GlobalResponseService;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.MapValiationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MapValiationError mapValiationError;

    @Autowired
    private GlobalResponseService globalResponseService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> postCategory(@Valid @RequestBody Category category, BindingResult result, Principal principal){
        ResponseEntity<?> errorMap = mapValiationError.MapValidationError(result);//validate binding result
        if(errorMap!=null) return errorMap;//return if there is error

        Category categorys = categoryService.save(category);
        return new ResponseEntity<>(categorys, HttpStatus.CREATED);

    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable int id){
        Category cat = categoryService.findById(id);
        if(cat==null)
            throw new GlobalExceptions("Category with id"+id+" Not found");
        categoryService.deleteById(id);
        return globalResponseService.globalResponse("Success",HttpStatus.OK.value());


    }
}
