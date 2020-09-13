package com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findRoleByName(String name);
}
