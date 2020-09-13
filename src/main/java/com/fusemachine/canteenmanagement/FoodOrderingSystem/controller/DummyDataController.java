package com.fusemachine.canteenmanagement.FoodOrderingSystem.controller;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.*;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.RoleRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class DummyDataController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuItemsService menuItemsService;

    @Autowired
    private FoodToBePreparedService foodToBePreparedService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemsService orderItemsService;

    @Autowired
    private RoleRepository roleRepository;

    //method for creating dummy data in database
    @GetMapping("/")
    public ResponseEntity<?> dummyData(){
        String [] roleslist = {"ROLE_ADMIN","ROLE_EMPLOYEE"};

        //initially adding roles to the database
        for(String str:roleslist){
            if(roleRepository.findRoleByName(str)==null) {
                Role role = new Role();
                role.setName(str);
                roleRepository.save(role);
            }
        }
        //creating User now
       User testuser =  creatingUserNow();

        //Adding Food Category
       Category cat =  addingFoodCategory();

        //Creating Food Items
       Food fod =  creatingFoodItems();

        //Creating Menu
        Menu menu = creatingMenu();

        //Creating menu items
        MenuItem menuItem1 = creatingMenuItems(menu);

        //Creating Food to be prepared
        FoodToPrepared foodToPrepared1 = foodTobePrepared();

        //creating new order
        Orders order1 = creatingNewOrder(testuser);

        //creating order items
        Orderitems orderitems1 = creatingOrderItems(menuItem1, order1);


        Map<String,Object> map = new HashMap<>();
        map.put("message","Dummy Data Created");
        map.put("User",testuser);
        map.put("Category",cat);
        map.put("Food",fod);
        map.put("Menu",menu);
        map.put("MenuItems",menuItem1);
        map.put("FoodToPrepared",foodToPrepared1);
        map.put("Order",order1);
        map.put("Orderitem",orderitems1);

        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }

    private Orderitems creatingOrderItems(MenuItem menuItem1, Orders order1) {
        Orderitems orderitems = new Orderitems();
        orderitems.setQuantity(1);
        orderitems.setPrice(333);
        orderitems.setMenuItemsId(menuItem1.getId());
        orderitems.setOrdersId(order1.getId());
        return orderItemsService.save(orderitems);
        //return orderitems;
    }

    private Orders creatingNewOrder(User testuser) {
        Orders order = new Orders();
        order.setOrderNumber("#111");
        order.setOrderSchedule(false);
        order.setRemarks("TESTREMARKS");
        order.setUsername(testuser.getUsername());
        return orderService.save(order);

    }

    private FoodToPrepared foodTobePrepared() {
        FoodToPrepared foodToPrepared = new FoodToPrepared();
        foodToPrepared.setFoodCode("TESTCODE");
        foodToPrepared.setQuantity(1);
        return foodToBePreparedService.save(foodToPrepared);
           }

    private MenuItem creatingMenuItems(Menu menu) {
        MenuItem menuItem = new MenuItem();
        menuItem.setMenuId(menu.getId());
        menuItem.setFoodCode("TESTCODE");
        menuItem.setPrice(1111.11f);

        return menuItemsService.save(menuItem);
    }

    private Menu creatingMenu() {
        Menu menu = new Menu();
        menu.setRemarks("TESTMENU");
        menu.setDescription("TEST_MENU_DESCRIPTION");
        return menuService.save(menu);
    }

    private Food creatingFoodItems() {
        Food food = new Food();
        food.setName("TESTMOMO");
        food.setCategoryName("TestCategory");
        food.setPrice(111.111f);
        food.setDiscount(1.11f);
        food.setFoodCode("TESTCODE");
        return foodService.save(food);
    }

    private Category addingFoodCategory() {
        Category category = new Category();
        category.setName("TestCategory");
        return categoryService.save(category);
    }

    private User creatingUserNow() {
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Testlastname");
        user.setUsername("TestUser");
        user.setEmail("testuser@test.com");
        user.setPhone("1111111111");
        user.setDesignation("BackendTest");
        user.setEmployeeType("FULLTIMETEST");
        user.setPassword("testpassword");
        user.setJoinedDate(new Date());
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return userService.save(user);
    }
}
