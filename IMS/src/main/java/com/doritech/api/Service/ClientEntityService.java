package com.doritech.api.Service;

import com.doritech.api.Common.Message;
import com.doritech.api.DTO.ClientEntityDTO;
import com.doritech.api.Entity.ClientEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.ClientEntityRepository;
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
public class ClientEntityService {
   @Autowired
   ClientEntityRepository clientEntityRepository;

   public ResponseEntity addNewClientEntity(ClientEntityDTO clientEntityDTO, HttpServletRequest httpServletRequest) {
      ResponseEntity response = new ResponseEntity();

      try {
         Optional<ClientEntity> existingClient = this.clientEntityRepository.findById(clientEntityDTO.getClientId());
         if (existingClient.isPresent()) {
            response.setMessage("Client already exists.");
            response.setStatusCode(HttpStatus.CONFLICT.value());
         } else {
            ClientEntity client = new ClientEntity();
            BeanUtils.copyProperties(clientEntityDTO, client);
            client.setTerminal(httpServletRequest.getRemoteAddr());
            client.setStatus("Active");
            ClientEntity savedClient = (ClientEntity)this.clientEntityRepository.save(client);
            response.setPayload(savedClient);
            response.setMessage("Client saved successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         }
      } catch (Exception var7) {
         var7.printStackTrace();
         response.setMessage("Error saving data");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getAllClientDetails() throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ClientEntity> clientList = this.clientEntityRepository.findAll();
         if (clientList.isEmpty()) {
            response.setMessage("No data to fetch");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }

         List<ClientEntityDTO> responseList = new ArrayList();
         Iterator var4 = clientList.iterator();

         while(var4.hasNext()) {
            ClientEntity clientEntity = (ClientEntity)var4.next();
            ClientEntityDTO clientEntityDTO = new ClientEntityDTO();
            BeanUtils.copyProperties(clientEntity, clientEntityDTO);
            responseList.add(clientEntityDTO);
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

   public ResponseEntity getClientDetailsById(String clientId) {
      ResponseEntity response = new ResponseEntity();

      try {
         ClientEntity entity = (ClientEntity)this.clientEntityRepository.getById(clientId);
         if (entity != null) {
            ClientEntityDTO clientEntityDTO = new ClientEntityDTO();
            BeanUtils.copyProperties(entity, clientEntityDTO);
            response.setPayload(clientEntityDTO);
            response.setMessage("Client Details with given Client Id has fetched successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("No Client Detail is present with given Client Id in the database");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var5) {
         var5.printStackTrace();
         response.setMessage("Error fetching data");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity deactivateClientByClientId(String clientId) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         Optional<ClientEntity> optionalItem = this.clientEntityRepository.findById(clientId);
         if (optionalItem.isPresent()) {
            ClientEntity entity = (ClientEntity)optionalItem.get();
            entity.setStatus("Inactive");
            this.clientEntityRepository.save(entity);
            response.setPayload(entity);
            response.setMessage("Client Deactivated Successfully.");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("Client with Id '" + clientId + "' not Found");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var5) {
         response.setMessage("Error Occurred. Please Contact the Administrator: " + var5.getLocalizedMessage());
         response.setStatusCode(HttpStatus.BAD_REQUEST.value());
      }

      return response;
   }

   public ResponseEntity updateClientMaster(ClientEntityDTO clientEntityDTO, HttpServletRequest httpServletRequest) {
      ResponseEntity response = new ResponseEntity();

      try {
         List<Message> validationMessages = this.validations(clientEntityDTO);
         if (validationMessages.isEmpty()) {
            Optional<ClientEntity> existingVendor = this.clientEntityRepository.findById(clientEntityDTO.getClientId());
            if (existingVendor.isPresent()) {
               ClientEntity entity = (ClientEntity)existingVendor.get();
               BeanUtils.copyProperties(clientEntityDTO, entity);
               this.clientEntityRepository.save(entity);
               response.setPayload(entity);
               response.setMessage("Client updated successfully.");
               response.setStatusCode(HttpStatus.OK.value());
            } else {
               response.setMessage("Client is not present in the database.");
               response.setStatusCode(HttpStatus.CONFLICT.value());
            }
         } else {
            response.setMessage("Client Not Added. Validation Failed.");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setPayload(validationMessages);
         }
      } catch (Exception var7) {
         response.setMessage("Error occurred while processing Client. Please Contact With Administrator!!");
         response.setStatusCode(HttpStatus.BAD_REQUEST.value());
      }

      return response;
   }

   public List<Message> validations(ClientEntityDTO clientEntityDTO) {
      List<Message> messageList = new ArrayList();
      if (clientEntityDTO.getClientName() == null || StringUtils.isEmpty(clientEntityDTO.getClientName())) {
         messageList.add(new Message("Vendor Name", "Client Name can't be null or empty"));
      }

      if (clientEntityDTO.getContactPerson() == null || StringUtils.isEmpty(clientEntityDTO.getContactPerson())) {
         messageList.add(new Message("Contact Person", "Contact Person can't be null or empty"));
      }

      if (clientEntityDTO.getEmail() == null || StringUtils.isEmpty(clientEntityDTO.getEmail())) {
         messageList.add(new Message("Email ID", "Email ID can't be null or empty"));
      }

      if (clientEntityDTO.getMobile() == null || StringUtils.isEmpty(clientEntityDTO.getMobile())) {
         messageList.add(new Message("Mobile Number", "Mobile Number can't be null or empty"));
      }

      if (clientEntityDTO.getAddress() == null || StringUtils.isEmpty(clientEntityDTO.getAddress())) {
         messageList.add(new Message("Address", "Address can't be null or empty"));
      }

      if (clientEntityDTO.getCity() == null || StringUtils.isEmpty(clientEntityDTO.getCity())) {
         messageList.add(new Message("City", "City can't be null or empty"));
      }

      if (clientEntityDTO.getState() == null || StringUtils.isEmpty(clientEntityDTO.getState())) {
         messageList.add(new Message("State", "State can't be null or empty"));
      }

      if (clientEntityDTO.getCountry() == null || StringUtils.isEmpty(clientEntityDTO.getCountry())) {
         messageList.add(new Message("Country", "Country can't be null or empty"));
      }

      if (clientEntityDTO.getPin() == null || StringUtils.isEmpty(clientEntityDTO.getPin())) {
         messageList.add(new Message("Pin Code", "Pin Code can't be null or empty"));
      }

      if (clientEntityDTO.getEmpId() == null || StringUtils.isEmpty(clientEntityDTO.getEmpId())) {
         messageList.add(new Message("Emp ID", "Emp ID can't be null or empty"));
      }

      return messageList;
   }
}
