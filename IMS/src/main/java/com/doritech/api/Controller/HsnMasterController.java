 package com.doritech.api.Controller;

import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Service.HsnMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HsnMasterController {
   @Autowired
   HsnMasterService hsnMasterService;

   @GetMapping({"getAllHsnMaster"})
   @ResponseBody
   public ResponseEntity getAllHsnMaster() throws Exception {
      return this.hsnMasterService.getAllHsnMaster();
   }

   @GetMapping({"getMsnMasterById"})
   @ResponseBody
   public ResponseEntity getMsnMasterById(@RequestParam("hsnCode") String hsnCode) throws Exception {
      return this.hsnMasterService.getMsnMasterById(hsnCode);
   }
}