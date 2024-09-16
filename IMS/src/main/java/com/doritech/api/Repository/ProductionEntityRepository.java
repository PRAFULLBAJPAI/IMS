package com.doritech.api.Repository;

import com.doritech.api.Entity.ProductionEntity;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionEntityRepository extends JpaRepository<ProductionEntity, Integer> {
   @Query(
      value = "SELECT * FROM production_entity WHERE production_date BETWEEN STR_TO_DATE(:startDate, '%Y-%m-%d') AND STR_TO_DATE(:endDate, '%Y-%m-%d');",
      nativeQuery = true
   )
   List<ProductionEntity> getAllProductionEntityByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
