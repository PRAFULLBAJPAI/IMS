package com.doritech.api.Entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
   name = "group_master"
)
public class GroupMasterEntity {
   @Id
   @Column(
      name = "group_id"
   )
   private String groupId;
   @Column(
      name = "group_name"
   )
   private String groupName;
   @Column(
      name = "group_description"
   )
   private String groupDescription;
   @Column(
      name = "created_date"
   )
   private Date createdDate;
   @Column(
      name = "emp_id"
   )
   private String empId;

   public GroupMasterEntity() {
   }

   public GroupMasterEntity(String groupId, String groupName, String groupDescription, Date createdDate, String empId) {
      this.groupId = groupId;
      this.groupName = groupName;
      this.groupDescription = groupDescription;
      this.createdDate = createdDate;
      this.empId = empId;
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

   public String toString() {
      return "GroupMasterEntity{groupId=" + this.groupId + ", groupName='" + this.groupName + '\'' + ", groupDescription='" + this.groupDescription + '\'' + ", createdDate=" + this.createdDate + ", empId=" + this.empId + '}';
   }
}
