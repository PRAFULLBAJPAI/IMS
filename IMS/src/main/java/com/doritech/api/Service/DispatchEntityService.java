package com.doritech.api.Service;

import com.doritech.api.DTO.DispatchEntityDTO;
import com.doritech.api.Entity.ClientEntity;
import com.doritech.api.Entity.DispatchEntity;
import com.doritech.api.Entity.ItemMasterEntity;
import com.doritech.api.Entity.ParamEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.ClientEntityRepository;
import com.doritech.api.Repository.DispatchEntityRepository;
import com.doritech.api.Repository.ItemMasterRepository;
import com.doritech.api.Repository.ParamRepository;
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
public class DispatchEntityService {
   @Autowired
   DispatchEntityRepository dispatchEntityRepository;
   @Autowired
   ParamRepository paramRepository;
   @Autowired
   ItemMasterRepository itemMasterRepository;
   @Autowired
   ClientEntityRepository clientEntityRepository;

   public ResponseEntity addMultipleDispatchLogs(List<DispatchEntityDTO> dispatchEntityDTOs, HttpServletRequest httpServletRequest) {
      ResponseEntity response = new ResponseEntity();

      try {
         List<DispatchEntityDTO> responseList = new ArrayList();
         ParamEntity paramEntity = this.paramRepository.findByCode("DISPATCH_ID");
         int id = Integer.parseInt(paramEntity.getDescp2());
         if (dispatchEntityDTOs != null) {
            Iterator var7 = dispatchEntityDTOs.iterator();

            while(var7.hasNext()) {
               DispatchEntityDTO dispatchEntityDTO = (DispatchEntityDTO)var7.next();
               dispatchEntityDTO.setDispatchId(paramEntity.getDescp1() + String.valueOf(id));
               DispatchEntity dispatch = new DispatchEntity();
               BeanUtils.copyProperties(dispatchEntityDTO, dispatch);
               dispatch.setTerminal(httpServletRequest.getRemoteAddr());
               dispatch.setEntryDate(new Date());
               dispatch.setQuantityInStock(((ItemMasterEntity)this.itemMasterRepository.getById(dispatchEntityDTO.getItemCode())).getQuantityInStock());
               ItemMasterEntity item = (ItemMasterEntity)this.itemMasterRepository.getById(dispatchEntityDTO.getItemCode());
               item.setQuantityInStock(item.getQuantityInStock() - dispatchEntityDTO.getQuantity());
               this.itemMasterRepository.save(item);
               this.dispatchEntityRepository.save(dispatch);
               DispatchEntityDTO dispatchDto = new DispatchEntityDTO();
               BeanUtils.copyProperties(dispatch, dispatchDto);
               responseList.add(dispatchDto);
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
      } catch (Exception var12) {
         response.setMessage("Error adding dispatch logs to the database. Please try again later.");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getAllDispatchEntity() throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         List<DispatchEntity> dispatchList = this.dispatchEntityRepository.findAll();
         if (dispatchList.isEmpty()) {
            response.setMessage("No data to fetch");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }

         List<DispatchEntityDTO> responseList = new ArrayList();
         Iterator var4 = dispatchList.iterator();

         while(var4.hasNext()) {
            DispatchEntity dispatchEntity = (DispatchEntity)var4.next();
            DispatchEntityDTO dispatchEntityDTO = new DispatchEntityDTO();
            BeanUtils.copyProperties(dispatchEntity, dispatchEntityDTO);
            dispatchEntityDTO.setItemName(((ItemMasterEntity)this.itemMasterRepository.getById(dispatchEntity.getItemCode())).getItemName());
            dispatchEntityDTO.setClientName(((ClientEntity)this.clientEntityRepository.getById(dispatchEntity.getClientId())).getClientName());
            responseList.add(dispatchEntityDTO);
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
}
