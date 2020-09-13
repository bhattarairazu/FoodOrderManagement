package com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemsRepository extends JpaRepository<MenuItem,Integer> {

}
