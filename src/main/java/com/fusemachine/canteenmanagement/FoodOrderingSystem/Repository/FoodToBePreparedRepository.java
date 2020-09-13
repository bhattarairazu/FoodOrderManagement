package com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository;


import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Food;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.FoodToPrepared;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FoodToBePreparedRepository extends JpaRepository<FoodToPrepared,Integer> {

    @Query(value = "SELECT * FROM food_tobe_prepared WHERE created_at BETWEEN :startDate AND :endDate",nativeQuery = true)
    List<FoodToPrepared> findFoodForTodays(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    FoodToPrepared findFoodToPreparedByFood(Food food);

    FoodToPrepared findFoodToPreparedByFoodCode(String code);

    @Query(value = "SELECT * from food_tobe_prepared ORDER BY quantity DESC LIMIT 5",nativeQuery = true)
    List<FoodToPrepared> findPopularFoodToPrepared();



}
