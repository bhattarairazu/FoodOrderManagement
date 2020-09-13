package com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Category;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Integer> {
    public List<Food> findAllFoodByCategory(Category category);

    public Food findFoodByFoodCode(String code);

}
