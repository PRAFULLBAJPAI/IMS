 package com.doritech.api.Controller;

import com.doritech.api.DTO.GroupMasterDTO;
import com.doritech.api.Entity.ParamEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.ParamRepository;
import com.doritech.api.Service.GroupMasterService;
import com.doritech.api.Service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class GroupMasterController {
   @Autowired
   public GroupMasterService groupMasterService;
   @Autowired
   private ParamService paramService;
   @Autowired
   private ParamRepository paramRepository;

   @GetMapping({"getGroupById"})
   @ResponseBody
   public ResponseEntity getGroupByGroupId(@RequestParam String groupId) throws Exception {
      return this.groupMasterService.getGroupByGroupId(groupId);
   }

   @GetMapping({"getAllGroupMaster"})
   @ResponseBody
   public ResponseEntity getAllGroupMaster() throws Exception {
      return this.groupMasterService.getAllGroupMaster();
   }

   @PostMapping({"saveOrUpdateGroupMaster"})
   @ResponseBody
   public ResponseEntity saveOrUpdateGroupMaster(@RequestBody GroupMasterDTO groupMasterDTO) throws Exception {
      ParamEntity paramEntity = this.paramRepository.findByCode("GROUP_ID");
      groupMasterDTO.setGroupId(this.paramService.getGroupDescpValue());
      this.paramService.updateGroupDescp2Value(paramEntity);
      return this.groupMasterService.saveOrUpdateGroupMaster(groupMasterDTO);
   }
}