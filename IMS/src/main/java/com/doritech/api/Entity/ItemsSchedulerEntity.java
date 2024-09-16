package com.doritech.api.Entity;

import com.doritech.api.Repository.ItemMasterRepository;
import com.doritech.api.Repository.ItemRecievedRepository;
import com.doritech.api.Service.MailService;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ItemsSchedulerEntity {
   private static final Logger logger = LoggerFactory.getLogger(ItemsSchedulerEntity.class);
   @Autowired
   private ItemRecievedRepository itemRecievedRepository;
   @Autowired
   ItemMasterRepository itemMasterRepository;
   @Autowired
   private MailService mailService;

   @Transactional
   @Scheduled(
      cron = "0 00 18 * * *"
   )
   public void sendPriceUpdateAlerts() throws Exception {
      try {
         List<ItemRecievedEntity> list = this.itemRecievedRepository.findItemsWithDifferentPricesAndCurrentDate();
         if (list != null) {
            this.mailService.sendPriceUpdateAlerts(list);
         } else {
            logger.info("No data to fetch");
         }

      } catch (Exception var2) {
         var2.printStackTrace();
         logger.error("Error fetching data", var2);
         throw new RuntimeException("Email send failed: " + var2.getLocalizedMessage());
      }
   }

   @Transactional
   @Scheduled(
      cron = "0 00 18 * * *"
   )
   public void sendStockUpdateAlerts() throws Exception {
      try {
         List<ItemMasterEntity> list = this.itemMasterRepository.getAllItemsWithStockChange();
         if (list != null) {
            this.mailService.sendStockUpdateAlerts(list);
         } else {
            logger.info("No data to fetch");
         }

      } catch (Exception var2) {
         var2.printStackTrace();
         logger.error("Error fetching data", var2);
         throw new RuntimeException("Email send failed: " + var2.getLocalizedMessage());
      }
   }

   @Transactional
   @Scheduled(
      cron = "0 00 18 * * *"
   )
   public void sendNewStockUpdateAlerts() throws Exception {
      try {
         List<ItemRecievedEntity> list = this.itemRecievedRepository.getAllItemsReceivedToday();
         if (list != null) {
            this.mailService.sendNewStockUpdateAlerts(list);
         } else {
            logger.info("No data to fetch");
         }

      } catch (Exception var2) {
         var2.printStackTrace();
         logger.error("Error fetching data", var2);
         throw new RuntimeException("Email send failed: " + var2.getLocalizedMessage());
      }
   }
}
