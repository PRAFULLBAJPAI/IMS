package com.doritech.api.Repository;

import com.doritech.api.Entity.MenuMasterEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuMasterRepository extends JpaRepository<MenuMasterEntity, Integer> {

	Optional<MenuMasterEntity> findById(int menuId);
}
