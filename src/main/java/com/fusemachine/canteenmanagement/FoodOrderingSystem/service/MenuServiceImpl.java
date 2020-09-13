package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Menu;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.MenuItemsRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.MenuRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.exceptions.UserExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemsRepository menuItemsRepository;

    @Override
    public Menu save(Menu menu) {

        System.out.println("New date time "+ LocalDate.now());
        String dateFrom = LocalDate.now() + " 00:00:00.0";
        String dateTo = LocalDate.now()+" 23:99:99.0";
        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date datesFrom=null;
        Date datesTo =null;
        try {
            datesFrom =  pattern.parse(dateFrom);
            datesTo = pattern.parse(dateTo);
            System.out.print(datesFrom+" "+datesTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Menu menus = menuRepository.findTodaysMenut(datesFrom,datesTo);

        if(menus!=null)
            throw new UserExceptions("Menu already Created.Now Add items to menu.Cann't create multiple menu in a single day");


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
