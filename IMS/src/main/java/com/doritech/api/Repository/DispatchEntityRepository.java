package com.doritech.api.Repository;

import com.doritech.api.Entity.DispatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatchEntityRepository extends JpaRepository<DispatchEntity, Integer> {
}
