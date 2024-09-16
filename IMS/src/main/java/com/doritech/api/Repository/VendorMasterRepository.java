package com.doritech.api.Repository;

import com.doritech.api.Entity.VendorMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorMasterRepository extends JpaRepository<VendorMasterEntity, String> {
}
