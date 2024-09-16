package com.doritech.api.Repository;

import com.doritech.api.Entity.ParamEntity;
import com.doritech.api.Entity.ParamEntityPk;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ParamRepository extends JpaRepository<ParamEntity, ParamEntityPk> {
   @Query(
      value = "select * from param where code = :code and serial = :serial",
      nativeQuery = true
   )
   List<ParamEntity> getParamDataByCodeAndSerial(String code, String serial);

   ParamEntity findByCode(String code);

   ParamEntity getParamByCodeAndSerial(String code, String serial);

   List<ParamEntity> findBySerial(String serial);
}
