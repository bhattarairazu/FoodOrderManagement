package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Food;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Menu;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.MenuItem;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.FoodRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.MenuItemsRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.MenuRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.exceptions.UserExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MenuItemServiceImpl implements MenuItemsService {

    @Autowired
    private MenuItemsRepository menuItemsRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private MenuService menuService;

    @Override
    public MenuItem save(MenuItem menuItem) {
        int id = menuItem.getMenuId();
        Menu menu = menuService.findById(id);
        if(menu==null)
            throw new UserExceptions("Menu with id "+id+" Not found");

        Food food = foodRepository.findFoodByFoodCode(menuItem.getFoodCode());
        if(food==null)
            throw new UserExceptions("Food with code "+menuItem.getFoodCode()+" Not found");

        menuItem.setFood(food);
        menuItem.setMenu(menu);
        return menuItemsRepository.save(menuItem);

    }

    @Override
    public void deleteById(int id) {
        menuItemsRepository.deleteById(id);

    }

    @Override
    public List<MenuItem> findMenuItemsByDate(Date date) {
        return null;
    }

    @Override
    public List<MenuItem> getAllMenuItem() {

        return menuItemsRepository.findAll();
    }

    @Override
    public MenuItem findById(int id) {
        Optional<MenuItem> result = menuItemsRepository.findById(id);
        MenuItem menuItem = null;
        if(result.isPresent()){
            menuItem = result.get();
            return menuItem;
        }
        return null;
    }
}
