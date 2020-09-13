package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Food;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.FoodToPrepared;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.FoodRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.FoodToBePreparedRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.UserRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.exceptions.GlobalExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FoodTobePreparedServiceImpl implements FoodToBePreparedService {

    @Autowired
    private FoodToBePreparedRepository foodToBePreparedRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public FoodToPrepared save(FoodToPrepared food) {

            int quantity=0;
            //checking food if it exists in databse
            Food foods = foodRepository.findFoodByFoodCode(food.getFoodCode());
            if(foods==null)
                throw new GlobalExceptions("Food with code "+food.getFoodCode()+" Not found");

            //cheking food to be prepared if it exists in database
            //if it exist than increae the quantity
           FoodToPrepared foodToPrepared = foodToBePreparedRepository.findFoodToPreparedByFoodCode(food.getFoodCode());
            if(foodToPrepared!=null){
                quantity =foodToPrepared.getQuantity()+food.getQuantity();
            }else{
                food.setFood(foods);
                quantity = food.getQuantity();
                food.setQuantity(quantity);

                return foodToBePreparedRepository.save(food);
            }
//            User user = userRepository.findByUsername(food.getUsername());
//            if(user==null)
//                throw new UserExceptions("User with username "+food.getUsername()+" Not found");
//
//            food.setUser(user);


            //food.setFood(foods);
            //set new quntity to foodtobeprepard
            foodToPrepared.setQuantity(quantity);

            //delete old one for update
            deleteById(food.getId());

           return foodToBePreparedRepository.save(foodToPrepared);
    }

    @Override
    public void deleteById(int id) {
        foodToBePreparedRepository.deleteById(id);

    }

    @Override
    public FoodToPrepared findFoodToBePreparedByFoodCode(String foodCode) {
        FoodToPrepared foodtobeprepared = foodToBePreparedRepository.findFoodToPreparedByFoodCode(foodCode);
        //TO DO
        //return foodToBePreparedRepository.findAllFoodToPreparedByFood(food);
        return foodtobeprepared;
    }

    @Override
    public List<FoodToPrepared> getAllFoodToBePreparedToday(Date startDate, Date endDate) {

        return foodToBePreparedRepository.findFoodForTodays(startDate,endDate);
    }

    @Override
    public FoodToPrepared findById(int id) {
        Optional<FoodToPrepared> result = foodToBePreparedRepository.findById(id);
        FoodToPrepared foodToPrepared = null;
        if(result.isPresent()){
            foodToPrepared = result.get();
            return foodToPrepared;
        }
        return null;
    }

    @Override
    public List<FoodToPrepared> findPopularFoodToPrepared() {
        return foodToBePreparedRepository.findPopularFoodToPrepared();
    }
}
