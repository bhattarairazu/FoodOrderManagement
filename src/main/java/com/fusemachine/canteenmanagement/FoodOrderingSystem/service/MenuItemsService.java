package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Food;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Menu;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.MenuItem;

import java.util.Date;
import java.util.List;

public interface MenuItemsService {
    public MenuItem save(MenuItem menuItem);

    public void deleteById(int id);

    public List<MenuItem> findMenuItemsByDate(Date date);

    public List<MenuItem> getAllMenuItem();

    public MenuItem findById(int id);
}
