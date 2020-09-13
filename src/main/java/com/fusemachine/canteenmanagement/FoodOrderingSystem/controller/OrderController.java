package com.fusemachine.canteenmanagement.FoodOrderingSystem.controller;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Orders;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Role;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.payload.OrderStatusChangeRequest;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.DateConversionService;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.GlobalResponseService;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.MapValiationError;
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
import java.util.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MapValiationError mapValiationError;

    @Autowired
    private GlobalResponseService globalResponse;

    @Autowired
    private DateConversionService dateConversionService;


    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllOrdersForToday(){

        return new ResponseEntity<>(todaysOrder(),HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<?> insertOrder(@Valid @RequestBody Orders orders, BindingResult result){

        ResponseEntity<?> errorMap = mapValiationError.MapValidationError(result);
        if(errorMap!=null) return errorMap;

        Orders order= orderService.save(orders);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteOrder(@PathVariable int id){
        Orders orders = orderService.findOrderById(id);
        if(orders ==null)
            return globalResponse.globalResponse("Orders with id "+id+" Not found",HttpStatus.NOT_FOUND.value());

        orderService.deleteOrdersById(id);
        return globalResponse.globalResponse("Success",HttpStatus.OK.value());
    }



    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasROLE('ROLE_ADMIN')")
    public ResponseEntity<?> updateOrderById(@PathVariable int id, @Valid @RequestBody Orders orders, BindingResult result){

        ResponseEntity<?> errorMap = mapValiationError.MapValidationError(result);
        if(errorMap!=null) return errorMap;
        Orders order = orderService.findOrderById(id);

        if (order==null)
            return globalResponse.globalResponse("Order with id "+id+" Not found",HttpStatus.NOT_FOUND.value());

        orders.setId(id);
        Orders updatedorder = orderService.save(orders);
        return new ResponseEntity<>(updatedorder,HttpStatus.CREATED);
    }
    @GetMapping("/previousdays/")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> previousDayOrder(){
        return new ResponseEntity<>(orderService.getAllOrdersForToday(dateConversionService.getyesterdayDate(" 00:00:00.0"),dateConversionService.getyesterdayDate(" 23:99:99.0")), HttpStatus.OK);
    }

    @GetMapping("/orderbydate/{date}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> specificDateOrder(@PathVariable String date){

        String dateFrom = date + " 00:00:00.0";
        String dateTo = date + " 23:59:59.0";

        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date datefrom = null;
        Date datesTo = null;
        try {
            datefrom = pattern.parse(dateFrom);
            datesTo = pattern.parse(dateTo);
            System.out.print(datefrom + " ");
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(orderService.getAllOrdersForToday(datefrom,datesTo),HttpStatus.OK);
    }

    //getting order by employees
    @GetMapping("/orderbyemployee")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getOrderByEmployee(){
        List<Orders> orderByEmployee = new ArrayList<>();
        List<Orders> allOrder = todaysOrder();
        for(Orders order:allOrder){
            Set<Role> roles = order.getUser().getRoles();
            for(Role rol :roles){
                String rolesname = rol.getName();
                if("ROLE_EMPLOYEE".matches(rolesname)){
                    orderByEmployee.add(order);
                }
            }

        }
        return new ResponseEntity<>(orderByEmployee,HttpStatus.OK);

    }

    private List<Orders> todaysOrder(){
       return orderService.getAllOrdersForToday(dateConversionService.getTodaysdate(" 00:00:00.0"),dateConversionService.getTodaysdate(" 23:99:99.0"));
    }

    @PostMapping("/changeorderstatus")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<?> changeOrderStatus(@Valid @RequestBody OrderStatusChangeRequest newMap,BindingResult result){
        ResponseEntity<?> errorMap = mapValiationError.MapValidationError(result);
        if(errorMap!=null) return errorMap;
        Orders order = orderService.findOrderById(newMap.getId());

        if (order==null)
            return globalResponse.globalResponse("Order with id "+newMap.getId()+" Not found",HttpStatus.NOT_FOUND.value());

        order.setStatus(newMap.getStatus());
        return new ResponseEntity<>(orderService.save(order),HttpStatus.CREATED);

    }

}
