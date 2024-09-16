package com.doritech.api.Service;

import com.doritech.api.DTO.ItemRecievedDTO;
import com.doritech.api.Entity.ItemMasterEntity;
import com.doritech.api.Entity.ItemRecievedEntity;
import com.doritech.api.Entity.ParamEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Entity.VendorMasterEntity;
import com.doritech.api.Repository.ItemMasterRepository;
import com.doritech.api.Repository.ItemRecievedRepository;
import com.doritech.api.Repository.ParamRepository;
import com.doritech.api.Repository.VendorMasterRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ItemRecievedService {
   @Autowired
   ItemRecievedRepository itemRecievedRepo;
   @Autowired
   ItemMasterRepository itemMasterRepo;
   @Autowired
   ParamRepository paramRepository;
   @Autowired
   ParamService paramService;
   @Autowired
   MailService mailService;
   @Autowired
   VendorMasterRepository vendorMasterRepository;

   public ResponseEntity getAllItemRecieved() {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemRecievedEntity> list = this.itemRecievedRepo.findAll();
         List<ItemRecievedDTO> responseList = new ArrayList();
         if (list.isEmpty()) {
            response.setMessage("No data to fetch");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }

         if (!list.isEmpty()) {
            Iterator var4 = list.iterator();

            while(var4.hasNext()) {
               ItemRecievedEntity itemRecievedEntity = (ItemRecievedEntity)var4.next();
               ItemRecievedDTO item = new ItemRecievedDTO();
               BeanUtils.copyProperties(itemRecievedEntity, item);
               item.setItemName(((ItemMasterEntity)this.itemMasterRepo.getById(itemRecievedEntity.getItemCode())).getItemName());
               item.setLastPrice(((ItemMasterEntity)this.itemMasterRepo.getById(itemRecievedEntity.getItemCode())).getUnitPrice());
               item.setVendorName(((VendorMasterEntity)this.vendorMasterRepository.getById(itemRecievedEntity.getItemSource())).getVendorName());
               responseList.add(item);
            }

            response.setPayload(responseList);
            response.setMessage("All items fetched successfully");
            response.setStatusCode(HttpStatus.OK.value());
         }
      } catch (Exception var7) {
         var7.printStackTrace();
         response.setMessage("Error fetching data from database.");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity addItemRecived(ItemRecievedDTO itemRecievedRequest, HttpServletRequest httpServletRequest) {
      ResponseEntity response = new ResponseEntity();

      try {
         ItemRecievedEntity entity = new ItemRecievedEntity();
         Optional<ItemMasterEntity> optional = this.itemMasterRepo.findByItemCode(itemRecievedRequest.getItemCode());
         if (optional.isPresent()) {
            ItemMasterEntity itemMasterEntity = (ItemMasterEntity)optional.get();
            itemMasterEntity.setQuantityInStock(itemMasterEntity.getQuantityInStock() + itemRecievedRequest.getItemQuantity());
            this.itemMasterRepo.save(itemMasterEntity);
         }

         BeanUtils.copyProperties(itemRecievedRequest, entity);
         entity.setTerminal(httpServletRequest.getRemoteAddr());
         this.itemRecievedRepo.save(entity);
         response.setMessage("Item recieved deatils saved successfully");
         response.setPayload(entity);
         response.setStatusCode(HttpStatus.OK.value());
      } catch (Exception var7) {
         response.setMessage("Error occur while saving item details");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity addMultipleReceivedItem(List<ItemRecievedDTO> itemsReceivedRequests, HttpServletRequest httpServletRequest) {
      ResponseEntity response = new ResponseEntity();

      try {
         System.out.println(itemsReceivedRequests);
         ParamEntity paramEntity = this.paramRepository.findByCode("RECEIVE_ID");
         List<ItemRecievedEntity> itemRecievedDTOs = new ArrayList();
         Iterator var6 = itemsReceivedRequests.iterator();

         while(var6.hasNext()) {
            ItemRecievedDTO itemRecievedDTO = (ItemRecievedDTO)var6.next();
            ItemRecievedEntity item = new ItemRecievedEntity();
            BeanUtils.copyProperties(itemRecievedDTO, item);
            item.setRecieveId(paramEntity.getDescp1() + paramEntity.getDescp2());
            item.setTerminal(httpServletRequest.getRemoteAddr());
            item.setEntryDate(new Date());
            item.setCurrentPrice(((ItemMasterEntity)this.itemMasterRepo.findByItemCode(itemRecievedDTO.getItemCode()).get()).getUnitPrice());
            itemRecievedDTOs.add(item);
            this.itemRecievedRepo.save(item);
            ItemMasterEntity itemMasterEntity = (ItemMasterEntity)this.itemMasterRepo.getById(itemRecievedDTO.getItemCode());
            if (itemMasterEntity != null) {
               double quantity = itemMasterEntity.getQuantityInStock() + itemRecievedDTO.getItemQuantity();
               itemMasterEntity.setQuantityInStock(quantity);
               if (itemMasterEntity.getUnitPrice().compareTo(itemRecievedDTO.getItemPrice()) != 0) {
                  BigDecimal currentPrice = itemMasterEntity.getUnitPrice();
                  BigDecimal newPrice = itemRecievedDTO.getItemPrice();
                  itemMasterEntity.setUnitPrice(newPrice);
               }

               this.itemMasterRepo.save(itemMasterEntity);
            } else {
               ItemMasterEntity itemMaster = new ItemMasterEntity();
               BeanUtils.copyProperties(itemRecievedDTO, itemMaster);
               this.itemMasterRepo.save(itemMaster);
            }
         }

         this.paramService.updateReceiveIdDescp2Value(paramEntity);
         response.setPayload(itemRecievedDTOs);
         response.setMessage("All items received successfully.");
         response.setStatusCode(HttpStatus.OK.value());
      } catch (Exception var14) {
         var14.printStackTrace();
         response.setMessage("Error saving data");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }
}
