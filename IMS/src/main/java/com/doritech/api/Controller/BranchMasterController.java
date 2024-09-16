package com.doritech.api.Controller;

import com.doritech.api.DTO.BranchMasterDTO;
import com.doritech.api.Entity.ParamEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.ParamRepository;
import com.doritech.api.Service.BranchMasterService;
import com.doritech.api.Service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BranchMasterController {
   @Autowired
   private BranchMasterService branchMasterService;
   @Autowired
   private ParamService paramService;
   @Autowired
   private ParamRepository paramRepository;

   @GetMapping({"getBranchById/{branchCode}"})
   @ResponseBody
   public ResponseEntity getBranchById(@RequestBody @PathVariable String branchCode) {
      return this.branchMasterService.getBranchById(branchCode);
   }

   @PostMapping({"addBranchMaster"})
   @ResponseBody
   public ResponseEntity addBranchMasterInformation(@RequestBody BranchMasterDTO branchMasterDTO) throws Exception {
      ParamEntity paramEntity = this.paramRepository.findByCode("BRANCH_ID");
      branchMasterDTO.setBranchCode(this.paramService.getBranchDescpValue());
      this.paramService.updateBranchDescp2Value(paramEntity);
      return this.branchMasterService.addBranchInformation(branchMasterDTO);
   }

   @GetMapping({"getAllBranchMasters"})
   @ResponseBody
   public ResponseEntity getAllBranchMasters() {
      return this.branchMasterService.getAllBranchInformation();
   }
}
