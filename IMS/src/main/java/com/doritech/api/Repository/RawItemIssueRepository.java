package com.doritech.api.Repository;

import com.doritech.api.Entity.RawItemsIssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawItemIssueRepository extends JpaRepository<RawItemsIssueEntity, Integer> {
}
