package com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.FoodToPrepared;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Integer> {

    @Query(value = "SELECT * FROM order_table WHERE created_at BETWEEN :startDate AND :endDate",nativeQuery = true)
    List<Orders> findOrderForTodays(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
