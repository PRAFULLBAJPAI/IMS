package com.doritech.api.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
   name = "employee_master"
)
public class EmployeeMasterEntity {
   @Id
   @Column(
      name = "emp_id"
   )
   private String empId;
   @Column(
      name = "emp_role"
   )
   private String empRole;
   @Column(
      name = "emp_name"
   )
   private String empName;
   @Column(
      name = "emp_pan"
   )
   private String empPan;
   @Column(
      name = "address"
   )
   private String address;
   @Column(
      name = "city"
   )
   private String city;
   @Column(
      name = "pin"
   )
   private String pin;
   @Column(
      name = "state"
   )
   private String state;
   @Column(
      name = "country"
   )
   private String country;
   @Column(
      name = "mobile"
   )
   private String mobile;
   @Column(
      name = "email"
   )
   private String email;
   @Column(
      name = "status"
   )
   private String status;
   @Column(
      name = "branch"
   )
   private String branch;
   @Column(
      name = "login_flag"
   )
   private String loginFlag;
   @Column(
      name = "password"
   )
   private String password;
   @Column(
      name = "group_id"
   )
   private String groupId;

   public EmployeeMasterEntity() {
   }

   public EmployeeMasterEntity(String empId, String empRole, String empName, String empPan, String address, String city, String pin, String state, String country, String mobile, String email, String status, String branch, String loginFlag, String password, String groupId) {
      this.empId = empId;
      this.empRole = empRole;
      this.empName = empName;
      this.empPan = empPan;
      this.address = address;
      this.city = city;
      this.pin = pin;
      this.state = state;
      this.country = country;
      this.mobile = mobile;
      this.email = email;
      this.status = status;
      this.branch = branch;
      this.loginFlag = loginFlag;
      this.password = password;
      this.groupId = groupId;
   }

   public String getEmpId() {
      return this.empId;
   }

   public void setEmpId(String empId) {
      this.empId = empId;
   }

   public String getEmpRole() {
      return this.empRole;
   }

   public void setEmpRole(String empRole) {
      this.empRole = empRole;
   }

   public String getEmpName() {
      return this.empName;
   }

   public void setEmpName(String empName) {
      this.empName = empName;
   }

   public String getEmpPan() {
      return this.empPan;
   }

   public void setEmpPan(String empPan) {
      this.empPan = empPan;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getCity() {
      return this.city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getPin() {
      return this.pin;
   }

   public void setPin(String pin) {
      this.pin = pin;
   }

   public String getState() {
      return this.state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getCountry() {
      return this.country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public String getMobile() {
      return this.mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getBranch() {
      return this.branch;
   }

   public void setBranch(String branch) {
      this.branch = branch;
   }

   public String getLoginFlag() {
      return this.loginFlag;
   }

   public void setLoginFlag(String loginFlag) {
      this.loginFlag = loginFlag;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getGroupId() {
      return this.groupId;
   }

   public void setGroupId(String groupId) {
      this.groupId = groupId;
   }

   public String toString() {
      return "EmployeeMasterEntity{empId='" + this.empId + '\'' + ", empRole='" + this.empRole + '\'' + ", empName='" + this.empName + '\'' + ", empPan='" + this.empPan + '\'' + ", address='" + this.address + '\'' + ", city='" + this.city + '\'' + ", pin='" + this.pin + '\'' + ", state='" + this.state + '\'' + ", country='" + this.country + '\'' + ", mobile='" + this.mobile + '\'' + ", email='" + this.email + '\'' + ", status='" + this.status + '\'' + ", branch='" + this.branch + '\'' + ", loginFlag='" + this.loginFlag + '\'' + ", password='" + this.password + '\'' + ", groupId='" + this.groupId + '\'' + '}';
   }
}
