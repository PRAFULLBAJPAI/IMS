package com.doritech.api.Repository;

import com.doritech.api.Entity.BranchMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchMasterRepository extends JpaRepository<BranchMasterEntity, String> {
}
