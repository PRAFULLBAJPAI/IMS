package com.doritech.api.Service;

import com.doritech.api.Common.General;
import com.doritech.api.Common.Message;
import com.doritech.api.DTO.BranchMasterDTO;
import com.doritech.api.Entity.BranchMasterEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.BranchMasterRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BranchMasterService {
   @Autowired
   private BranchMasterRepository branchMasterRepository;
   @Autowired
   private General general;

   public ResponseEntity addBranchInformation(BranchMasterDTO branchMasterDTO) {
      ResponseEntity response = new ResponseEntity();

      try {
         List<Message> validationError = this.validation(branchMasterDTO);
         if (!validationError.isEmpty()) {
            response.setPayload(validationError);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Branch Master can't be added. Please fill correct Data");
         } else {
            BranchMasterEntity branchMasterEntity = new BranchMasterEntity();
            branchMasterEntity.setBranchCode(this.general.checkNull(branchMasterDTO.getBranchCode()));
            branchMasterEntity.setBranchName(this.general.checkNull(branchMasterDTO.getBranchName()));
            branchMasterEntity.setAddress(this.general.checkNull(branchMasterDTO.getAddress()));
            branchMasterEntity.setCity(this.general.checkNull(branchMasterDTO.getCity()));
            branchMasterEntity.setPin(this.general.checkNull(branchMasterDTO.getPin()));
            branchMasterEntity.setState(this.general.checkNull(branchMasterDTO.getState()));
            branchMasterEntity.setCountry(this.general.checkNull(branchMasterDTO.getCountry()));
            branchMasterEntity.setMobile(this.general.checkNull(branchMasterDTO.getMobile()));
            branchMasterEntity.setEmail(this.general.checkNull(branchMasterDTO.getEmail()));
            this.branchMasterRepository.save(branchMasterEntity);
            response.setPayload(branchMasterEntity);
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Branch Master added successfully.");
         }
      } catch (Exception var5) {
         var5.printStackTrace();
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         response.setMessage("Branch Master can't be added in the database.");
      }

      return response;
   }

   public ResponseEntity getAllBranchInformation() {
      ResponseEntity response = new ResponseEntity();

      try {
         List<BranchMasterEntity> branchMasterEntityList = this.branchMasterRepository.findAll();
         if (!branchMasterEntityList.isEmpty()) {
            List<BranchMasterDTO> branchMasterResponseList = new ArrayList();
            Iterator var4 = branchMasterEntityList.iterator();

            while(var4.hasNext()) {
               BranchMasterEntity branchMasterEntity = (BranchMasterEntity)var4.next();
               BranchMasterDTO branchMasterResponse = new BranchMasterDTO();
               branchMasterResponse.setBranchCode(branchMasterEntity.getBranchCode());
               branchMasterResponse.setBranchName(branchMasterEntity.getBranchName());
               branchMasterResponse.setAddress(branchMasterEntity.getAddress());
               branchMasterResponse.setCity(branchMasterEntity.getCity());
               branchMasterResponse.setPin(branchMasterEntity.getPin());
               branchMasterResponse.setState(branchMasterEntity.getState());
               branchMasterResponse.setCountry(branchMasterEntity.getCountry());
               branchMasterResponse.setMobile(branchMasterEntity.getMobile());
               branchMasterResponse.setEmail(branchMasterEntity.getEmail());
               branchMasterResponseList.add(branchMasterResponse);
            }

            response.setPayload(branchMasterResponseList);
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("All Branch Information fetched successfully.");
         } else {
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("No Branch Information found.");
         }
      } catch (Exception var7) {
         var7.printStackTrace();
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         response.setMessage("Failed to retrieve branch information.");
      }

      return response;
   }

   public ResponseEntity getBranchById(String branchCode) {
      ResponseEntity response = new ResponseEntity();

      try {
         Optional<BranchMasterEntity> branchMasterEntityOptional = this.branchMasterRepository.findById(branchCode);
         if (branchMasterEntityOptional.isPresent()) {
            BranchMasterEntity branchMasterEntity = (BranchMasterEntity)branchMasterEntityOptional.get();
            BranchMasterDTO branchMasterResponse = new BranchMasterDTO();
            branchMasterResponse.setBranchCode(branchMasterEntity.getBranchCode());
            branchMasterResponse.setBranchName(branchMasterEntity.getBranchName());
            branchMasterResponse.setAddress(branchMasterEntity.getAddress());
            branchMasterResponse.setCity(branchMasterEntity.getCity());
            branchMasterResponse.setPin(branchMasterEntity.getPin());
            branchMasterResponse.setState(branchMasterEntity.getState());
            branchMasterResponse.setCountry(branchMasterEntity.getCountry());
            branchMasterResponse.setMobile(branchMasterEntity.getMobile());
            branchMasterResponse.setEmail(branchMasterEntity.getEmail());
            response.setPayload(branchMasterResponse);
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Branch Information fetched successfully.");
         } else {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Branch Information cannot be retrieved");
         }

         return response;
      } catch (Exception var6) {
         var6.printStackTrace();
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         response.setMessage("Branch Information cannot br retrieved");
         throw new RuntimeException("Failed to get branch information by ID.", var6);
      }
   }

   public ResponseEntity updateBranchInformation(BranchMasterDTO branchMasterDTO) {
      ResponseEntity response = new ResponseEntity();

      try {
         BranchMasterEntity branchMasterEntity = (BranchMasterEntity)this.branchMasterRepository.findById(branchMasterDTO.getBranchCode()).orElseThrow(() -> {
            return new NotFoundException("Organization not found with ID: " + branchMasterDTO.getBranchCode());
         });
         branchMasterEntity.setBranchCode(this.general.checkNull(branchMasterDTO.getBranchCode()));
         branchMasterEntity.setBranchName(this.general.checkNull(branchMasterDTO.getBranchName()));
         branchMasterEntity.setAddress(this.general.checkNull(branchMasterDTO.getAddress()));
         branchMasterEntity.setCity(this.general.checkNull(branchMasterDTO.getCity()));
         branchMasterEntity.setPin(this.general.checkNull(branchMasterDTO.getPin()));
         branchMasterEntity.setState(this.general.checkNull(branchMasterDTO.getState()));
         branchMasterEntity.setCountry(this.general.checkNull(branchMasterDTO.getCountry()));
         branchMasterEntity.setMobile(this.general.checkNull(branchMasterDTO.getMobile()));
         branchMasterEntity.setEmail(this.general.checkNull(branchMasterDTO.getEmail()));
         BranchMasterEntity updatedOrganizationEntity = (BranchMasterEntity)this.branchMasterRepository.save(branchMasterEntity);
         response.setPayload(updatedOrganizationEntity);
         response.setStatusCode(HttpStatus.OK.value());
         response.setMessage("Branch Master updated successfully.");
         return response;
      } catch (NotFoundException var5) {
         var5.printStackTrace();
         throw new RuntimeException("Failed to update branch information.", var5);
      }
   }

   public void deleteBranchInformation(String branchCode) {
      try {
         BranchMasterEntity branchMasterEntity = (BranchMasterEntity)this.branchMasterRepository.findById(branchCode).orElseThrow(() -> {
            return new NotFoundException("Branch not found with ID: " + branchCode);
         });
         this.branchMasterRepository.delete(branchMasterEntity);
      } catch (NotFoundException var3) {
         var3.printStackTrace();
         throw new RuntimeException("Failed to delete branch information.", var3);
      }
   }

   public List<Message> validation(BranchMasterDTO branchMasterDTO) {
      List<Message> validateError = new ArrayList();
      if (branchMasterDTO.getBranchCode() == null || branchMasterDTO.getBranchCode().isEmpty()) {
         validateError.add(new Message("OrganizationName", " Organization name cannot be null"));
      }

      if (branchMasterDTO.getBranchName() == null || branchMasterDTO.getBranchName().isEmpty()) {
         validateError.add(new Message("Controlling Office", " Controlling Office cannot be null"));
      }

      if (branchMasterDTO.getAddress() == null || branchMasterDTO.getAddress().isEmpty()) {
         validateError.add(new Message("address1", " Address1 cannot be null"));
      }

      if (branchMasterDTO.getCity() == null || branchMasterDTO.getCity().isEmpty()) {
         validateError.add(new Message("city", "City cannot be null"));
      }

      if (branchMasterDTO.getPin() == null || branchMasterDTO.getPin().isEmpty()) {
         validateError.add(new Message("pin", " Pin cannot be null"));
      }

      if (branchMasterDTO.getState() == null || branchMasterDTO.getState().isEmpty()) {
         validateError.add(new Message("state", " State cannot be null"));
      }

      if (branchMasterDTO.getCountry() == null || branchMasterDTO.getCountry().isEmpty()) {
         validateError.add(new Message("country", "Country  cannot be null"));
      }

      if (branchMasterDTO.getMobile() == null || branchMasterDTO.getMobile().isEmpty()) {
         validateError.add(new Message("mobile", " Mobile cannot be null"));
      }

      if (branchMasterDTO.getEmail() == null || branchMasterDTO.getEmail().isEmpty()) {
         validateError.add(new Message("email", " email cannot be null"));
      }

      return validateError;
   }
}
