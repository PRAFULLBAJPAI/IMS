package com.doritech.api.Service;

import com.doritech.api.DTO.ProductionEntityDTO;
import com.doritech.api.Entity.ItemIssuedEntity;
import com.doritech.api.Entity.ItemMasterEntity;
import com.doritech.api.Entity.ParamEntity;
import com.doritech.api.Entity.ProductionEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.ItemIssuedRepository;
import com.doritech.api.Repository.ItemMasterRepository;
import com.doritech.api.Repository.ParamRepository;
import com.doritech.api.Repository.ProductionEntityRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductionEntityService {
   @Autowired
   ProductionEntityRepository productionEntityRepository;
   @Autowired
   ParamRepository paramRepository;
   @Autowired
   ItemMasterRepository itemMasterRepository;
   @Autowired
   ItemIssuedRepository itemIssuedRepository;

   public ResponseEntity addMultipleProductsLogs(List<ProductionEntityDTO> productionEntityDTOs, HttpServletRequest httpServletRequest) {
      ResponseEntity response = new ResponseEntity();

      try {
         System.out.println("<------------------------------------------------------>");
         System.out.println(productionEntityDTOs);
         System.out.println("<------------------------------------------------------>");
         ParamEntity paramEntity = this.paramRepository.findByCode("PRODUCT_ID");
         if (productionEntityDTOs != null && !productionEntityDTOs.isEmpty()) {
            List<ProductionEntity> responseList = new ArrayList();
            Iterator var6 = productionEntityDTOs.iterator();

            label54:
            while(true) {
               if (!var6.hasNext()) {
                  paramEntity.setDescp2(String.valueOf(Integer.parseInt(paramEntity.getDescp2()) + 1));
                  this.paramRepository.save(paramEntity);
                  response.setPayload(responseList);
                  response.setMessage("Production logs added successfully");
                  response.setStatusCode(HttpStatus.OK.value());
                  break;
               }

               ProductionEntityDTO productionEntityDTO = (ProductionEntityDTO)var6.next();
               System.out.println("Entity:---> " + productionEntityDTO);
               ProductionEntity production = new ProductionEntity();
               BeanUtils.copyProperties(productionEntityDTO, production);
               production.setTerminal(httpServletRequest.getRemoteAddr());
               production.setProductionDate(productionEntityDTO.getProductionDate());
               production.setProductId(paramEntity.getDescp1() + paramEntity.getDescp2());
               production.setEntryDate(new Date());
               this.productionEntityRepository.save(production);
               System.out.println("Saved Entity:----> " + production);
               responseList.add(production);
               ItemMasterEntity item = (ItemMasterEntity)this.itemMasterRepository.getById(productionEntityDTO.getItemCode());
               if (item == null) {
                  response.setMessage("Item with code " + productionEntityDTO.getItemCode() + " not found.");
                  response.setStatusCode(HttpStatus.NOT_FOUND.value());
                  return response;
               }

               double updatedQuantity = item.getQuantityInStock() + (double)productionEntityDTO.getQuantity();
               item.setQuantityInStock(updatedQuantity);
               this.itemMasterRepository.save(item);
               List<ItemIssuedEntity> list = this.itemIssuedRepository.findByProductCode(production.getItemCode());
               if (list != null && !list.isEmpty()) {
                  Iterator var11 = list.iterator();

                  while(true) {
                     while(true) {
                        if (!var11.hasNext()) {
                           continue label54;
                        }

                        ItemIssuedEntity itemIssuedEntity = (ItemIssuedEntity)var11.next();
                        ItemMasterEntity itemMaster = (ItemMasterEntity)this.itemMasterRepository.getById(itemIssuedEntity.getItemCode());
                        if (itemMaster != null && "A".equalsIgnoreCase(itemIssuedEntity.getRawMaterialStatus())) {
                           double updatedIssuedQuantity = itemMaster.getQuantityInStock() - (double)productionEntityDTO.getQuantity() * itemIssuedEntity.getItemQuantity();
                           itemMaster.setQuantityInStock(updatedIssuedQuantity);
                           this.itemMasterRepository.save(itemMaster);
                        } else {
                           response.setMessage("No item master for issued item found");
                           response.setStatusCode(HttpStatus.NOT_FOUND.value());
                        }
                     }
                  }
               } else {
                  response.setMessage("No items are issued for this product");
                  response.setStatusCode(HttpStatus.NOT_FOUND.value());
               }
            }
         } else {
            response.setMessage("No data to save.");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
         }
      } catch (Exception var16) {
         var16.printStackTrace();
         response.setMessage("Error adding production logs to the database. " + var16.getMessage());
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getAllProductionEntity() throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ProductionEntity> productionList = this.productionEntityRepository.findAll();
         if (productionList.isEmpty()) {
            response.setMessage("No data to fetch");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }

         List<ProductionEntityDTO> responseList = new ArrayList();
         Iterator var4 = productionList.iterator();

         while(var4.hasNext()) {
            ProductionEntity productionEntity = (ProductionEntity)var4.next();
            ProductionEntityDTO responseDto = new ProductionEntityDTO();
            BeanUtils.copyProperties(productionEntity, responseDto);
            responseDto.setItemName(((ItemMasterEntity)this.itemMasterRepository.getById(productionEntity.getItemCode())).getItemName());
            responseList.add(responseDto);
         }

         response.setPayload(responseList);
         response.setMessage("Data fetched successfully.");
         response.setStatusCode(HttpStatus.OK.value());
      } catch (Exception var7) {
         var7.printStackTrace();
         response.setMessage("Error fetching data");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getAllBOMByDateRange(Date startDate, Date endDate) {
      ResponseEntity response = new ResponseEntity();

      try {
         System.out.println(startDate + " is of type " + startDate.getClass().getSimpleName());
         System.out.println(endDate + " is of type " + endDate.getClass().getSimpleName());
         System.out.println("Request Part");
         List<ProductionEntity> productionList = this.productionEntityRepository.getAllProductionEntityByDateRange(startDate, endDate);
         System.out.println("----------------------------");
         System.out.println(productionList);
         System.out.println("----------------------------");
         if (productionList.isEmpty()) {
            response.setMessage("No production logs found within the specified date range");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         } else {
            List<ProductionEntityDTO> responseList = new ArrayList();
            Iterator var6 = productionList.iterator();

            while(var6.hasNext()) {
               ProductionEntity productionEntity = (ProductionEntity)var6.next();
               ProductionEntityDTO responseDto = new ProductionEntityDTO();
               BeanUtils.copyProperties(productionEntity, responseDto);
               responseList.add(responseDto);
               System.out.println(responseDto);
            }

            response.setPayload(responseList);
            response.setMessage("Production logs fetched successfully within the specified date range");
            response.setStatusCode(HttpStatus.OK.value());
         }
      } catch (Exception var9) {
         response.setMessage("Error fetching production logs within the specified date range");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }
}
