package com.doritech.api.Service;

import com.doritech.api.Common.Message;
import com.doritech.api.DTO.ItemIssuedDTO;
import com.doritech.api.Entity.ItemIssuedEntity;
import com.doritech.api.Entity.ItemMasterEntity;
import com.doritech.api.Entity.ParamEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.ItemIssuedRepository;
import com.doritech.api.Repository.ItemMasterRepository;
import com.doritech.api.Repository.ParamRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ItemIssuedService {
   @Autowired
   ItemIssuedRepository issuedRepository;
   @Autowired
   ItemMasterRepository itemMasterRepository;
   @Autowired
   ParamRepository paramRepository;
   @Autowired
   ParamService paramService;
   @Autowired
   MailService mailService;

   public ResponseEntity getProductDetails(String itemCode) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemIssuedEntity> itemIssuedList = this.issuedRepository.findByProductCode(itemCode);
         List<ItemIssuedDTO> responseList = new ArrayList();
         Iterator var5 = itemIssuedList.iterator();

         while(var5.hasNext()) {
            ItemIssuedEntity itemIssuedEntity = (ItemIssuedEntity)var5.next();
            if ("A".equalsIgnoreCase(itemIssuedEntity.getRawMaterialStatus())) {
               ItemIssuedDTO itemIssuedDTO = new ItemIssuedDTO();
               BeanUtils.copyProperties(itemIssuedEntity, itemIssuedDTO);
               itemIssuedDTO.setItemName(((ItemMasterEntity)this.itemMasterRepository.getById(itemIssuedEntity.getItemCode())).getItemName());
               responseList.add(itemIssuedDTO);
            }
         }

         response.setPayload(responseList);
         response.setMessage("Data fetched successfully.");
         response.setStatusCode(HttpStatus.OK.value());
      } catch (Exception var8) {
         var8.printStackTrace();
         response.setMessage("Error fetching data");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getAllIssuedItems() throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemIssuedEntity> responseList = this.issuedRepository.findAll();
         List<ItemIssuedDTO> ansList = new ArrayList();
         if (responseList != null) {
            Iterator var4 = responseList.iterator();

            while(var4.hasNext()) {
               ItemIssuedEntity itemIssued = (ItemIssuedEntity)var4.next();
               ItemIssuedDTO iList = new ItemIssuedDTO();
               iList.setIssueId(itemIssued.getIssueId());
               iList.setItemIssueId(itemIssued.getItemIssueId());
               iList.setIssueDate(itemIssued.getIssueDate());
               iList.setConsumptionFor(itemIssued.getConsumptionFor());
               iList.setConsumptionType(itemIssued.getConsumptionType());
               iList.setItemCode(itemIssued.getItemCode());
               iList.setItemName(((ItemMasterEntity)this.itemMasterRepository.getById(itemIssued.getItemCode())).getItemName());
               iList.setItemQuantity(itemIssued.getItemQuantity());
               iList.setEmpId(itemIssued.getEmpId());
               iList.setRemark(itemIssued.getRemark());
               iList.setTerminal(itemIssued.getTerminal());
               iList.setEntryDate(itemIssued.getEntryDate());
               ansList.add(iList);
            }

            response.setPayload(ansList);
            response.setMessage("All Items Issued fetched successfully");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("No items has been issued yet.");
            response.setPayload(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var7) {
         var7.printStackTrace();
         response.setMessage("Error fething data from the database.");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getItemIssuedByDate(Date issueDate) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemIssuedEntity> itemList = this.issuedRepository.getByIssueDate(issueDate);
         if (!itemList.isEmpty()) {
            response.setPayload(itemList);
            response.setMessage("Data fetched successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("No data present in the database for the given date");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setPayload((Object)null);
         }
      } catch (Exception var4) {
         var4.printStackTrace();
         response.setMessage("Error fetching data");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity issueItems(ItemIssuedDTO itemIssued, HttpServletRequest request) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         Optional<ItemMasterEntity> optionalItem = this.itemMasterRepository.findByItemCode(itemIssued.getItemCode());
         if (optionalItem.isPresent()) {
            ItemMasterEntity itemMasterEntity = (ItemMasterEntity)optionalItem.get();
            if (itemMasterEntity.getQuantityInStock() >= itemIssued.getItemQuantity()) {
               ItemIssuedEntity issuedEntity = new ItemIssuedEntity();
               issuedEntity.setIssueId(itemIssued.getIssueId());
               issuedEntity.setIssueDate(itemIssued.getIssueDate());
               issuedEntity.setConsumptionType(itemIssued.getConsumptionType());
               issuedEntity.setConsumptionFor(itemIssued.getConsumptionFor());
               issuedEntity.setItemCode(itemIssued.getItemCode());
               issuedEntity.setItemQuantity(itemIssued.getItemQuantity());
               issuedEntity.setEmpId(itemIssued.getEmpId());
               issuedEntity.setRemark(itemIssued.getRemark());
               issuedEntity.setTerminal(request.getRemoteAddr());
               issuedEntity.setEntryDate(new Date());
               this.issuedRepository.save(issuedEntity);
               double updatedQuantity = itemMasterEntity.getQuantityInStock() - itemIssued.getItemQuantity();
               itemMasterEntity.setQuantityInStock(updatedQuantity);
               this.itemMasterRepository.save(itemMasterEntity);
               response.setPayload(itemIssued);
               response.setMessage("Items issued successfully.");
               response.setStatusCode(HttpStatus.OK.value());
            } else {
               response.setMessage("Required number of stock is not present");
               response.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
         } else {
            response.setMessage("No items present in the database.");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var9) {
         var9.printStackTrace();
         response.setMessage("Error issuing items");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getAllIssuedItemsByDateRange(Date startDate, Date endDate) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemIssuedEntity> issuedItemsList = this.issuedRepository.getIssuedItemsByDateRange(startDate, endDate);
         List<ItemIssuedDTO> responseList = new ArrayList();
         if (!issuedItemsList.isEmpty()) {
            Iterator var6 = issuedItemsList.iterator();

            while(var6.hasNext()) {
               ItemIssuedEntity itemIssuedEntity = (ItemIssuedEntity)var6.next();
               ItemIssuedDTO itemIssuedDTO = new ItemIssuedDTO();
               BeanUtils.copyProperties(itemIssuedEntity, itemIssuedDTO);
               itemIssuedDTO.setItemName(((ItemMasterEntity)this.itemMasterRepository.getById(itemIssuedEntity.getItemCode())).getItemName());
               responseList.add(itemIssuedDTO);
            }

            response.setPayload(responseList);
            response.setMessage("Issued items fetched successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("No Items has been issued in the given date range.");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var9) {
         var9.printStackTrace();
         response.setMessage("Error fetching data from database.");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getByItemIssuedId(int itemIssuedId) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         ItemIssuedEntity itemIssuedEntity = this.issuedRepository.findByItemIssueId(itemIssuedId);
         if (itemIssuedEntity != null) {
            ItemIssuedDTO itemIssuedDTO = new ItemIssuedDTO();
            BeanUtils.copyProperties(itemIssuedEntity, itemIssuedDTO);
            response.setPayload(itemIssuedDTO);
            response.setMessage("Issued Item fetched successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("No Items has been issued with the given issue id");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var5) {
         var5.printStackTrace();
         response.setMessage("Error fetching issued item");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity saveAllItemIssued(List<ItemIssuedDTO> itemIssuedDTO, HttpServletRequest httpServletRequest) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemIssuedDTO> issuedDTOs = new ArrayList();
         if (!itemIssuedDTO.isEmpty()) {
            Iterator var5 = itemIssuedDTO.iterator();

            while(var5.hasNext()) {
               ItemIssuedDTO itemIssued = (ItemIssuedDTO)var5.next();
               ResponseEntity responseEntity = this.issueItems(itemIssued, httpServletRequest);
               ItemIssuedDTO issuedDTO = (ItemIssuedDTO)responseEntity.getPayload();
               issuedDTOs.add(issuedDTO);
            }

            response.setPayload(issuedDTOs);
            response.setMessage("Data saved successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("Sorry! Data can't be saved in the database. Check your entries again.");
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
         }
      } catch (Exception var9) {
         var9.printStackTrace();
         response.setMessage("Error saving data in the database");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity issueMultipleItems(List<ItemIssuedDTO> itemsIssued, HttpServletRequest request) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemIssuedDTO> responseList = new ArrayList();
         ParamEntity paramEntity = this.paramRepository.findByCode("ISSUE_ID");
         int id = Integer.parseInt(paramEntity.getDescp2());
         if (itemsIssued != null) {
            Iterator var7 = itemsIssued.iterator();

            while(var7.hasNext()) {
               ItemIssuedDTO itemIssuedDTO = (ItemIssuedDTO)var7.next();
               itemIssuedDTO.setIssueId(paramEntity.getDescp1() + String.valueOf(id));
               ItemIssuedEntity item = new ItemIssuedEntity();
               BeanUtils.copyProperties(itemIssuedDTO, item);
               item.setQuantityInStock(((ItemMasterEntity)this.itemMasterRepository.getById(itemIssuedDTO.getItemCode())).getQuantityInStock());
               item.setTerminal(request.getRemoteAddr());
               item.setEntryDate(new Date());
               item.setRawMaterialStatus("A");
               item.setModifiedOn(new Date());
               responseList.add(itemIssuedDTO);
               this.issuedRepository.save(item);
            }

            this.toString();
            paramEntity.setDescp2(String.valueOf(id + 1));
            this.paramRepository.save(paramEntity);
            response.setPayload(responseList);
            response.setMessage("Data saved successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("No data found to save.");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
         }
      } catch (Exception var10) {
         var10.printStackTrace();
         response.setMessage("Error issuing items");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value() + var10.getLocalizedMessage());
      }

      return response;
   }

   public ResponseEntity updateSubproductDefinition(List<ItemIssuedDTO> itemIssuedDTOs, HttpServletRequest httpServletRequest) {
      ResponseEntity response = new ResponseEntity();

      try {
         if (itemIssuedDTOs.isEmpty()) {
            response.setMessage("No data found to update");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            return response;
         }

         String productCode = ((ItemIssuedDTO)itemIssuedDTOs.get(0)).getProductCode();
         List<ItemIssuedEntity> existingItems = this.issuedRepository.findProductDetailsByProductCode(productCode);
         Iterator var6 = itemIssuedDTOs.iterator();

         while(var6.hasNext()) {
            ItemIssuedDTO itemDTO = (ItemIssuedDTO)var6.next();
            ItemIssuedEntity item = this.issuedRepository.getByProductCodeAndItemCode(productCode, itemDTO.getItemCode());
            if (item != null) {
               item.setItemQuantity(itemDTO.getItemQuantity());
               item.setRemark(itemDTO.getRemark());
               item.setModifiedBy(itemDTO.getModifiedBy());
               item.setModifiedOn(new Date());
               item.setRawMaterialStatus("A");
               this.issuedRepository.save(item);
            } else {
               item = new ItemIssuedEntity();
               item.setIssueId(itemDTO.getIssueId());
               item.setProductCode(itemDTO.getProductCode());
               item.setProductName(itemDTO.getProductName());
               item.setIssueDate(itemDTO.getIssueDate());
               item.setConsumptionFor(itemDTO.getConsumptionFor());
               item.setConsumptionType(itemDTO.getConsumptionType());
               item.setItemCode(itemDTO.getItemCode());
               item.setQuantityInStock(((ItemMasterEntity)this.itemMasterRepository.getById(itemDTO.getItemCode())).getQuantityInStock());
               item.setItemQuantity(itemDTO.getItemQuantity());
               item.setEmpId(itemDTO.getEmpId());
               item.setRemark(itemDTO.getRemark());
               item.setEntryDate(new Date());
               item.setTerminal(httpServletRequest.getRemoteAddr());
               item.setModifiedBy(itemDTO.getModifiedBy());
               item.setModifiedOn(new Date());
               item.setRawMaterialStatus("A");
               this.issuedRepository.save(item);
            }
         }

         String modifiedBy = "";
         Date modifiedOn = new Date();
         Iterator var16 = existingItems.iterator();

         while(var16.hasNext()) {
            ItemIssuedEntity item = (ItemIssuedEntity)var16.next();
            boolean found = false;
            Iterator var11 = itemIssuedDTOs.iterator();

            while(var11.hasNext()) {
               ItemIssuedDTO itemDTO = (ItemIssuedDTO)var11.next();
               if (item.getItemCode().equalsIgnoreCase(itemDTO.getItemCode())) {
                  found = true;
                  modifiedBy = itemDTO.getModifiedBy();
                  break;
               }
            }

            if (!found) {
               item.setModifiedBy(modifiedBy);
               item.setQuantityInStock(((ItemMasterEntity)this.itemMasterRepository.getById(item.getItemCode())).getQuantityInStock());
               item.setModifiedOn(modifiedOn);
               item.setRawMaterialStatus("D");
               this.issuedRepository.save(item);
            }
         }

         response.setPayload("Data updated successfully.");
         response.setMessage("Subproduct Details updated successfully.");
         response.setStatusCode(HttpStatus.OK.value());
      } catch (Exception var13) {
         var13.printStackTrace();
         response.setMessage("Error updating subproduct details: " + var13.getMessage());
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getIssuedItemsByItemCode(String itemCode) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemIssuedEntity> issuedEntities = this.issuedRepository.getIssuedEntities(itemCode);
         List<ItemIssuedDTO> responseList = new ArrayList();
         if (issuedEntities != null) {
            Iterator var5 = issuedEntities.iterator();

            while(var5.hasNext()) {
               ItemIssuedEntity itemIssuedEntity = (ItemIssuedEntity)var5.next();
               if ("A".equalsIgnoreCase(itemIssuedEntity.getRawMaterialStatus())) {
                  ItemIssuedDTO item = new ItemIssuedDTO();
                  BeanUtils.copyProperties(itemIssuedEntity, item);
                  item.setItemName(((ItemMasterEntity)this.itemMasterRepository.getById(itemIssuedEntity.getItemCode())).getItemName());
                  responseList.add(item);
               }
            }

            response.setPayload(responseList);
            response.setMessage("Issue items successfully fetched.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("There is no data for the given item code in teh database.");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var8) {
         var8.printStackTrace();
         response.setMessage("Error fetching data");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getIssuedItemsByItemName(String itemName) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         String itemCode = ((ItemMasterEntity)this.itemMasterRepository.findByItemName(itemName).get()).getItemCode();
         if (itemCode != null) {
            List<ItemIssuedEntity> issuedEntities = this.issuedRepository.getIssuedEntities(itemCode);
            List<ItemIssuedDTO> responseList = new ArrayList();
            if (issuedEntities != null) {
               Iterator var6 = issuedEntities.iterator();

               while(var6.hasNext()) {
                  ItemIssuedEntity itemIssuedEntity = (ItemIssuedEntity)var6.next();
                  ItemIssuedDTO item = new ItemIssuedDTO();
                  BeanUtils.copyProperties(itemIssuedEntity, item);
                  item.setItemName(((ItemMasterEntity)this.itemMasterRepository.getById(itemIssuedEntity.getItemCode())).getItemName());
                  responseList.add(item);
               }

               response.setPayload(responseList);
               response.setMessage("Issue items successfully fetched.");
               response.setStatusCode(HttpStatus.OK.value());
            } else {
               response.setMessage("There is no data for the given item code in teh database.");
               response.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
         } else {
            response.setMessage("No item has been present with the given item name.");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var9) {
         var9.printStackTrace();
         response.setMessage("Error fetching data.");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getProductDetailsByProductCode(String subProductCode) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemIssuedEntity> productDetailsList = this.issuedRepository.findProductDetailsByProductCode(subProductCode);
         productDetailsList.stream().forEach((x) -> {
            System.out.println(x);
         });
         if (productDetailsList.isEmpty()) {
            response.setMessage("No data to fetch.");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }

         List<ItemIssuedDTO> responseDto = new ArrayList();
         Iterator var5 = productDetailsList.iterator();

         while(var5.hasNext()) {
            ItemIssuedEntity itemIssuedEntity = (ItemIssuedEntity)var5.next();
            if ("A".equalsIgnoreCase(itemIssuedEntity.getRawMaterialStatus())) {
               ItemIssuedDTO itemIssued = new ItemIssuedDTO();
               BeanUtils.copyProperties(itemIssuedEntity, itemIssued);
               itemIssued.setItemName(((ItemMasterEntity)this.itemMasterRepository.getById(itemIssuedEntity.getItemCode())).getItemName());
               itemIssued.setMinimumStock(((ItemMasterEntity)this.itemMasterRepository.getById(itemIssuedEntity.getItemCode())).getMinimumStock());
               responseDto.add(itemIssued);
            }
         }

         response.setPayload(responseDto);
         response.setMessage("Data fetched successfully.");
         response.setStatusCode(HttpStatus.OK.value());
      } catch (Exception var8) {
         var8.printStackTrace();
         response.setMessage("Error fetching Data");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getProductDetailsByProductName(String subProductName) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemIssuedEntity> productDetailsList = this.issuedRepository.findByProductName(subProductName);
         if (productDetailsList.isEmpty()) {
            response.setMessage("No data to fetch.");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }

         List<ItemIssuedDTO> responseDto = new ArrayList();
         Iterator var5 = productDetailsList.iterator();

         while(var5.hasNext()) {
            ItemIssuedEntity itemIssuedEntity = (ItemIssuedEntity)var5.next();
            ItemIssuedDTO itemIssued = new ItemIssuedDTO();
            BeanUtils.copyProperties(itemIssuedEntity, itemIssued);
            itemIssued.setItemName(((ItemMasterEntity)this.itemMasterRepository.getById(itemIssuedEntity.getItemCode())).getItemName());
            responseDto.add(itemIssued);
         }

         response.setPayload(responseDto);
         response.setMessage("Data fetched successfully.");
         response.setStatusCode(HttpStatus.OK.value());
      } catch (Exception var8) {
         var8.printStackTrace();
         response.setMessage("Error fetching Data");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getAllProductCodesByPattern(String productCode) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         List<String> productCodeList = this.issuedRepository.findAllProductCodes(productCode);
         if (productCodeList.isEmpty()) {
            response.setMessage("No data found.");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }

         response.setPayload(productCodeList);
         response.setMessage("Data fetched successfully.");
         response.setStatusCode(HttpStatus.OK.value());
      } catch (Exception var4) {
         var4.printStackTrace();
         response.setMessage("Error fetching data");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getAllProductNamesByPattern(String productName) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         List<String> productNameList = this.issuedRepository.findAllProductNames(productName);
         if (productNameList.isEmpty()) {
            response.setMessage("No data found.");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }

         response.setPayload(productNameList);
         response.setMessage("Data fetched successfully.");
         response.setStatusCode(HttpStatus.OK.value());
      } catch (Exception var4) {
         var4.printStackTrace();
         response.setMessage("Error fetching data");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public List<List<Message>> validations(List<ItemIssuedDTO> itemIssuedDTO) {
      List<List<Message>> messageList = new ArrayList();

      ArrayList messages;
      for(Iterator var3 = itemIssuedDTO.iterator(); var3.hasNext(); messageList.add(messages)) {
         ItemIssuedDTO issuedDTO = (ItemIssuedDTO)var3.next();
         messages = new ArrayList();
         if (StringUtils.isEmpty(issuedDTO.getIssueId()) || issuedDTO.getIssueId() == null) {
            messages.add(new Message("Issue ID", "Issue ID can't be null or empty"));
         }

         if (StringUtils.isEmpty(issuedDTO.getIssueDate().toString()) || issuedDTO.getIssueDate().toString() == null) {
            messages.add(new Message("Issue Date", "Issue Date can't be null or empty"));
         }

         if (StringUtils.isEmpty(issuedDTO.getConsumptionType()) || issuedDTO.getConsumptionType() == null) {
            messages.add(new Message("Consumption Type", "Consumption Type can't be null or empty"));
         }

         if (StringUtils.isEmpty(issuedDTO.getConsumptionFor()) || issuedDTO.getConsumptionFor() == null) {
            messages.add(new Message("Consumption For", "Consumption For can't be null or empty"));
         }

         if (StringUtils.isEmpty(issuedDTO.getItemCode()) || issuedDTO.getItemCode() == null) {
            messages.add(new Message("Item Code", "Item Code can't be null or empty"));
         }

         if (issuedDTO.getItemQuantity() == 0.0D) {
            messages.add(new Message("Item Quantity", "Item Quantity can't be null or empty"));
         }
      }

      return messageList;
   }
}
