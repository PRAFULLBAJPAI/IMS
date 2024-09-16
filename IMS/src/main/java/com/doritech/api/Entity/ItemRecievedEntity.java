package com.doritech.api.Entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(
   name = "item_recieve"
)
public class ItemRecievedEntity {
   @Id
   @Column(
      name = "item_recieve_id"
   )
   @GeneratedValue(
      strategy = GenerationType.AUTO
   )
   private int itemRecieveId;
   @Column(
      name = "recieve_id"
   )
   private String recieveId;
   @Column(
      name = "recieved_date"
   )
   @Temporal(TemporalType.DATE)
   private Date recievedDate;
   @Column(
      name = "invoice_no"
   )
   private String invoiceNo;
   @Column(
      name = "invoice_date"
   )
   @Temporal(TemporalType.DATE)
   private Date invoiceDate;
   @Column(
      name = "item_code"
   )
   private String itemCode;
   @Column(
      name = "item_source"
   )
   private String itemSource;
   @Column(
      name = "item_quantity"
   )
   private double itemQuantity;
   @Column(
      name = "current_price"
   )
   private BigDecimal currentPrice;
   @Column(
      name = "item_price"
   )
   private BigDecimal itemPrice;
   @Column(
      name = "ware_house"
   )
   private String wareHouse;
   @Column(
      name = "remarks"
   )
   private String remarks;
   @Column(
      name = "emp_id"
   )
   private String empId;
   @Column(
      name = "entry_date"
   )
   @Temporal(TemporalType.DATE)
   private Date entryDate;
   @Column(
      name = "terminal"
   )
   private String terminal;

   public ItemRecievedEntity() {
   }

   public ItemRecievedEntity(int itemRecieveId, String recieveId, Date recievedDate, String invoiceNo, Date invoiceDate, String itemCode, String itemSource, double itemQuantity, BigDecimal itemPrice, String wareHouse, String remarks, String empId, Date entryDate, String terminal, BigDecimal currentPrice) {
      this.itemRecieveId = itemRecieveId;
      this.recieveId = recieveId;
      this.recievedDate = recievedDate;
      this.invoiceNo = invoiceNo;
      this.invoiceDate = invoiceDate;
      this.itemCode = itemCode;
      this.itemSource = itemSource;
      this.itemQuantity = itemQuantity;
      this.itemPrice = itemPrice;
      this.wareHouse = wareHouse;
      this.remarks = remarks;
      this.empId = empId;
      this.entryDate = entryDate;
      this.terminal = terminal;
      this.currentPrice = currentPrice;
   }

   public BigDecimal getCurrentPrice() {
      return this.currentPrice;
   }

   public void setCurrentPrice(BigDecimal currentPrice) {
      this.currentPrice = currentPrice;
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
      return "ItemRecievedEntity [itemRecieveId=" + this.itemRecieveId + ", recieveId=" + this.recieveId + ", recievedDate=" + this.recievedDate + ", invoiceNo=" + this.invoiceNo + ", invoiceDate=" + this.invoiceDate + ", itemCode=" + this.itemCode + ", itemSource=" + this.itemSource + ", itemQuantity=" + this.itemQuantity + ", currentPrice=" + this.currentPrice + ", itemPrice=" + this.itemPrice + ", wareHouse=" + this.wareHouse + ", remarks=" + this.remarks + ", empId=" + this.empId + ", entryDate=" + this.entryDate + ", terminal=" + this.terminal + "]";
   }
}
