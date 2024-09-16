package com.doritech.api.Service;

import com.doritech.api.Common.General;
import com.doritech.api.Common.Message;
import com.doritech.api.DTO.GroupMasterDTO;
import com.doritech.api.Entity.GroupMasterEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.EmployeeMasterRepository;
import com.doritech.api.Repository.GroupMasterRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GroupMasterService {
   @Autowired
   private GroupMasterRepository groupMasterRepo;
   @Autowired
   General general;
   @Autowired
   EmployeeMasterRepository employeeMasterRepository;

   public ResponseEntity saveOrUpdateGroupMaster(GroupMasterDTO groupMasterDTO) {
      ResponseEntity response = new ResponseEntity();

      try {
         List<Message> validationErrorList = this.validationList(groupMasterDTO);
         if (validationErrorList.isEmpty()) {
            Optional<GroupMasterEntity> existingGroup = Optional.empty();
            String groupId = "";
            GroupMasterEntity groupEntity;
            if (!existingGroup.isPresent()) {
               groupEntity = new GroupMasterEntity();
               BeanUtils.copyProperties(groupMasterDTO, groupEntity);
               this.groupMasterRepo.save(groupEntity);
               response.setPayload(groupEntity);
               response.setMessage("Group Master saved successfully.");
               response.setStatusCode(HttpStatus.OK.value());
            } else {
               groupEntity = (GroupMasterEntity)existingGroup.get();
               BeanUtils.copyProperties(groupMasterDTO, groupEntity);
               this.groupMasterRepo.save(groupEntity);
               response.setPayload(groupEntity);
               response.setMessage("Group Master updated successfully.");
               response.setStatusCode(HttpStatus.OK.value());
            }
         } else {
            response.setMessage("Validation failed");
            response.setPayload(validationErrorList);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
         }
      } catch (Exception var7) {
         var7.printStackTrace();
         response.setMessage("Group Master save failed: " + var7.getLocalizedMessage());
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getGroupByGroupId(String groupId) {
      ResponseEntity response = new ResponseEntity();

      try {
         GroupMasterEntity groupMaster = this.groupMasterRepo.findByGroupId(String.valueOf(groupId));
         if (groupMaster != null) {
            GroupMasterDTO groupMasterResponse = new GroupMasterDTO();
            groupMasterResponse.setGroupId(groupMaster.getGroupId());
            groupMasterResponse.setGroupName(this.general.checkNull(groupMaster.getGroupName()));
            groupMasterResponse.setGroupDescription(this.general.checkNull(groupMaster.getGroupDescription()));
            groupMasterResponse.setCreatedDate(groupMaster.getCreatedDate());
            groupMasterResponse.setEmpId(this.general.checkNull(groupMaster.getEmpId()));
            groupMasterResponse.setEmpName(this.employeeMasterRepository.findByEmpId(groupMaster.getEmpId()).getEmpName());
            System.out.println(groupMasterResponse);
            response.setPayload(groupMasterResponse);
            response.setMessage("Group details fetched ");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("Group details not found");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var5) {
         var5.printStackTrace();
         response.setMessage("Group details fetch failed:-" + var5.getLocalizedMessage());
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
      }

      return response;
   }

   public ResponseEntity getAllGroupMaster() {
      ResponseEntity response = new ResponseEntity();

      try {
         List<GroupMasterEntity> groupList = this.groupMasterRepo.findAll();
         List<GroupMasterDTO> groupResponseList = new ArrayList();
         if (!groupList.isEmpty()) {
            Iterator var4 = groupList.iterator();

            while(var4.hasNext()) {
               GroupMasterEntity group = (GroupMasterEntity)var4.next();
               GroupMasterDTO groupResponse = new GroupMasterDTO();
               groupResponse.setGroupId(group.getGroupId());
               groupResponse.setGroupName(this.general.checkNull(group.getGroupName()));
               groupResponse.setGroupDescription(this.general.checkNull(group.getGroupDescription()));
               groupResponse.setCreatedDate(group.getCreatedDate());
               groupResponse.setEmpId(this.general.checkNull(group.getEmpId()));
               groupResponse.setEmpName(this.employeeMasterRepository.findByEmpId(group.getEmpId()).getEmpName());
               groupResponseList.add(groupResponse);
            }

            response.setPayload(groupResponseList);
            response.setMessage("All Group details fetched ");
            response.setStatusCode(HttpStatus.OK);
         } else {
            response.setMessage("No record found for Groups");
            response.setStatusCode(HttpStatus.NOT_FOUND);
         }
      } catch (Exception var7) {
         var7.printStackTrace();
         response.setMessage("Group details fetch failed:-" + var7.getLocalizedMessage());
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
      }

      return response;
   }

   public List<Message> validationList(GroupMasterDTO groupMasterDTO) {
      List<Message> validateErrorMessages = new ArrayList();
      if (groupMasterDTO.getGroupName() == null || groupMasterDTO.getGroupName().isEmpty()) {
         validateErrorMessages.add(new Message("Group Name", "Group name can't be null"));
      }

      if (groupMasterDTO.getGroupDescription() == null || groupMasterDTO.getGroupDescription().isEmpty()) {
         validateErrorMessages.add(new Message("Group Description", "Group Description can't be null"));
      }

      if (groupMasterDTO.getCreatedDate() == null) {
         validateErrorMessages.add(new Message("Created Date", "Creation date can't be null"));
      }

      if (groupMasterDTO.getEmpId() == null || groupMasterDTO.getEmpId().isEmpty()) {
         validateErrorMessages.add(new Message("empId", "Employee ID can't be null"));
      }

      return validateErrorMessages;
   }
}
