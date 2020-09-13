package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Category;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Food;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.CategoryRepositoyr;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.FoodRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.exceptions.UserExceptions;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private CategoryRepositoyr categoryRepositoyr;

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food save(Food food) {

        Category category = categoryRepositoyr.findByName(food.getCategoryName());
        if(category==null){
            throw new UserExceptions("Category with name "+category.getName()+" Not found.Please Insert valid category Name");
        }
        Food foods = foodRepository.findFoodByFoodCode(food.getFoodCode());
        if(foods!=null)
            throw new UserExceptions("Food with code "+food.getFoodCode()+" Already Exists.Please select new food code");
        food.setCategory(category);


        return foodRepository.save(food);
    }

    @Override
    public void deleteById(int id) {
        foodRepository.deleteById(id);

    }

    @Override
    public List<Food> findFoodByCategory(String category) {
        Category categorys = categoryRepositoyr.findByName(category);
        List<Food> allFood = foodRepository.findAllFoodByCategory(categorys);

        return allFood;
    }

    @Override
    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    @Override
    public Food findById(int id) {
        Optional<Food> result = foodRepository.findById(id);
        Food food = null;
        if(result.isPresent()){
            food = result.get();
            return food;
        }
        return null;
    }
}
