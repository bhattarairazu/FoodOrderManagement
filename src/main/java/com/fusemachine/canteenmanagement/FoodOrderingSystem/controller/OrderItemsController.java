package com.fusemachine.canteenmanagement.FoodOrderingSystem.controller;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Orderitems;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Orders;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.GlobalResponseService;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.MapValiationError;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.OrderItemsService;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/orderitems")
public class OrderItemsController {

    @Autowired
    private OrderItemsService orderService;

    @Autowired
    private MapValiationError mapValiationError;

    @Autowired
    private GlobalResponseService globalResponse;



    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllOrdersItems(){

        return new ResponseEntity<>(orderService.getAllOrdersItems(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> insertOrderItems(@Valid @RequestBody Orderitems orders, BindingResult result){

        ResponseEntity<?> errorMap = mapValiationError.MapValidationError(result);
        if(errorMap!=null) return errorMap;

        Orderitems order= orderService.save(orders);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteOrderitems(@PathVariable int id){
        Orderitems orders = orderService.findOrderById(id);
        if(orders ==null)
            return globalResponse.globalResponse("OrderItems with id "+id+" Not found",HttpStatus.NOT_FOUND.value());

        orderService.deleteOrdersItemsById(id);
        return globalResponse.globalResponse("Success",HttpStatus.OK.value());
    }


}
