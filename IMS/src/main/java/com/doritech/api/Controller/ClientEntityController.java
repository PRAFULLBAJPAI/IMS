    package com.doritech.api.Controller;

import com.doritech.api.DTO.ClientEntityDTO;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Service.ClientEntityService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientEntityController {
   @Autowired
   ClientEntityService clientEntityService;

   @PostMapping({"addNewClientEntity"})
   @ResponseBody
   public ResponseEntity addNewClientEntity(@RequestBody ClientEntityDTO clientEntityDTO, HttpServletRequest httpServletRequest) throws Exception {
      return this.clientEntityService.addNewClientEntity(clientEntityDTO, httpServletRequest);
   }

   @GetMapping({"getAllClientDetails"})
   @ResponseBody
   public ResponseEntity getAllClientDetails() throws Exception {
      return this.clientEntityService.getAllClientDetails();
   }

   @GetMapping({"getClientDetailsById"})
   @ResponseBody
   public ResponseEntity getClientDetailsById(@RequestParam String clientId) throws Exception {
      return this.clientEntityService.getClientDetailsById(clientId);
   }

   @PutMapping({"deactivateClientByClientId"})
   @ResponseBody
   public ResponseEntity deactivateClientByClientId(String clientid) throws Exception {
      return this.clientEntityService.deactivateClientByClientId(clientid);
   }

   @PutMapping({"updateClientMaster"})
   @ResponseBody
   public ResponseEntity updateClientMaster(@RequestBody ClientEntityDTO clientEntityDTO, HttpServletRequest httpServletRequest) throws Exception {
      return this.clientEntityService.updateClientMaster(clientEntityDTO, httpServletRequest);
   }
}