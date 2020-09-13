package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Orderitems;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Orders;

import java.util.Date;
import java.util.List;

public interface OrderItemsService {

    Orderitems save(Orderitems orders);

    void deleteOrdersItemsById(int id);

    List<Orderitems> getAllOrdersItems();

    Orderitems findOrderById(int id);
}
