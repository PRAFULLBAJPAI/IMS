package com.doritech.api.DTO;

import java.util.Date;

public class VendorMasterDTO {
   private String vendorId;
   private String vendorName;
   private String contactPerson;
   private String email;
   private String mobile;
   private String address;
   private String city;
   private String state;
   private String country;
   private String status;
   private String empId;
   private Date entryDate;
   private String terminal;
   private String pin;

   public String getPin() {
      return this.pin;
   }

   public void setPin(String pin) {
      this.pin = pin;
   }

   public String getVendorId() {
      return this.vendorId;
   }

   public void setVendorId(String vendorId) {
      this.vendorId = vendorId;
   }

   public String getVendorName() {
      return this.vendorName;
   }

   public void setVendorName(String vendorName) {
      this.vendorName = vendorName;
   }

   public String getContactPerson() {
      return this.contactPerson;
   }

   public void setContactPerson(String contactPerson) {
      this.contactPerson = contactPerson;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getMobile() {
      return this.mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
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

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getEmpId() {
      return this.empId;
   }

   public void setEmpId(String empId) {
      this.empId = empId;
   }

   public Date getEntryDate() {
      return this.entryDate;
   }

   public void setEntryDate(Date entryDate) {
      this.entryDate = entryDate;
   }

   public String getTerminal() {
      return this.terminal;
   }

   public void setTerminal(String terminal) {
      this.terminal = terminal;
   }

   public String toString() {
      return "VendorMasterDTO [vendorId=" + this.vendorId + ", vendorName=" + this.vendorName + ", contactPerson=" + this.contactPerson + ", email=" + this.email + ", mobile=" + this.mobile + ", address=" + this.address + ", city=" + this.city + ", state=" + this.state + ", country=" + this.country + ", status=" + this.status + ", empId=" + this.empId + ", entryDate=" + this.entryDate + ", terminal=" + this.terminal + "]";
   }
}