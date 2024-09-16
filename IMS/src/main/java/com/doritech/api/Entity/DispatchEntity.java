package com.doritech.api.Entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(
   name = "dispatch_entity"
)
public class DispatchEntity {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private int id;
   private String dispatchId;
   private String itemCode;
   private double quantity;
   private String clientId;
   @Temporal(TemporalType.DATE)
   private Date dispatchDate;
   private String invoiceNo;
   @Temporal(TemporalType.DATE)
   private Date invoiceDate;
   private double quantityInStock;
   @Temporal(TemporalType.DATE)
   private Date entryDate;
   private String remarks;
   private String terminal;

   public DispatchEntity() {
   }

   public DispatchEntity(int id, String dispatchId, String itemCode, double quantity, String clientId, Date dispatchDate, String invoiceNo, Date invoiceDate, Date entryDate, String terminal, String remarks, double quantityInStock) {
      this.id = id;
      this.dispatchId = dispatchId;
      this.itemCode = itemCode;
      this.quantity = quantity;
      this.clientId = clientId;
      this.dispatchDate = dispatchDate;
      this.invoiceNo = invoiceNo;
      this.invoiceDate = invoiceDate;
      this.entryDate = entryDate;
      this.terminal = terminal;
      this.remarks = remarks;
      this.quantityInStock = quantityInStock;
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
      return "DispatchEntity [id=" + this.id + ", dispatchId=" + this.dispatchId + ", itemCode=" + this.itemCode + ", quantity=" + this.quantity + ", clientId=" + this.clientId + ", dispatchDate=" + this.dispatchDate + ", invoiceNo=" + this.invoiceNo + ", invoiceDate=" + this.invoiceDate + ", quantityInStock=" + this.quantityInStock + ", entryDate=" + this.entryDate + ", remarks=" + this.remarks + ", terminal=" + this.terminal + "]";
   }
}
