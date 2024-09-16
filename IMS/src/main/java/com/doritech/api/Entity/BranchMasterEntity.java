package com.doritech.api.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
   name = "branch_master"
)
public class BranchMasterEntity {
   @Id
   @Column(
      name = "branch_code"
   )
   private String branchCode;
   @Column(
      name = "branch_name"
   )
   private String branchName;
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

   public BranchMasterEntity() {
   }

   public BranchMasterEntity(String branchCode, String branchName, String address, String city, String pin, String state, String country, String mobile, String email) {
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
      return "BranchMasterEntity{branchCode='" + this.branchCode + '\'' + ", branchName='" + this.branchName + '\'' + ", address='" + this.address + '\'' + ", city='" + this.city + '\'' + ", pin='" + this.pin + '\'' + ", state='" + this.state + '\'' + ", country='" + this.country + '\'' + ", mobile='" + this.mobile + '\'' + ", email='" + this.email + '\'' + '}';
   }
}
