package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Orders;

import java.util.Date;
import java.util.List;

public interface OrderService {

    Orders save(Orders orders);

    void deleteOrdersById(int id);

    List<Orders> getAllOrdersForToday(Date startDate, Date endDate);

    Orders findOrderById(int id);

}
