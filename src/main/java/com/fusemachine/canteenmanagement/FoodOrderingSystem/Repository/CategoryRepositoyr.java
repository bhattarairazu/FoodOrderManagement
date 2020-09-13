package com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositoyr extends JpaRepository<Category,Integer> {
    public Category findByName(String name);
}
