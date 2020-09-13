package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.MenuItem;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Orderitems;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Orders;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.User;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.MenuItemsRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.OrderItemsRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.OrderRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.UserRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.exceptions.UserExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private MenuItemsService menuItemsService;

    @Override
    public Orders save(Orders orders) {
        //if(orders.getId()==0){
            System.out.println("Order date"+orders.getOrderScheduleDate()+" orderschedule "+orders.isOrderSchedule());
            User user = userRepository.findByUsername(orders.getUsername());
            if(user==null)
                throw new UserExceptions("User with username "+orders.getUsername()+" Not found");
            if(orders.isOrderSchedule()){
                if(orders.getOrderScheduleDate()==null)
                    throw new UserExceptions("Schedule order should include date and time");
            }
            orders.setUser(user);
            if(orders.getId()==0){
                orders.setStatus("PENDING");
            }else{
                orders.setStatus(findOrderById(orders.getId()).getStatus());
                orders.setCreatedAt(findOrderById(orders.getId()).getCreatedAt());
            }


           return orderRepository.save(orders);

    }

    @Override
    public void deleteOrdersById(int id) {
        orderRepository.deleteById(id);

    }

    @Override
    public List<Orders> getAllOrdersForToday(Date startDate, Date endDate) {

        return orderRepository.findOrderForTodays(startDate,endDate);
    }

    @Override
    public Orders findOrderById(int id) {
        Optional<Orders> result = orderRepository.findById(id);
        Orders order = null;
        if(result.isPresent()){
            order = result.get();
            return order;
        }
        return null;
    }

    public void changeOrderStatus(int id,String status){
        Orders orders = findOrderById(id);
        orders.setStatus(status);
        orderRepository.save(orders);

    }
}
