package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Category;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.CategoryRepositoyr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepositoyr categoryRepositoyr;

    @Override
    public Category save(Category category) {
        return categoryRepositoyr.save(category);
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepositoyr.findAll();
    }

    @Override
    public void deleteById(int id) {
        categoryRepositoyr.deleteById(id);

    }

    @Override
    public Category findById(int id) {
        Optional<Category> result = categoryRepositoyr.findById(id);
        Category cat = null;
        if(result.isPresent()){
            cat = result.get();
            return cat;
        }
        return null;
    }
}
