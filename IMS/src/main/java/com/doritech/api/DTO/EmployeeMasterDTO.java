   package com.doritech.api.DTO;

public class EmployeeMasterDTO {
   private String empId;
   private String empName;
   private String empRole;
   private String empPan;
   private String address;
   private String city;
   private String pin;
   private String state;
   private String country;
   private String mobile;
   private String email;
   private String status;
   private String branch;
   private String password;
   private String loginFlag;
   private String groupId;

   public EmployeeMasterDTO() {
   }

   public EmployeeMasterDTO(String empId, String empName, String empRole, String empPan, String address, String city, String pin, String state, String country, String mobile, String email, String status, String branch, String password, String loginFlag, String groupId) {
      this.empId = empId;
      this.empName = empName;
      this.empRole = empRole;
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
      this.password = password;
      this.loginFlag = loginFlag;
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
      return "EmployeeMasterDTO{empId='" + this.empId + '\'' + ", empName='" + this.empName + '\'' + ", empRole='" + this.empRole + '\'' + ", empPan='" + this.empPan + '\'' + ", address='" + this.address + '\'' + ", city='" + this.city + '\'' + ", pin='" + this.pin + '\'' + ", state='" + this.state + '\'' + ", country='" + this.country + '\'' + ", mobile='" + this.mobile + '\'' + ", email='" + this.email + '\'' + ", status='" + this.status + '\'' + ", branch='" + this.branch + '\'' + ", password='" + this.password + '\'' + ", loginFlag='" + this.loginFlag + '\'' + ", groupId='" + this.groupId + '\'' + '}';
   }
}