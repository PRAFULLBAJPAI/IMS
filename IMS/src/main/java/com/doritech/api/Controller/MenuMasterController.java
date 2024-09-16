  package com.doritech.api.Controller;

import com.doritech.api.DTO.MenuMasterDTO;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Service.MenuMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MenuMasterController {
   @Autowired
   MenuMasterService menuMasterService;

   @PostMapping({"saveOrEditMenuDetails"})
   @ResponseBody
   public ResponseEntity saveOrEditMenuDetails(@RequestBody MenuMasterDTO menuMasterDTO) {
      return this.menuMasterService.saveOrEditMenuDetails(menuMasterDTO);
   }

   @GetMapping({"getAllMenuDetails"})
   @ResponseBody
   public ResponseEntity getAllMenuDetails() throws Exception {
      return this.menuMasterService.getAllMenuDetails();
   }
}
    