 package com.doritech.api.DTO;

import java.util.Date;

public class ClientEntityDTO {
   private String clientId;
   private String clientName;
   private String contactPerson;
   private String email;
   private String mobile;
   private String address;
   private String city;
   private String state;
   private String country;
   private String pin;
   private String status;
   private String empId;
   private Date entryDate;
   private String terminal;

   public String getState() {
      return this.state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getClientId() {
      return this.clientId;
   }

   public void setClientId(String clientId) {
      this.clientId = clientId;
   }

   public String getClientName() {
      return this.clientName;
   }

   public void setClientName(String clientName) {
      this.clientName = clientName;
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

   public String getCountry() {
      return this.country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public String getPin() {
      return this.pin;
   }

   public void setPin(String pin) {
      this.pin = pin;
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
      return "ClientEntityDTO [clientId=" + this.clientId + ", clientName=" + this.clientName + ", contactPerson=" + this.contactPerson + ", email=" + this.email + ", mobile=" + this.mobile + ", address=" + this.address + ", city=" + this.city + ", state=" + this.state + ", country=" + this.country + ", pin=" + this.pin + ", status=" + this.status + ", empId=" + this.empId + ", entryDate=" + this.entryDate + ", terminal=" + this.terminal + "]";
   }
}