package com.doritech.api.Repository;

import com.doritech.api.Entity.HsnMasterEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HsnMasterRepository extends JpaRepository<HsnMasterEntity, String> {
   List<HsnMasterEntity> findAll();
}
