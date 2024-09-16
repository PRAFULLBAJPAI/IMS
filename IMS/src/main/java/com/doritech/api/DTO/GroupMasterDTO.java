 package com.doritech.api.DTO;

import java.sql.Date;

public class GroupMasterDTO {
   private String groupId;
   private String groupName;
   private String groupDescription;
   private Date createdDate;
   private String empId;
   private String empName;

   public GroupMasterDTO() {
   }

   public GroupMasterDTO(String groupId, String groupName, String groupDescription, Date createdDate, String empId, String empName) {
      this.groupId = groupId;
      this.groupName = groupName;
      this.groupDescription = groupDescription;
      this.createdDate = createdDate;
      this.empId = empId;
      this.empName = empName;
   }

   public String getGroupId() {
      return this.groupId;
   }

   public void setGroupId(String groupId) {
      this.groupId = groupId;
   }

   public String getGroupName() {
      return this.groupName;
   }

   public void setGroupName(String groupName) {
      this.groupName = groupName;
   }

   public String getGroupDescription() {
      return this.groupDescription;
   }

   public void setGroupDescription(String groupDescription) {
      this.groupDescription = groupDescription;
   }

   public Date getCreatedDate() {
      return this.createdDate;
   }

   public void setCreatedDate(Date createdDate) {
      this.createdDate = createdDate;
   }

   public String getEmpId() {
      return this.empId;
   }

   public void setEmpId(String empId) {
      this.empId = empId;
   }

   public String getEmpName() {
      return this.empName;
   }

   public void setEmpName(String empName) {
      this.empName = empName;
   }

   public String toString() {
      return "GroupMasterDTO{groupId='" + this.groupId + '\'' + ", groupName='" + this.groupName + '\'' + ", groupDescription='" + this.groupDescription + '\'' + ", createdDate=" + this.createdDate + ", empId='" + this.empId + '\'' + ", empName='" + this.empName + '\'' + '}';
   }
}