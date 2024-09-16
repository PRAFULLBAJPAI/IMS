package com.doritech.api.Repository;

import com.doritech.api.Entity.GroupMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMasterRepository extends JpaRepository<GroupMasterEntity, String> {
   GroupMasterEntity findByGroupId(String groupId);
}
