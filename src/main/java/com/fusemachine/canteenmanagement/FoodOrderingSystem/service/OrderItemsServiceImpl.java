package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.MenuItem;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Orderitems;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Orders;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.OrderItemsRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.OrderRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.exceptions.UserExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemsServiceImpl implements OrderItemsService{

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private MenuItemsService menuItemsService;

    @Autowired
    private OrderService orderService;

    @Override
    public Orderitems save(Orderitems orders) {
            Orders order = orderService.findOrderById(orders.getOrdersId());
            if(order==null)
                throw new UserExceptions("Order with id "+orders.getOrdersId()+" not found");

            double totalOrderPrice = order.getGrandTotal();
            MenuItem menuitem = menuItemsService.findById(orders.getMenuItemsId());
            if(menuitem!=null){
                orders.setMenuitem(menuitem);
            }else{
                throw new UserExceptions("Cann't create order without proper menu item");
            }
            if(orders.getQuantity()==0){
                throw new UserExceptions("There is no any order.Please order atleast one item");
            }
            if(orders.getPrice()==0){
                throw new UserExceptions("Price cannot be set to 0");
            }
            double totalprice = (double) orders.getQuantity() * orders.getPrice();
            orders.setTotalPrice(totalprice);
            totalOrderPrice+=totalprice;

            order.setGrandTotal((float)totalOrderPrice);
            orders.setOrderstable(order);


        return orderItemsRepository.save(orders);

    }

    @Override
    public void deleteOrdersItemsById(int id) {
        orderItemsRepository.deleteById(id);

    }

    @Override
    public List<Orderitems> getAllOrdersItems() {

        return orderItemsRepository.findAll();
    }

    @Override
    public Orderitems findOrderById(int id) {
        Optional<Orderitems> result = orderItemsRepository.findById(id);
        Orderitems orderitems = null;
        if(result.isPresent()){
            orderitems = result.get();
            return orderitems;
        }
        return null;
    }
}
