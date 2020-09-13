package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Category;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Food;

import java.util.List;

public interface FoodService {

    public Food save(Food food);

    public void deleteById(int id);

    public List<Food> findFoodByCategory(String category);

    public List<Food> getAllFood();

    public Food findById(int id);
}
