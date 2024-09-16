 package com.doritech.api.Controller;

import com.doritech.api.DTO.VendorMasterDTO;
import com.doritech.api.Entity.ParamEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.ParamRepository;
import com.doritech.api.Repository.VendorMasterRepository;
import com.doritech.api.Service.ParamService;
import com.doritech.api.Service.VendorMasterService;
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
public class VendorMasterController {
   @Autowired
   VendorMasterService vendorMasterService;
   @Autowired
   VendorMasterRepository vendorMasterRepository;
   @Autowired
   ParamRepository paramRepository;
   @Autowired
   ParamService paramService;

   @GetMapping({"getAllVendorMaster"})
   @ResponseBody
   public ResponseEntity getAllVendorMaster() throws Exception {
      return this.vendorMasterService.getAllVendorMaster();
   }

   @GetMapping({"getVendorMasterById"})
   @ResponseBody
   public ResponseEntity getVendorMasterById(@RequestParam("vendorId") String vendorId) throws Exception {
      return this.vendorMasterService.getVendorMasterById(vendorId);
   }

   @PutMapping({"deactivateVendorByVendorId"})
   @ResponseBody
   public ResponseEntity deactivateVendorByVendorId(@RequestParam("vendorId") String vendroId) throws Exception {
      return this.vendorMasterService.deactivateVendorByVendorId(vendroId);
   }

   @PostMapping({"saveVendorMaster"})
   @ResponseBody
   public ResponseEntity saveVendorMaster(@RequestBody VendorMasterDTO vendorMasterDTO, HttpServletRequest httpServletRequest) throws Exception {
      ParamEntity paramEntity = this.paramRepository.findByCode("VENDOR_ID");
      vendorMasterDTO.setVendorId(this.paramService.getVendorIdDescpValue());
      this.paramService.updateVendorIdDescp2Value(paramEntity);
      return this.vendorMasterService.saveVendorMaster(vendorMasterDTO, httpServletRequest);
   }

   @PutMapping({"updateVendorMaster"})
   @ResponseBody
   public ResponseEntity updateVendorMaster(@RequestBody VendorMasterDTO vendorMasterDTO, HttpServletRequest httpServletRequest) throws Exception {
      return this.vendorMasterService.updateVendorMaster(vendorMasterDTO, httpServletRequest);
   }
}
   