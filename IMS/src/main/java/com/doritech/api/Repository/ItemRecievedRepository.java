package com.doritech.api.Repository;

import com.doritech.api.Entity.ItemRecievedEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRecievedRepository extends JpaRepository<ItemRecievedEntity, Integer> {
   @Query(
      value = "SELECT * FROM item_recieve WHERE current_price <> item_price AND recieved_date = CURRENT_DATE",
      nativeQuery = true
   )
   List<ItemRecievedEntity> findItemsWithDifferentPricesAndCurrentDate();

   @Query(
      value = "SELECT * FROM item_recieve WHERE recieved_date = CURRENT_DATE",
      nativeQuery = true
   )
   List<ItemRecievedEntity> getAllItemsReceivedToday();
}
