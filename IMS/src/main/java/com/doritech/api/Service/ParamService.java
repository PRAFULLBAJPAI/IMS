package com.doritech.api.Service;

import com.doritech.api.DTO.ParamEntityDTO;
import com.doritech.api.Entity.ParamEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.ParamRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ParamService {
   @Autowired
   ParamRepository paramRepository;

   public String getVendorIdDescpValue() throws Exception {
      ParamEntity paramEntity = this.paramRepository.findByCode("VENDOR_ID");
      String generateVendorId = "";
      if (paramEntity != null) {
         generateVendorId = paramEntity.getDescp1() + paramEntity.getDescp2();
         return generateVendorId;
      } else {
         throw new Exception("Invalid Param Entity");
      }
   }

   public ParamEntity updateVendorIdDescp2Value(ParamEntity paramRequest) {
      ParamEntity paramEntity = this.paramRepository.findByCode(paramRequest.getCode());
      if (paramEntity != null) {
         String newValue = String.valueOf(Integer.parseInt(paramEntity.getDescp2()) + 1);
         paramEntity.setDescp2(newValue);
         this.paramRepository.save(paramEntity);
      }

      return paramRequest;
   }

   public ParamEntity updateEmployeeDescp2Value(ParamEntity paramRequest) {
      ParamEntity paramEntity = this.paramRepository.findByCode(paramRequest.getCode());
      if (paramEntity != null) {
         String newValue = String.valueOf(Integer.parseInt(paramEntity.getDescp2()) + 1);
         paramEntity.setDescp2(newValue);
         this.paramRepository.save(paramEntity);
      }

      return paramRequest;
   }

   public String getIssueIdDescpValue() throws Exception {
      ParamEntity paramEntity = this.paramRepository.findByCode("ISSUE_ID");
      String generateItemIssueId = "";
      if (paramEntity != null) {
         generateItemIssueId = paramEntity.getDescp1() + paramEntity.getDescp2();
         return generateItemIssueId;
      } else {
         throw new Exception("Invalid Param Entity");
      }
   }

   public ParamEntity updateIssueIdDescp2Value(ParamEntity paramRequest) {
      ParamEntity paramEntity = this.paramRepository.findByCode(paramRequest.getCode());
      if (paramEntity != null) {
         String newValue = String.valueOf(Integer.parseInt(paramEntity.getDescp2()) + 1);
         paramEntity.setDescp2(newValue);
         this.paramRepository.save(paramEntity);
      }

      return paramRequest;
   }

   public String getReceiveIdDescpValue() throws Exception {
      ParamEntity paramEntity = this.paramRepository.findByCode("RECEIVE_ID");
      String generateItemReceiveId = "";
      if (paramEntity != null) {
         generateItemReceiveId = paramEntity.getDescp1() + paramEntity.getDescp2();
         return generateItemReceiveId;
      } else {
         throw new Exception("Invalid Param Entity");
      }
   }

   public List<ParamEntity> getAllConsumptionFor() {
      List<ParamEntity> paramList = this.paramRepository.findAll();
      List<ParamEntity> consumptionForList = new ArrayList();
      Iterator var3 = paramList.iterator();

      while(var3.hasNext()) {
         ParamEntity param = (ParamEntity)var3.next();
         if (param.getCode().equalsIgnoreCase("ITEM_TYPE")) {
            consumptionForList.add(param);
         }
      }

      return consumptionForList;
   }

   public List<ParamEntity> getAllConsumptionType() {
      List<ParamEntity> paramList = this.paramRepository.findAll();
      List<ParamEntity> consumptionTypeList = new ArrayList();
      Iterator var3 = paramList.iterator();

      while(var3.hasNext()) {
         ParamEntity param = (ParamEntity)var3.next();
         if (param.getCode().equalsIgnoreCase("CONSUMPTION_TYPE")) {
            consumptionTypeList.add(param);
         }
      }

      return consumptionTypeList;
   }

   public List<ParamEntity> getAllWarehouse() {
      List<ParamEntity> paramList = this.paramRepository.findAll();
      List<ParamEntity> warehouseList = new ArrayList();
      Iterator var3 = paramList.iterator();

      while(var3.hasNext()) {
         ParamEntity param = (ParamEntity)var3.next();
         if (param.getCode().equalsIgnoreCase("WAREHOUSE")) {
            warehouseList.add(param);
         }
      }

      return warehouseList;
   }

   public List<ParamEntity> getAllItemUomType() {
      List<ParamEntity> paramList = this.paramRepository.findAll();
      List<ParamEntity> itemUomList = new ArrayList();
      Iterator var3 = paramList.iterator();

      while(var3.hasNext()) {
         ParamEntity param = (ParamEntity)var3.next();
         if (param.getCode().equalsIgnoreCase("ITEM_UOM")) {
            itemUomList.add(param);
         }
      }

      return itemUomList;
   }

   public ParamEntity updateReceiveIdDescp2Value(ParamEntity paramRequest) {
      ParamEntity paramEntity = this.paramRepository.findByCode(paramRequest.getCode());
      if (paramEntity != null) {
         String newValue = String.valueOf(Integer.parseInt(paramEntity.getDescp2()) + 1);
         paramEntity.setDescp2(newValue);
         this.paramRepository.save(paramEntity);
      }

      return paramRequest;
   }

   public ParamEntity updateItemDescp2Value(ParamEntity paramRequest) {
      ParamEntity paramEntity = this.paramRepository.findByCode(paramRequest.getCode());
      if (paramEntity != null) {
         String newValue = String.valueOf(Integer.parseInt(paramEntity.getDescp2()) + 1);
         paramEntity.setDescp2(newValue);
         this.paramRepository.save(paramEntity);
      }

      return paramRequest;
   }

   public ParamEntity updateImageDescp2Value(ParamEntity paramRequest) {
      ParamEntity paramEntity = this.paramRepository.findByCode(paramRequest.getCode());
      if (paramEntity != null) {
         String newValue = String.valueOf(Integer.parseInt(paramEntity.getDescp2()) + 1);
         paramEntity.setDescp2(newValue);
         this.paramRepository.save(paramEntity);
      }

      return paramRequest;
   }

   public String getEmployeeDescpValue() throws Exception {
      ParamEntity paramEntity = this.paramRepository.findByCode("EMPLOYEE_ID");
      String generateEmployeeCode = "";
      if (paramEntity != null) {
         generateEmployeeCode = paramEntity.getDescp1() + paramEntity.getDescp2();
         return generateEmployeeCode;
      } else {
         throw new Exception("Invalid Param Entity");
      }
   }

   public String getItemDescpValue() throws Exception {
      ParamEntity paramEntity = this.paramRepository.findByCode("ITEM_CODE");
      String generateItemCode = "";
      if (paramEntity != null) {
         generateItemCode = paramEntity.getDescp1() + paramEntity.getDescp2();
         return generateItemCode;
      } else {
         throw new Exception("Invalid Param Entity");
      }
   }

   public String getImageDescpValue() throws Exception {
      ParamEntity paramEntity = this.paramRepository.findByCode("IMAGE_CODE");
      String generateImageCode = "";
      if (paramEntity != null) {
         generateImageCode = paramEntity.getDescp1() + paramEntity.getDescp2();
         return generateImageCode;
      } else {
         throw new Exception("Invalid Param Entity");
      }
   }

   public ParamEntity updateBranchDescp2Value(ParamEntity paramRequest) {
      ParamEntity paramEntity = this.paramRepository.findByCode(paramRequest.getCode());
      if (paramEntity != null) {
         String newValue = String.valueOf(Integer.parseInt(paramEntity.getDescp2()) + 1);
         paramEntity.setDescp2(newValue);
         this.paramRepository.save(paramEntity);
      }

      return paramRequest;
   }

   public String getBranchDescpValue() throws Exception {
      ParamEntity paramEntity = this.paramRepository.findByCode("BRANCH_ID");
      String generateBranchCode = "";
      if (paramEntity != null) {
         generateBranchCode = paramEntity.getDescp1() + paramEntity.getDescp2();
         return generateBranchCode;
      } else {
         throw new Exception("Invalid Param Entity");
      }
   }

   public ResponseEntity getParamByCodeAndSerial() {
      ResponseEntity responseEntity = new ResponseEntity();

      try {
         ParamEntity paramEntity = this.paramRepository.getParamByCodeAndSerial("COMPANY", "OWN");
         if (paramEntity != null) {
            ParamEntityDTO dto = new ParamEntityDTO();
            dto.setCode(paramEntity.getCode());
            dto.setSerial(paramEntity.getSerial());
            dto.setDescp1(paramEntity.getDescp1());
            dto.setDescp2(paramEntity.getDescp2());
            responseEntity.setPayload(dto);
            responseEntity.setMessage("Param Fetched..");
            responseEntity.setStatusCode(HttpStatus.OK.value());
         } else {
            responseEntity.setMessage("Param Not Fetched..");
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         }
      } catch (Exception var4) {
         responseEntity.setMessage("Error Occured!!" + var4.getLocalizedMessage());
         responseEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
      }

      return responseEntity;
   }

   public ResponseEntity getParamForClientByCodeAndSerial() {
      ResponseEntity responseEntity = new ResponseEntity();

      try {
         ParamEntity paramEntity = this.paramRepository.getParamByCodeAndSerial("COMPANY", "CLIENT");
         if (paramEntity != null) {
            ParamEntityDTO dto = new ParamEntityDTO();
            dto.setCode(paramEntity.getCode());
            dto.setSerial(paramEntity.getSerial());
            dto.setDescp1(paramEntity.getDescp1());
            dto.setDescp2(paramEntity.getDescp2());
            responseEntity.setPayload(dto);
            responseEntity.setMessage("Param Fetched..");
            responseEntity.setStatusCode(HttpStatus.OK.value());
         } else {
            responseEntity.setMessage("Param Not Fetched..");
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         }
      } catch (Exception var4) {
         responseEntity.setMessage("Error Occured!!" + var4.getLocalizedMessage());
         responseEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
      }

      return responseEntity;
   }

   public ParamEntity updateGroupDescp2Value(ParamEntity paramRequest) {
      ParamEntity paramEntity = this.paramRepository.findByCode(paramRequest.getCode());
      if (paramEntity != null) {
         String newValue = String.valueOf(Integer.parseInt(paramEntity.getDescp2()) + 1);
         paramEntity.setDescp2(newValue);
         this.paramRepository.save(paramEntity);
      }

      return paramRequest;
   }

   public String getGroupDescpValue() throws Exception {
      ParamEntity paramEntity = this.paramRepository.findByCode("GROUP_ID");
      String generateGroupCode = "";
      if (paramEntity != null) {
         generateGroupCode = paramEntity.getDescp1() + paramEntity.getDescp2();
         return generateGroupCode;
      } else {
         throw new Exception("Invalid Param Entity");
      }
   }

   public String getNoOfAttempts() {
      try {
         ParamEntity paramEntity = this.paramRepository.findByCode("ASSESSMENT");
         String noOfAttempts = "";
         if (paramEntity != null) {
            noOfAttempts = paramEntity.getDescp2();
         }

         return noOfAttempts;
      } catch (Exception var3) {
         return "record not found";
      }
   }
}
