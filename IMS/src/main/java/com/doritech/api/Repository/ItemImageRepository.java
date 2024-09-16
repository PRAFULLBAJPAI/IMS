package com.doritech.api.Repository;

import com.doritech.api.Entity.ItemImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemImageRepository extends JpaRepository<ItemImageEntity, String> {
}
