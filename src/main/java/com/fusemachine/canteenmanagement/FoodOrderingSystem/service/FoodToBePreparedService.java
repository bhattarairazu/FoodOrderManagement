package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Food;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.FoodToPrepared;

import java.util.Date;
import java.util.List;

public interface FoodToBePreparedService {

    public FoodToPrepared save(FoodToPrepared food);

    public void deleteById(int id);

    public FoodToPrepared findFoodToBePreparedByFoodCode(String foodCode);

    public List<FoodToPrepared> getAllFoodToBePreparedToday(Date startDate, Date endDate);

    public FoodToPrepared findById(int id);

    List<FoodToPrepared> findPopularFoodToPrepared();
}
