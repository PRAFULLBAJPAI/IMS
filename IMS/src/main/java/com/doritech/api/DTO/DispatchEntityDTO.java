 package com.doritech.api.DTO;

import java.util.Date;

public class DispatchEntityDTO {
   private int id;
   private String dispatchId;
   private String itemCode;
   private String itemName;
   private double quantity;
   private String clientId;
   private String clientName;
   private Date dispatchDate;
   private String invoiceNo;
   private Date invoiceDate;
   private Date entryDate;
   private String terminal;
   private String remarks;
   private double quantityInStock;

   public String getClientName() {
      return this.clientName;
   }

   public void setClientName(String clientName) {
      this.clientName = clientName;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public double getQuantityInStock() {
      return this.quantityInStock;
   }

   public void setQuantityInStock(double quantityInStock) {
      this.quantityInStock = quantityInStock;
   }

   public String getRemarks() {
      return this.remarks;
   }

   public void setRemarks(String remarks) {
      this.remarks = remarks;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getDispatchId() {
      return this.dispatchId;
   }

   public void setDispatchId(String dispatchId) {
      this.dispatchId = dispatchId;
   }

   public String getItemCode() {
      return this.itemCode;
   }

   public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
   }

   public double getQuantity() {
      return this.quantity;
   }

   public void setQuantity(double quantity) {
      this.quantity = quantity;
   }

   public String getClientId() {
      return this.clientId;
   }

   public void setClientId(String clientId) {
      this.clientId = clientId;
   }

   public Date getDispatchDate() {
      return this.dispatchDate;
   }

   public void setDispatchDate(Date dispatchDate) {
      this.dispatchDate = dispatchDate;
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

   public String toString() {
      return "DispatchEntityDTO [id=" + this.id + ", dispatchId=" + this.dispatchId + ", itemCode=" + this.itemCode + ", itemName=" + this.itemName + ", quantity=" + this.quantity + ", clientId=" + this.clientId + ", clientName=" + this.clientName + ", dispatchDate=" + this.dispatchDate + ", invoiceNo=" + this.invoiceNo + ", invoiceDate=" + this.invoiceDate + ", entryDate=" + this.entryDate + ", terminal=" + this.terminal + ", remarks=" + this.remarks + ", quantityInStock=" + this.quantityInStock + "]";
   }
}