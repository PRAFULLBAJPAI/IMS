package com.doritech.api.Service;

import com.doritech.api.DTO.HsnMasterDTO;
import com.doritech.api.Entity.HsnMasterEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.HsnMasterRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class HsnMasterService {
   @Autowired
   HsnMasterRepository hsnMasterRepository;

   public ResponseEntity getAllHsnMaster() throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         List<HsnMasterEntity> hsnMasterList = this.hsnMasterRepository.findAll();
         if (hsnMasterList != null) {
            List<HsnMasterDTO> responseList = new ArrayList();
            Iterator var4 = hsnMasterList.iterator();

            while(var4.hasNext()) {
               HsnMasterEntity entity = (HsnMasterEntity)var4.next();
               HsnMasterDTO hsnMasterDTO = new HsnMasterDTO();
               BeanUtils.copyProperties(entity, hsnMasterDTO);
               responseList.add(hsnMasterDTO);
            }

            response.setPayload(responseList);
            response.setMessage("All Hsn Master Entity fetched successfully");
            response.setStatusCode(HttpStatus.FOUND.value());
         } else {
            response.setMessage("Sorry! There is no data present in the database");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var7) {
         var7.printStackTrace();
         response.setMessage("Error fetching All Hsn Master Entity");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getMsnMasterById(String hsnCode) {
      ResponseEntity response = new ResponseEntity();

      try {
         HsnMasterEntity entity = (HsnMasterEntity)this.hsnMasterRepository.getById(hsnCode);
         if (entity != null) {
            HsnMasterDTO hsnMasterDTO = new HsnMasterDTO();
            BeanUtils.copyProperties(entity, hsnMasterDTO);
            response.setPayload(hsnMasterDTO);
            response.setMessage("Hsn Master with given Hsn Code has fetched successfully.");
            response.setStatusCode(HttpStatus.FOUND.value());
         } else {
            response.setMessage("No Hsn Master is present with given Hsn Code in the database");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var5) {
         var5.printStackTrace();
         response.setMessage("Error fetching Hsn Master Entity with given HsnCode");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }
}
