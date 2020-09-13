package com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository;


import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Orderitems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<Orderitems,Integer> {


}
