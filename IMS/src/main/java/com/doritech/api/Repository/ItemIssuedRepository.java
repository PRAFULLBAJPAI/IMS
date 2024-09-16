package com.doritech.api.Repository;

import com.doritech.api.Entity.ItemIssuedEntity;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemIssuedRepository extends JpaRepository<ItemIssuedEntity, Integer> {
   @Query(
      value = "SELECT * FROM item_issued WHERE issue_date = :issueDate",
      nativeQuery = true
   )
   List<ItemIssuedEntity> getByIssueDate(Date issueDate);

   @Query(
      value = "SELECT * FROM item_issued WHERE issue_date BETWEEN :startDate AND :endDate",
      nativeQuery = true
   )
   List<ItemIssuedEntity> getIssuedItemsByDateRange(Date startDate, Date endDate);

   ItemIssuedEntity findByItemIssueId(int itemIssueId);

   @Query(
      value = "SELECT * FROM item_issued WHERE item_code = :itemCode",
      nativeQuery = true
   )
   List<ItemIssuedEntity> getIssuedEntities(String itemCode);

   @Query(
      value = "SELECT * FROM item_issued WHERE product_code = :productCode",
      nativeQuery = true
   )
   List<ItemIssuedEntity> findByProductCode(@Param("productCode") String productCode);

   @Query(
      value = "SELECT * FROM item_issued WHERE item_code LIKE %:itemCode%",
      nativeQuery = true
   )
   List<ItemIssuedEntity> getIssuedEntitiesByPattern(String itemCode);

   @Query(
      value = "SELECT * FROM item_issued WHERE  product_name LIKE %:subProductName%",
      nativeQuery = true
   )
   List<ItemIssuedEntity> findByProductName(String subProductName);

   @Query(
      value = "SELECT DISTINCT(product_code) FROM item_issued WHERE product_code LIKE %:productCode%",
      nativeQuery = true
   )
   List<String> findAllProductCodes(String productCode);

   @Query(
      value = "SELECT DISTINCT(product_name) FROM item_issued WHERE product_name LIKE %:subProductName%",
      nativeQuery = true
   )
   List<String> findAllProductNames(String subProductName);

   @Query(
      value = "SELECT * FROM item_issued WHERE product_code = :productCode",
      nativeQuery = true
   )
   List<ItemIssuedEntity> findProductDetailsByProductCode(String productCode);

   @Query(
      value = "SELECT * FROM item_issued WHERE product_code = :productCode AND item_code = :itemCode",
      nativeQuery = true
   )
   ItemIssuedEntity getByProductCodeAndItemCode(@Param("productCode") String productCode, @Param("itemCode") String itemCode);
}
