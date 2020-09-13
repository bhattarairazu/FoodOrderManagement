package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Category;

import java.util.List;

public interface CategoryService {

    public Category save(Category category);

    public List<Category> findAllCategory();

    public void deleteById(int id);

    Category findById(int id);




}
