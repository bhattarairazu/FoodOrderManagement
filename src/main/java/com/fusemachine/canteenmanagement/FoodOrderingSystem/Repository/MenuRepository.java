package com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Integer> {

    @Query(value = "SELECT * FROM menu WHERE created_at BETWEEN :startDate AND :endDate",nativeQuery = true)
    public Menu findTodaysMenut(@Param("startDate")Date startDate,@Param("endDate") Date endDate);

}
