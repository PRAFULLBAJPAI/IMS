package com.doritech.api.Repository;

import com.doritech.api.Entity.MenuAccessEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuAccessRepository extends JpaRepository<MenuAccessEntity, Integer> {
   MenuAccessEntity findByRoleId(int userRoleId);

   MenuAccessEntity findByRoleNameAndMenuId(String roleName, int menuId);

   List<MenuAccessEntity> findByRoleName(String empRole);
}
