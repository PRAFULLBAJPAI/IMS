package com.doritech.api.Repository;

import com.doritech.api.Entity.ItemMasterEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMasterRepository extends JpaRepository<ItemMasterEntity, String> {
   @Query(
      value = "SELECT * FROM item_master WHERE item_type = 'SUBPRODUCT' OR item_type = 'FINAL PRODUCT';",
      nativeQuery = true
   )
   List<ItemMasterEntity> getAllSubproducts();

   @Query(
      value = "SELECT * FROM item_master WHERE item_code LIKE %:itemCode% AND (item_type = 'SUBPRODUCT' OR item_type = 'FINAL PRODUCT')",
      nativeQuery = true
   )
   List<ItemMasterEntity> getAllSubproductsByPattern(@Param("itemCode") String itemCode);

   Optional<ItemMasterEntity> findByItemCode(String itemCode);

   @Query(
      value = "SELECT * FROM item_master WHERE item_code LIKE %:itemCode%",
      nativeQuery = true
   )
   List<ItemMasterEntity> findByItemCodeContainingPattern(@Param("itemCode") String itemCode);

   @Query(
      value = "SELECT * FROM item_master WHERE item_name LIKE %:itemName%",
      nativeQuery = true
   )
   List<ItemMasterEntity> findByItemNameContainingPattern(@Param("itemName") String itemname);

   Optional<ItemMasterEntity> findByItemName(String itemName);

   List<ItemMasterEntity> findByItemType(String itemType);

   @Query(
      value = "SELECT * FROM item_master WHERE item_type = 'SUBPRODUCT' or item_type = 'FINAL PRODUCT';",
      nativeQuery = true
   )
   List<ItemMasterEntity> findByConsumptionType();

   @Query(
      value = "SELECT count(item_type) FROM item_master;",
      nativeQuery = true
   )
   int getAllItemsCount();

   @Query(
      value = "SELECT count(item_type) FROM item_master WHERE item_type = 'RAW';",
      nativeQuery = true
   )
   int getRawItemsCount();

   @Query(
      value = "SELECT count(item_type) FROM item_master WHERE item_type = 'SUBPRODUCT';",
      nativeQuery = true
   )
   int getSubproductItemsCount();

   @Query(
      value = "SELECT count(item_type) FROM item_master WHERE item_type = 'FINAL PRODUCT';",
      nativeQuery = true
   )
   int getFinalProductItemsCount();

   @Query(
      value = "SELECT * FROM item_master WHERE quantity_in_stock <= minimum_stock",
      nativeQuery = true
   )
   List<ItemMasterEntity> getAllItemsWithStockChange();
}
