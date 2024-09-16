package com.doritech.api.Entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(
   name = "vendor_master"
)
public class VendorMasterEntity {
   @Id
   @Column(
      name = "vendor_id",
      length = 10
   )
   private String vendorId;
   @Column(
      name = "vendor_name",
      length = 200
   )
   private String vendorName;
   @Column(
      name = "contact_person",
      length = 200
   )
   private String contactPerson;
   @Column(
      name = "email",
      length = 100
   )
   private String email;
   @Column(
      name = "mobile",
      length = 15
   )
   private String mobile;
   @Column(
      name = "address",
      length = 100
   )
   private String address;
   @Column(
      name = "city",
      length = 100
   )
   private String city;
   @Column(
      name = "state",
      length = 100
   )
   private String state;
   @Column(
      name = "country",
      length = 100
   )
   private String country;
   @Column(
      name = "pin",
      length = 10
   )
   private String pin;
   @Column(
      name = "status",
      length = 10
   )
   private String status;
   @Column(
      name = "emp_id",
      length = 10
   )
   private String empId;
   @Column(
      name = "entry_date"
   )
   @Temporal(TemporalType.DATE)
   private Date entryDate;
   @Column(
      name = "terminal",
      length = 20
   )
   private String terminal;

   public VendorMasterEntity() {
   }

   public VendorMasterEntity(String vendorId, String vendorName, String contactPerson, String email, String mobile, String address, String city, String state, String country, String status, String empId, Date entryDate, String terminal, String pin) {
      this.vendorId = vendorId;
      this.vendorName = vendorName;
      this.contactPerson = contactPerson;
      this.email = email;
      this.mobile = mobile;
      this.address = address;
      this.city = city;
      this.state = state;
      this.country = country;
      this.status = status;
      this.empId = empId;
      this.entryDate = entryDate;
      this.terminal = terminal;
      this.pin = pin;
   }

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
      return "VendorMasterEntity [vendorId=" + this.vendorId + ", vendorName=" + this.vendorName + ", contactPerson=" + this.contactPerson + ", email=" + this.email + ", mobile=" + this.mobile + ", address=" + this.address + ", city=" + this.city + ", state=" + this.state + ", country=" + this.country + ", status=" + this.status + ", empId=" + this.empId + ", entryDate=" + this.entryDate + ", terminal=" + this.terminal + ", pin=" + this.pin + "]";
   }
}
