 package com.doritech.api.DTO;

public class BranchMasterDTO {
   private String branchCode;
   private String branchName;
   private String address;
   private String city;
   private String pin;
   private String state;
   private String country;
   private String mobile;
   private String email;

   public BranchMasterDTO() {
   }

   public BranchMasterDTO(String branchCode, String branchName, String address, String city, String pin, String state, String country, String mobile, String email) {
      this.branchCode = branchCode;
      this.branchName = branchName;
      this.address = address;
      this.city = city;
      this.pin = pin;
      this.state = state;
      this.country = country;
      this.mobile = mobile;
      this.email = email;
   }

   public String getBranchCode() {
      return this.branchCode;
   }

   public void setBranchCode(String branchCode) {
      this.branchCode = branchCode;
   }

   public String getBranchName() {
      return this.branchName;
   }

   public void setBranchName(String branchName) {
      this.branchName = branchName;
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

   public String toString() {
      return "BranchMasterDTO{branchCode='" + this.branchCode + '\'' + ", branchName='" + this.branchName + '\'' + ", address='" + this.address + '\'' + ", city='" + this.city + '\'' + ", pin='" + this.pin + '\'' + ", state='" + this.state + '\'' + ", country='" + this.country + '\'' + ", mobile='" + this.mobile + '\'' + ", email='" + this.email + '\'' + '}';
   }
}