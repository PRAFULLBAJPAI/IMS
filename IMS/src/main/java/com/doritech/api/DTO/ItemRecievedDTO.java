 package com.doritech.api.DTO;

import java.math.BigDecimal;
import java.util.Date;

public class ItemRecievedDTO {
   private int itemRecieveId;
   private String recieveId;
   private Date recievedDate;
   private String invoiceNo;
   private Date invoiceDate;
   private String itemCode;
   private String itemName;
   private String itemSource;
   private double itemQuantity;
   private BigDecimal itemPrice;
   private BigDecimal lastPrice;
   private String wareHouse;
   private String remarks;
   private String empId;
   private String vendorName;
   private Date entryDate;
   private String terminal;

   public BigDecimal getLastPrice() {
      return this.lastPrice;
   }

   public void setLastPrice(BigDecimal lastPrice) {
      this.lastPrice = lastPrice;
   }

   public String getVendorName() {
      return this.vendorName;
   }

   public void setVendorName(String vendorName) {
      this.vendorName = vendorName;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public int getItemRecieveId() {
      return this.itemRecieveId;
   }

   public void setItemRecieveId(int itemRecieveId) {
      this.itemRecieveId = itemRecieveId;
   }

   public String getRecieveId() {
      return this.recieveId;
   }

   public void setRecieveId(String recieveId) {
      this.recieveId = recieveId;
   }

   public Date getRecievedDate() {
      return this.recievedDate;
   }

   public void setRecievedDate(Date recievedDate) {
      this.recievedDate = recievedDate;
   }

   public String getInvoiceNo() {
      return this.invoiceNo;
   }

   public void setInvoiceNo(String invoiceNo) {
      this.invoiceNo = invoiceNo;
   }

   public Date getInvoiceDate() {
      return this.invoiceDate;
   }

   public void setInvoiceDate(Date invoiceDate) {
      this.invoiceDate = invoiceDate;
   }

   public String getItemCode() {
      return this.itemCode;
   }

   public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
   }

   public String getItemSource() {
      return this.itemSource;
   }

   public void setItemSource(String itemSource) {
      this.itemSource = itemSource;
   }

   public double getItemQuantity() {
      return this.itemQuantity;
   }

   public void setItemQuantity(double itemQuantity) {
      this.itemQuantity = itemQuantity;
   }

   public BigDecimal getItemPrice() {
      return this.itemPrice;
   }

   public void setItemPrice(BigDecimal itemPrice) {
      this.itemPrice = itemPrice;
   }

   public String getWareHouse() {
      return this.wareHouse;
   }

   public void setWareHouse(String wareHouse) {
      this.wareHouse = wareHouse;
   }

   public String getRemarks() {
      return this.remarks;
   }

   public void setRemarks(String remarks) {
      this.remarks = remarks;
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
      return "ItemRecievedDTO [itemRecieveId=" + this.itemRecieveId + ", recieveId=" + this.recieveId + ", recievedDate=" + this.recievedDate + ", invoiceNo=" + this.invoiceNo + ", invoiceDate=" + this.invoiceDate + ", itemCode=" + this.itemCode + ", itemName=" + this.itemName + ", itemSource=" + this.itemSource + ", itemQuantity=" + this.itemQuantity + ", itemPrice=" + this.itemPrice + ", lastPrice=" + this.lastPrice + ", wareHouse=" + this.wareHouse + ", remarks=" + this.remarks + ", empId=" + this.empId + ", vendorName=" + this.vendorName + ", entryDate=" + this.entryDate + ", terminal=" + this.terminal + "]";
   }
}