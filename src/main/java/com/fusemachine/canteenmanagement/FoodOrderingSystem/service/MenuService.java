package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Food;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Menu;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.MenuItem;

import java.util.Date;
import java.util.List;

public interface MenuService {

    public Menu save(Menu menu);

    public void deleteById(int id);

    public Menu findMenuByDate(Date startDate,Date endDate);

    public List<Menu> getAllMenu();

    public Menu findById(int id);
}
