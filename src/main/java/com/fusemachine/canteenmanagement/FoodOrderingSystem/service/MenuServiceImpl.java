package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Menu;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.MenuItemsRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.MenuRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.exceptions.GlobalExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemsRepository menuItemsRepository;

    @Autowired
    private DateConversionService dateConversionService;
    @Override
    public Menu save(Menu menu) {

        Menu menus = menuRepository.findTodaysMenut(dateConversionService.getTodaysdate(" 00:00:00.0"),dateConversionService.getTodaysdate(" 23:59:59.0"));

        if(menus!=null)
            throw new GlobalExceptions("Menu already Created.Now Add items to menu.Cann't create multiple menu in a single day");


        return menuRepository.save(menu);

    }

    @Override
    public void deleteById(int id) {
        menuRepository.deleteById(id);

    }

    @Override
    public Menu findMenuByDate(Date startDdate,Date endDate) {
        Menu menus = menuRepository.findTodaysMenut(startDdate,endDate);
        if(menus == null)
            return null;

        return menus;
    }

    @Override
    public List<Menu> getAllMenu() {
        return menuRepository.findAll();
    }

    @Override
    public Menu findById(int id) {
        Optional<Menu> result = menuRepository.findById(id);
        Menu menu = null;
        if(result.isPresent()){
            menu = result.get();
            return menu;
        }
        return null;
    }
}
