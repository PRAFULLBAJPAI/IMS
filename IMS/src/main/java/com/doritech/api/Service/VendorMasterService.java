package com.doritech.api.Service;

import com.doritech.api.Common.Message;
import com.doritech.api.DTO.VendorMasterDTO;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Entity.VendorMasterEntity;
import com.doritech.api.Repository.VendorMasterRepository;
import java.util.ArrayList;
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
public class VendorMasterService {
   @Autowired
   VendorMasterRepository vendorMasterRepository;

   public ResponseEntity getAllVendorMaster() throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         List<VendorMasterEntity> vendorMasterList = this.vendorMasterRepository.findAll();
         if (vendorMasterList != null) {
            List<VendorMasterDTO> responseList = new ArrayList();
            Iterator var4 = vendorMasterList.iterator();

            while(true) {
               VendorMasterEntity entity;
               VendorMasterDTO vendorMasterDTO;
               do {
                  if (!var4.hasNext()) {
                     response.setPayload(responseList);
                     response.setMessage("All Vendor Master Entity fetched successfully");
                     response.setStatusCode(HttpStatus.OK.value());
                     return response;
                  }

                  entity = (VendorMasterEntity)var4.next();
                  vendorMasterDTO = new VendorMasterDTO();
               } while(!entity.getStatus().equalsIgnoreCase("Y") && !entity.getStatus().equals(""));

               BeanUtils.copyProperties(entity, vendorMasterDTO);
               responseList.add(vendorMasterDTO);
            }
         } else {
            response.setMessage("Sorry! There is no data present in the database");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var7) {
         var7.printStackTrace();
         response.setMessage("Error fetching All Vendor Master Entity");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getVendorMasterById(String vendorId) {
      ResponseEntity response = new ResponseEntity();

      try {
         VendorMasterEntity entity = (VendorMasterEntity)this.vendorMasterRepository.getById(vendorId);
         if (entity != null) {
            VendorMasterDTO vendorMasterDTO = new VendorMasterDTO();
            BeanUtils.copyProperties(entity, vendorMasterDTO);
            response.setPayload(vendorMasterDTO);
            response.setMessage("Vendor Master with given Hsn Code has fetched successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("No Vendor Master is present with given Hsn Code in the database");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var5) {
         var5.printStackTrace();
         response.setMessage("Error fetching Vendor Master Entity with given HsnCode");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity deactivateVendorByVendorId(String vendorId) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         Optional<VendorMasterEntity> optionalItem = this.vendorMasterRepository.findById(vendorId);
         if (optionalItem.isPresent()) {
            VendorMasterEntity entity = (VendorMasterEntity)optionalItem.get();
            entity.setStatus("N");
            this.vendorMasterRepository.save(entity);
            response.setPayload(entity);
            response.setMessage("Vendor Deactivated Successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("Vendor with Code '" + vendorId + "' not Found");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var5) {
         response.setMessage("Error Occurred. Please Contact the Administrator: " + var5.getLocalizedMessage());
         response.setStatusCode(HttpStatus.BAD_REQUEST.value());
      }

      return response;
   }

   public ResponseEntity saveVendorMaster(VendorMasterDTO vendorMasterDTO, HttpServletRequest httpServletRequest) {
      ResponseEntity response = new ResponseEntity();

      try {
         List<Message> validationMessages = this.validations(vendorMasterDTO);
         if (validationMessages.isEmpty()) {
            Optional<VendorMasterEntity> existingVendor = this.vendorMasterRepository.findById(vendorMasterDTO.getVendorId());
            if (!existingVendor.isPresent()) {
               VendorMasterEntity entity = new VendorMasterEntity();
               BeanUtils.copyProperties(vendorMasterDTO, entity);
               entity.setTerminal(httpServletRequest.getRemoteAddr());
               this.vendorMasterRepository.save(entity);
               response.setPayload(entity);
               response.setMessage("Vendor saved successfully.");
               response.setStatusCode(HttpStatus.OK.value());
            } else {
               response.setMessage("Vendor already present in the database.");
               response.setStatusCode(HttpStatus.CONFLICT.value());
            }
         } else {
            response.setMessage("Item Not Added. Validation Failed.");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setPayload(validationMessages);
         }
      } catch (Exception var7) {
         response.setMessage("Error occurred while processing item. Please Contact With Administrator!!");
         response.setStatusCode(HttpStatus.BAD_REQUEST.value());
      }

      return response;
   }

   public ResponseEntity updateVendorMaster(VendorMasterDTO vendorMasterDTO, HttpServletRequest httpServletRequest) {
      ResponseEntity response = new ResponseEntity();

      try {
         List<Message> validationMessages = this.validations(vendorMasterDTO);
         if (validationMessages.isEmpty()) {
            Optional<VendorMasterEntity> existingVendor = this.vendorMasterRepository.findById(vendorMasterDTO.getVendorId());
            if (existingVendor.isPresent()) {
               VendorMasterEntity entity = (VendorMasterEntity)existingVendor.get();
               BeanUtils.copyProperties(vendorMasterDTO, entity);
               this.vendorMasterRepository.save(entity);
               response.setPayload(entity);
               response.setMessage("Vendor updated successfully.");
               response.setStatusCode(HttpStatus.OK.value());
            } else {
               response.setMessage("Vendor is not present in the database.");
               response.setStatusCode(HttpStatus.CONFLICT.value());
            }
         } else {
            response.setMessage("Item Not Added. Validation Failed.");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setPayload(validationMessages);
         }
      } catch (Exception var7) {
         response.setMessage("Error occurred while processing item. Please Contact With Administrator!!");
         response.setStatusCode(HttpStatus.BAD_REQUEST.value());
      }

      return response;
   }

   public List<Message> validations(VendorMasterDTO vendorMasterDTO) {
      List<Message> messageList = new ArrayList();
      if (vendorMasterDTO.getVendorName() == null || StringUtils.isEmpty(vendorMasterDTO.getVendorName())) {
         messageList.add(new Message("Vendor Name", "Vendor Name can't be null or empty"));
      }

      if (vendorMasterDTO.getContactPerson() == null || StringUtils.isEmpty(vendorMasterDTO.getContactPerson())) {
         messageList.add(new Message("Contact Person", "Contact Person can't be null or empty"));
      }

      if (vendorMasterDTO.getEmail() == null || StringUtils.isEmpty(vendorMasterDTO.getEmail())) {
         messageList.add(new Message("Email ID", "Email ID can't be null or empty"));
      }

      if (vendorMasterDTO.getMobile() == null || StringUtils.isEmpty(vendorMasterDTO.getMobile())) {
         messageList.add(new Message("Mobile Number", "Mobile Number can't be null or empty"));
      }

      if (vendorMasterDTO.getAddress() == null || StringUtils.isEmpty(vendorMasterDTO.getAddress())) {
         messageList.add(new Message("Address", "Address can't be null or empty"));
      }

      if (vendorMasterDTO.getCity() == null || StringUtils.isEmpty(vendorMasterDTO.getCity())) {
         messageList.add(new Message("City", "City can't be null or empty"));
      }

      if (vendorMasterDTO.getState() == null || StringUtils.isEmpty(vendorMasterDTO.getState())) {
         messageList.add(new Message("State", "State can't be null or empty"));
      }

      if (vendorMasterDTO.getCountry() == null || StringUtils.isEmpty(vendorMasterDTO.getCountry())) {
         messageList.add(new Message("Country", "Country can't be null or empty"));
      }

      if (vendorMasterDTO.getPin() == null || StringUtils.isEmpty(vendorMasterDTO.getPin())) {
         messageList.add(new Message("Pin Code", "Pin Code can't be null or empty"));
      }

      if (vendorMasterDTO.getEmpId() == null || StringUtils.isEmpty(vendorMasterDTO.getEmpId())) {
         messageList.add(new Message("Emp ID", "Emp ID can't be null or empty"));
      }

      return messageList;
   }
}
