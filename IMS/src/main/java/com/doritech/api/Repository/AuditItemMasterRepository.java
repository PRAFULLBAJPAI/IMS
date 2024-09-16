package com.doritech.api.Repository;

import com.doritech.api.Entity.AuditItemMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditItemMasterRepository extends JpaRepository<AuditItemMasterEntity, Integer> {
}
