package com.doritech.api.Service;

import com.doritech.api.Common.Message;
import com.doritech.api.DTO.RawItemsIssueDTO;
import com.doritech.api.Entity.ItemMasterEntity;
import com.doritech.api.Entity.ParamEntity;
import com.doritech.api.Entity.RawItemsIssueEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.ItemMasterRepository;
import com.doritech.api.Repository.ParamRepository;
import com.doritech.api.Repository.RawItemIssueRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RawItemIssueService {
   @Autowired
   RawItemIssueRepository rawItemIssueRepository;
   @Autowired
   ItemMasterRepository itemMasterRepository;
   @Autowired
   ParamRepository paramRepository;

   public ResponseEntity issueMultipleWithBOM(List<RawItemsIssueDTO> rawItemsIssueDTOs, HttpServletRequest httpServletRequest) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         if (rawItemsIssueDTOs.isEmpty()) {
            response.setMessage("No data to save");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }

         List<List<Message>> vlidationList = this.validations(rawItemsIssueDTOs);
         if (!vlidationList.isEmpty()) {
            response.setPayload(vlidationList);
            response.setMessage("Validations failed");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
         }

         ParamEntity paramEntity = this.paramRepository.findByCode("RAW_ISSUE_ID");
         List<RawItemsIssueEntity> responseList = new ArrayList();
         Iterator var7 = rawItemsIssueDTOs.iterator();

         while(var7.hasNext()) {
            RawItemsIssueDTO rawItemsIssueDTO = (RawItemsIssueDTO)var7.next();
            RawItemsIssueEntity rawItemsIssueEntity = new RawItemsIssueEntity();
            if (!(((ItemMasterEntity)this.itemMasterRepository.getById(rawItemsIssueDTO.getItemCode())).getMinimumStock() <= ((ItemMasterEntity)this.itemMasterRepository.getById(rawItemsIssueDTO.getItemCode())).getQuantityInStock())) {
               break;
            }

            BeanUtils.copyProperties(rawItemsIssueDTO, rawItemsIssueEntity);
            rawItemsIssueEntity.setItemName(((ItemMasterEntity)this.itemMasterRepository.getById(rawItemsIssueDTO.getItemCode())).getItemName());
            rawItemsIssueEntity.setQuantityInStock(((ItemMasterEntity)this.itemMasterRepository.getById(rawItemsIssueDTO.getItemCode())).getQuantityInStock());
            rawItemsIssueEntity.setMinimumQuantity(((ItemMasterEntity)this.itemMasterRepository.getById(rawItemsIssueDTO.getItemCode())).getMinimumStock());
            rawItemsIssueEntity.setEntryDate(new Date());
            rawItemsIssueEntity.setTerminal(httpServletRequest.getRemoteAddr());
            ItemMasterEntity itemMasterEntity = (ItemMasterEntity)this.itemMasterRepository.getById(rawItemsIssueDTO.getItemCode());
            itemMasterEntity.setQuantityInStock(itemMasterEntity.getQuantityInStock() - rawItemsIssueDTO.getItemQuantity());
            this.itemMasterRepository.save(itemMasterEntity);
            responseList.add(rawItemsIssueEntity);
            this.rawItemIssueRepository.save(rawItemsIssueEntity);
         }

         paramEntity.setDescp2(String.valueOf(Integer.parseInt(paramEntity.getDescp2()) + 1));
         this.paramRepository.save(paramEntity);
         response.setPayload(responseList);
         response.setMessage("Data issued successful");
         response.setStatusCode(HttpStatus.OK.value());
      } catch (Exception var11) {
         var11.printStackTrace();
         response.setMessage("Error issuing raw items. " + var11.getLocalizedMessage());
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public List<List<Message>> validations(List<RawItemsIssueDTO> rawItemsIssueDTOs) {
      List<List<Message>> responseList = new ArrayList();

      ArrayList messageList;
      for(Iterator var3 = rawItemsIssueDTOs.iterator(); var3.hasNext(); responseList.add(messageList)) {
         RawItemsIssueDTO rawItemsIssueDTO = (RawItemsIssueDTO)var3.next();
         messageList = new ArrayList();
         if (rawItemsIssueDTO.getItemCode() == null || StringUtils.isEmpty(rawItemsIssueDTO.getItemCode())) {
            messageList.add(new Message("Item Code", "Item Code can't be null or empty"));
         }

         if (rawItemsIssueDTO.getConsumptionFor() == null || StringUtils.isEmpty(rawItemsIssueDTO.getConsumptionFor())) {
            messageList.add(new Message("Consumption For", "Consumption For can't be null or empty"));
         }

         if (rawItemsIssueDTO.getConsumptionType() == null || StringUtils.isEmpty(rawItemsIssueDTO.getConsumptionType())) {
            messageList.add(new Message("Consumption Type", "Consumption Type can't be null or empty"));
         }

         if (rawItemsIssueDTO.getItemQuantity() == 0.0D) {
            messageList.add(new Message("Item Qty", "Item Qty can't be null or empty"));
         }
      }

      return responseList;
   }

   public ResponseEntity getAllRawItemsReport() throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         List<RawItemsIssueEntity> rawItemsIssueList = this.rawItemIssueRepository.findAll();
         if (rawItemsIssueList.isEmpty()) {
            response.setMessage("No data is present in the database.");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }

         List<RawItemsIssueDTO> responseList = new ArrayList();
         Iterator var4 = rawItemsIssueList.iterator();

         while(var4.hasNext()) {
            RawItemsIssueEntity rawItem = (RawItemsIssueEntity)var4.next();
            RawItemsIssueDTO rawItemsIssueDTO = new RawItemsIssueDTO();
            BeanUtils.copyProperties(rawItem, rawItemsIssueDTO);
            responseList.add(rawItemsIssueDTO);
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
