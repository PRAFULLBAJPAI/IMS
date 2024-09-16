  package com.doritech.api.Controller;

import com.doritech.api.DTO.DispatchEntityDTO;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Service.DispatchEntityService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DispatchEntityController {
   @Autowired
   DispatchEntityService dispatchEntityService;

   @PostMapping({"addMultipleDispatchLogs"})
   @ResponseBody
   public ResponseEntity addMultipleDispatchLogs(@RequestBody List<DispatchEntityDTO> dispatchEntityDTOs, HttpServletRequest httpServletRequest) {
      return this.dispatchEntityService.addMultipleDispatchLogs(dispatchEntityDTOs, httpServletRequest);
   }

   @GetMapping({"getAllDispatchEntity"})
   @ResponseBody
   public ResponseEntity getAllDispatchEntity() throws Exception {
      return this.dispatchEntityService.getAllDispatchEntity();
   }
}