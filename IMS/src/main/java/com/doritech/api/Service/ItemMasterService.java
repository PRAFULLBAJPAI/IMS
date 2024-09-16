package com.doritech.api.Service;

import com.doritech.api.Common.Message;
import com.doritech.api.DTO.ItemIssuedDTO;
import com.doritech.api.DTO.ItemMasterDTO;
import com.doritech.api.Entity.AuditItemMasterEntity;
import com.doritech.api.Entity.ItemImageEntity;
import com.doritech.api.Entity.ItemMasterEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Entity.VendorMasterEntity;
import com.doritech.api.Repository.AuditItemMasterRepository;
import com.doritech.api.Repository.EmployeeMasterRepository;
import com.doritech.api.Repository.ItemImageRepository;
import com.doritech.api.Repository.ItemIssuedRepository;
import com.doritech.api.Repository.ItemMasterRepository;
import com.doritech.api.Repository.ParamRepository;
import com.doritech.api.Repository.VendorMasterRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemMasterService {
   @Autowired
   ItemMasterRepository itemMasterRepository;
   @Autowired
   ItemIssuedRepository itemIssuedRepository;
   @Autowired
   ItemIssuedService itemIssuedService;
   @Autowired
   ItemImageRepository imageRepo;
   @Autowired
   AuditItemMasterRepository auditItemMasterRepository;
   @Autowired
   EmployeeMasterRepository employeeMasterRepository;
   @Autowired
   ParamService paramService;
   @Autowired
   ParamRepository paramRepository;
   @Autowired
   VendorMasterRepository vendorMasterRepository;
   private static final Logger logger = LoggerFactory.getLogger(ItemMasterService.class);

   public ResponseEntity getAllRawItemsForSubFinalProduct() throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         Map<ItemMasterEntity, List<ItemIssuedDTO>> responseList = new LinkedHashMap();
         List<ItemMasterEntity> subproductList = this.itemMasterRepository.getAllSubproducts();
         if (subproductList != null) {
            Iterator var4 = subproductList.iterator();

            while(var4.hasNext()) {
               ItemMasterEntity item = (ItemMasterEntity)var4.next();
               String itemCode = item.getItemCode();
               ResponseEntity responseEntity = this.itemIssuedService.getProductDetails(itemCode);
               List<ItemIssuedDTO> subproductDefination = (List)responseEntity.getPayload();
               responseList.put(item, subproductDefination);
            }

            response.setPayload(responseList);
            response.setMessage("Data fetched successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("No data found in the database.");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var9) {
         var9.printStackTrace();
         response.setMessage("Error fetching data");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getAllSubProductsByPattern(String itemCode) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         System.out.println(itemCode);
         List<ItemMasterEntity> responseList = this.itemMasterRepository.getAllSubproductsByPattern(itemCode);
         System.out.println(responseList);
         if (responseList != null) {
            response.setPayload(responseList);
            response.setMessage("Data fetched successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("No data is present in the database.");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var4) {
         var4.printStackTrace();
         response.setMessage("Error fetching data.");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getAllItemMaster() {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemMasterEntity> itemMaster = this.itemMasterRepository.findAll();
         if (!itemMaster.isEmpty()) {
            List<ItemMasterDTO> itemMasterDTOList = new ArrayList();
            Iterator var4 = itemMaster.iterator();

            while(var4.hasNext()) {
               ItemMasterEntity item = (ItemMasterEntity)var4.next();
               ItemMasterDTO itemMasterDTO = new ItemMasterDTO();
               BeanUtils.copyProperties(itemMasterDTO, item);
               String itemSourceName = this.getItemSourceName(item.getItemSource());
               itemMasterDTO.setItemSourceName(itemSourceName);
               itemMasterDTOList.add(itemMasterDTO);
            }

            response.setPayload(itemMasterDTOList);
            response.setMessage("Item Master All Values Fetched Successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("Item Master Values not Fetched");
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         }
      } catch (Exception var8) {
         response.setMessage("Error Occurred. Please Contact the Administrator: " + var8.getLocalizedMessage());
         response.setStatusCode(HttpStatus.BAD_REQUEST.value());
      }

      return response;
   }

   private String getItemSourceName(String itemSourceId) {
      if (itemSourceId != null) {
         VendorMasterEntity vendor = (VendorMasterEntity)this.vendorMasterRepository.getById(itemSourceId);
         if (vendor != null) {
            return vendor.getVendorName();
         }
      }

      return "Unknown Vendor";
   }

   public ResponseEntity getRawItemMaster() {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemMasterEntity> itemMaster = this.itemMasterRepository.findAll();
         if (!itemMaster.isEmpty()) {
            List<ItemMasterDTO> itemMasterDTOList = new ArrayList();
            Iterator var4 = itemMaster.iterator();

            while(var4.hasNext()) {
               ItemMasterEntity item = (ItemMasterEntity)var4.next();
               if (item.getItemType().equals("RAW")) {
                  ItemMasterDTO itemMasterDTO = new ItemMasterDTO();
                  BeanUtils.copyProperties(itemMasterDTO, item);
                  itemMasterDTO.setItemSourceName(((VendorMasterEntity)this.vendorMasterRepository.getById(item.getItemSource())).getVendorName());
                  itemMasterDTOList.add(itemMasterDTO);
               }
            }

            response.setPayload(itemMasterDTOList);
            response.setMessage("Item Master All Values Fetched Successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("Item Master Values not Fetched");
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         }
      } catch (Exception var7) {
         response.setMessage("Error Occurred. Please Contact the Administrator: " + var7.getLocalizedMessage());
         response.setStatusCode(HttpStatus.BAD_REQUEST.value());
      }

      return response;
   }

   public ResponseEntity getSubproductItemMaster() {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemMasterEntity> itemMaster = this.itemMasterRepository.findAll();
         if (!itemMaster.isEmpty()) {
            List<ItemMasterDTO> itemMasterDTOList = new ArrayList();
            Iterator var4 = itemMaster.iterator();

            while(var4.hasNext()) {
               ItemMasterEntity item = (ItemMasterEntity)var4.next();
               if (item.getItemType().equals("SUBPRODUCT")) {
                  ItemMasterDTO itemMasterDTO = new ItemMasterDTO();
                  BeanUtils.copyProperties(itemMasterDTO, item);
                  itemMasterDTO.setItemSourceName(((VendorMasterEntity)this.vendorMasterRepository.getById(item.getItemSource())).getVendorName());
                  itemMasterDTOList.add(itemMasterDTO);
               }
            }

            response.setPayload(itemMasterDTOList);
            response.setMessage("Item Master All Values Fetched Successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("Item Master Values not Fetched");
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         }
      } catch (Exception var7) {
         response.setMessage("Error Occurred. Please Contact the Administrator: " + var7.getLocalizedMessage());
         response.setStatusCode(HttpStatus.BAD_REQUEST.value());
      }

      return response;
   }

   public ResponseEntity getFinalProductItemMaster() {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemMasterEntity> itemMaster = this.itemMasterRepository.findAll();
         if (!itemMaster.isEmpty()) {
            List<ItemMasterDTO> itemMasterDTOList = new ArrayList();
            Iterator var4 = itemMaster.iterator();

            while(var4.hasNext()) {
               ItemMasterEntity item = (ItemMasterEntity)var4.next();
               if (item.getItemType().equals("FINAL PRODUCT")) {
                  ItemMasterDTO itemMasterDTO = new ItemMasterDTO();
                  BeanUtils.copyProperties(itemMasterDTO, item);
                  itemMasterDTO.setItemSourceName(((VendorMasterEntity)this.vendorMasterRepository.getById(item.getItemSource())).getVendorName());
                  itemMasterDTOList.add(itemMasterDTO);
               }
            }

            response.setPayload(itemMasterDTOList);
            response.setMessage("Item Master All Values Fetched Successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("Item Master Values not Fetched");
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         }
      } catch (Exception var7) {
         response.setMessage("Error Occurred. Please Contact the Administrator: " + var7.getLocalizedMessage());
         response.setStatusCode(HttpStatus.BAD_REQUEST.value());
      }

      return response;
   }

   public ResponseEntity getItemByCode(String itemCode) {
      ResponseEntity response = new ResponseEntity();

      try {
         Optional<ItemMasterEntity> optionalItem = this.itemMasterRepository.findByItemCode(itemCode);
         if (optionalItem.isPresent()) {
            ItemMasterEntity item = (ItemMasterEntity)optionalItem.get();
            ItemMasterDTO itemMasterDTO = new ItemMasterDTO();
            itemMasterDTO.setImage(((ItemImageEntity)this.imageRepo.findById(item.getImageId()).get()).getImage());
            itemMasterDTO.setImageName(((ItemImageEntity)this.imageRepo.findById(item.getImageId()).get()).getImageName());
            BeanUtils.copyProperties(itemMasterDTO, item);
            response.setPayload(itemMasterDTO);
            response.setMessage("Item with Code '" + itemCode + "' Fetched Successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("Item with Code '" + itemCode + "' not Found");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var6) {
         response.setMessage("Error Occurred. Please Contact the Administrator: " + var6.getLocalizedMessage());
         response.setStatusCode(HttpStatus.BAD_REQUEST.value());
      }

      return response;
   }

   public ResponseEntity saveItemMaster(ItemMasterDTO itemMasterDTO, HttpServletRequest httpServletRequest) {
      ResponseEntity response = new ResponseEntity();

      try {
         List<Message> validationMessages = this.validations(itemMasterDTO);
         if (validationMessages.isEmpty()) {
            Optional<ItemMasterEntity> existingItem = this.itemMasterRepository.findById(itemMasterDTO.getItemCode());
            ItemMasterEntity entity;
            if (existingItem.isPresent() && existingItem != null) {
               entity = (ItemMasterEntity)existingItem.get();
               BeanUtils.copyProperties(itemMasterDTO, entity);
               this.itemMasterRepository.save(entity);
               response.setPayload(entity);
               response.setMessage("Item Updated Successfully.");
               response.setStatusCode(HttpStatus.OK.value());
               logger.info("Item Updated Successfully. Item Code: {}", itemMasterDTO.getItemCode());
            } else {
               entity = new ItemMasterEntity();
               BeanUtils.copyProperties(entity, itemMasterDTO);
               entity.setItemCode(itemMasterDTO.getItemCode().toUpperCase());
               entity.setStatus("Active");
               entity.setTerminal(httpServletRequest.getRemoteAddr());
               entity.setEntryDate(new Date());
               this.itemMasterRepository.save(entity);
               response.setPayload(entity);
               response.setMessage("Item added successfully.");
               response.setStatusCode(HttpStatus.OK.value());
            }
         } else {
            response.setMessage("Item Not Added. Validation Failed.");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setPayload(validationMessages);
            logger.warn("Item validation failed for code {}. Not added.", itemMasterDTO.getItemCode());
         }
      } catch (Exception var7) {
         response.setMessage("Error occurred while processing item. Please Contact With Administrator!!");
         response.setStatusCode(HttpStatus.BAD_REQUEST.value());
         logger.error("Error while processing item. Item Code: {}", itemMasterDTO.getItemCode(), var7);
      }

      return response;
   }

   public ResponseEntity editItemMaster(ItemMasterDTO itemMasterDTO, HttpServletRequest httpServletRequest) {
      ResponseEntity response = new ResponseEntity();

      try {
         List<Message> validationMessages = this.validations(itemMasterDTO);
         if (validationMessages.isEmpty()) {
            Optional<ItemMasterEntity> existingItem = this.itemMasterRepository.findById(itemMasterDTO.getItemCode());
            if (existingItem.isPresent()) {
               ItemMasterEntity itemMasterEntity = (ItemMasterEntity)existingItem.get();
               itemMasterEntity.setItemName(itemMasterDTO.getItemName());
               itemMasterEntity.setItemType(itemMasterDTO.getItemType());
               itemMasterEntity.setItemSource(itemMasterDTO.getItemSource());
               itemMasterEntity.setItemUom(itemMasterDTO.getItemUom());
               itemMasterEntity.setMinimumStock(itemMasterDTO.getMinimumStock());
               itemMasterEntity.setItemGroup(itemMasterDTO.getItemGroup());
               itemMasterEntity.setHsnCode(itemMasterDTO.getHsnCode());
               itemMasterEntity.setWarehouse(itemMasterDTO.getWarehouse());
               itemMasterEntity.setDescription(itemMasterDTO.getDescription());
               itemMasterEntity.setUnitPrice(itemMasterDTO.getUnitPrice());
               itemMasterEntity.setQuantityInStock(itemMasterDTO.getQuantityInStock());
               itemMasterEntity.setStatus("Active");
               itemMasterEntity.setTerminal(httpServletRequest.getRemoteAddr());
               itemMasterEntity.setEntryDate(java.sql.Date.valueOf(LocalDate.now()));
               itemMasterEntity.setRemarks(itemMasterDTO.getRemarks());
               this.itemMasterRepository.save(itemMasterEntity);
               response.setPayload(itemMasterEntity);
               response.setMessage("Item Updated Successfully.");
               response.setStatusCode(HttpStatus.OK.value());
               logger.info("Item Updated Successfully. Item Code: {}", itemMasterDTO.getItemCode());
            } else {
               response.setMessage("Item not found in the database.");
               response.setStatusCode(HttpStatus.NOT_FOUND.value());
               logger.warn("Item not found in the database. Item Code: {}", itemMasterDTO.getItemCode());
            }
         } else {
            response.setMessage("Item Not Updated. Validation Failed.");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setPayload(validationMessages);
            logger.warn("Item validation failed for code {}. Not updated.", itemMasterDTO.getItemCode());
         }
      } catch (Exception var7) {
         response.setMessage("Error occurred while processing item. Please contact the administrator.");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         logger.error("Error while processing item. Item Code: {}", itemMasterDTO.getItemCode(), var7);
      }

      return response;
   }

   @Transactional
   public ResponseEntity deleteItemMaster(String itemCode) {
      ResponseEntity response = new ResponseEntity();

      try {
         Optional<ItemMasterEntity> optional = this.itemMasterRepository.findByItemCode(itemCode);
         if (optional.isPresent()) {
            ItemMasterEntity itemToDelete = (ItemMasterEntity)optional.get();
            AuditItemMasterEntity auditItem = new AuditItemMasterEntity();
            auditItem.copyFrom(itemToDelete);
            this.auditItemMasterRepository.save(auditItem);
            this.itemMasterRepository.delete(itemToDelete);
            response.setMessage("Item with Item Code " + itemCode + " deleted successfully and saved to audit table.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("Item with Item Code " + itemCode + " not found.");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var6) {
         logger.error("Error occurred while deleting item with Item Code {}: {}", itemCode, var6.getMessage());
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         response.setMessage("Error occurred while deleting item. Please check the logs for details.");
      }

      return response;
   }

   public ResponseEntity deactivateItemByCode(String itemCode) {
      ResponseEntity response = new ResponseEntity();

      try {
         Optional<ItemMasterEntity> optionalItem = this.itemMasterRepository.findByItemCode(itemCode);
         if (optionalItem.isPresent()) {
            ItemMasterEntity entity = (ItemMasterEntity)optionalItem.get();
            entity.setStatus("Inactive");
            this.itemMasterRepository.save(entity);
            response.setPayload(entity);
            response.setMessage("Item Deactivated Successfully.");
            response.setStatusCode(HttpStatus.OK.value());
            logger.info("Item Deactivated Successfully. Item Code: {}", itemCode);
         } else {
            response.setMessage("Item with Code '" + itemCode + "' not Found");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var5) {
         response.setMessage("Error Occurred. Please Contact the Administrator: " + var5.getLocalizedMessage());
         response.setStatusCode(HttpStatus.BAD_REQUEST.value());
      }

      return response;
   }

   public List<Message> validations(ItemMasterDTO itemMasterDTO) {
      List<Message> messageList = new ArrayList();
      if (itemMasterDTO.getItemName() == null || StringUtils.isEmpty(itemMasterDTO.getItemName())) {
         messageList.add(new Message("Item Name", "Item Name can't be null or empty"));
      }

      return messageList;
   }

   public ResponseEntity getItemByConsumptionType(String itemCode) {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemMasterEntity> matchingItems = this.itemMasterRepository.findByItemCodeContainingPattern(itemCode);
         if (!matchingItems.isEmpty()) {
            List<ItemMasterDTO> itemMasterDTOList = new ArrayList();
            Iterator var5 = matchingItems.iterator();

            while(true) {
               ItemMasterEntity item;
               do {
                  if (!var5.hasNext()) {
                     response.setPayload(itemMasterDTOList);
                     response.setMessage("Items with Code similar to '" + itemCode + "' Fetched Successfully.");
                     response.setStatusCode(HttpStatus.OK.value());
                     return response;
                  }

                  item = (ItemMasterEntity)var5.next();
               } while(!item.getItemType().equals("SUBPRODUCT") && !item.getItemType().equals("FINAL PRODUCT"));

               ItemMasterDTO itemMasterDTO = new ItemMasterDTO();
               BeanUtils.copyProperties(itemMasterDTO, item);
               itemMasterDTOList.add(itemMasterDTO);
            }
         } else {
            response.setMessage("Item with Code '" + itemCode + "' not Found");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var8) {
         response.setMessage("Error Occurred. Please Contact the Administrator: " + var8.getLocalizedMessage());
         response.setStatusCode(HttpStatus.BAD_REQUEST.value());
      }

      return response;
   }

   public ResponseEntity getItemByItemCode(String itemCode) {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemMasterEntity> matchingItems = this.itemMasterRepository.findByItemCodeContainingPattern(itemCode);
         if (!matchingItems.isEmpty()) {
            List<ItemMasterDTO> itemMasterDTOList = new ArrayList();
            Iterator var5 = matchingItems.iterator();

            while(var5.hasNext()) {
               ItemMasterEntity item = (ItemMasterEntity)var5.next();
               ItemMasterDTO itemMasterDTO = new ItemMasterDTO();
               BeanUtils.copyProperties(itemMasterDTO, item);
               itemMasterDTOList.add(itemMasterDTO);
            }

            response.setPayload(itemMasterDTOList);
            response.setMessage("Items with Code similar to '" + itemCode + "' Fetched Successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("Item with Code '" + itemCode + "' not Found");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var8) {
         response.setMessage("Error Occurred. Please Contact the Administrator: " + var8.getLocalizedMessage());
         response.setStatusCode(HttpStatus.BAD_REQUEST.value());
      }

      return response;
   }

   public ResponseEntity getItemByItemName(String itemName) {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemMasterEntity> matchingItems = this.itemMasterRepository.findByItemNameContainingPattern(itemName);
         if (!matchingItems.isEmpty()) {
            List<ItemMasterDTO> itemMasterDTOList = new ArrayList();
            Iterator var5 = matchingItems.iterator();

            while(var5.hasNext()) {
               ItemMasterEntity item = (ItemMasterEntity)var5.next();
               ItemMasterDTO itemMasterDTO = new ItemMasterDTO();
               BeanUtils.copyProperties(itemMasterDTO, item);
               itemMasterDTOList.add(itemMasterDTO);
            }

            response.setPayload(itemMasterDTOList);
            response.setMessage("Items with Code similar to '" + itemName + "' Fetched Successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("Item with Code '" + itemName + "' not Found");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var8) {
         response.setMessage("Error Occurred. Please Contact the Administrator: " + var8.getLocalizedMessage());
         response.setStatusCode(HttpStatus.BAD_REQUEST.value());
      }

      return response;
   }

   public ResponseEntity getItemByType(String itemType) {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemMasterEntity> itemTypeList = this.itemMasterRepository.findByItemType(itemType);
         if (!itemTypeList.isEmpty()) {
            List<ItemMasterDTO> itemMasterDTOList = new ArrayList();
            Iterator var5 = itemTypeList.iterator();

            while(var5.hasNext()) {
               ItemMasterEntity item = (ItemMasterEntity)var5.next();
               ItemMasterDTO itemMasterDTO = new ItemMasterDTO();
               BeanUtils.copyProperties(itemMasterDTO, item);
               itemMasterDTOList.add(itemMasterDTO);
            }

            response.setPayload(itemMasterDTOList);
            response.setMessage("Items with Type '" + itemType + "' Fetched Successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("Items with Type '" + itemType + "' not Found");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var8) {
         response.setMessage("Error Occurred. Please Contact the Administrator: " + var8.getLocalizedMessage());
         response.setStatusCode(HttpStatus.BAD_REQUEST.value());
      }

      return response;
   }

   public ResponseEntity getAllRawItemsForSubFinalProductByProductCode(String productCode) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         ItemMasterEntity itemMasterEntity = (ItemMasterEntity)this.itemMasterRepository.getById(productCode);
         if (itemMasterEntity == null) {
            response.setMessage("No Item found with the given product code");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }

         ResponseEntity responseEntity = this.itemIssuedService.getProductDetails(productCode);
         List<ItemIssuedDTO> subproductDefination = (List)responseEntity.getPayload();
         response.setPayload(subproductDefination);
         response.setMessage("Data fetched successfully.");
         response.setStatusCode(HttpStatus.OK.value());
      } catch (Exception var6) {
         var6.printStackTrace();
         response.setMessage("Error fetching data");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }
}
