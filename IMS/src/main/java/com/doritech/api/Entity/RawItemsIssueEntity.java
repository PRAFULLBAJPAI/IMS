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
   name = "raw_items_issue"
)
public class RawItemsIssueEntity {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private int rawItemIssueId;
   private String rawIssueId;
   private String itemCode;
   private String itemName;
   @Temporal(TemporalType.DATE)
   private Date issueDate;
   private String consumptionType;
   private String consumptionFor;
   private double quantityInStock;
   private double minimumQuantity;
   private double itemQuantity;
   private String empId;
   private String remark;
   @Temporal(TemporalType.DATE)
   private Date entryDate;
   private String terminal;

   public RawItemsIssueEntity() {
   }

   public RawItemsIssueEntity(int rawItemIssueId, String rawIssueId, String itemCode, String itemName, Date issueDate, String consumptionType, String consumptionFor, double quantityInStock, double minimumQuantity, double itemQuantity, String empId, String remark, Date entryDate, String terminal) {
      this.rawItemIssueId = rawItemIssueId;
      this.rawIssueId = rawIssueId;
      this.itemCode = itemCode;
      this.itemName = itemName;
      this.issueDate = issueDate;
      this.consumptionType = consumptionType;
      this.consumptionFor = consumptionFor;
      this.quantityInStock = quantityInStock;
      this.minimumQuantity = minimumQuantity;
      this.itemQuantity = itemQuantity;
      this.empId = empId;
      this.remark = remark;
      this.entryDate = entryDate;
      this.terminal = terminal;
   }

   public int getRawItemIssueId() {
      return this.rawItemIssueId;
   }

   public void setRawItemIssueId(int rawItemIssueId) {
      this.rawItemIssueId = rawItemIssueId;
   }

   public String getRawIssueId() {
      return this.rawIssueId;
   }

   public void setRawIssueId(String rawIssueId) {
      this.rawIssueId = rawIssueId;
   }

   public String getItemCode() {
      return this.itemCode;
   }

   public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public Date getIssueDate() {
      return this.issueDate;
   }

   public void setIssueDate(Date issueDate) {
      this.issueDate = issueDate;
   }

   public String getConsumptionType() {
      return this.consumptionType;
   }

   public void setConsumptionType(String consumptionType) {
      this.consumptionType = consumptionType;
   }

   public String getConsumptionFor() {
      return this.consumptionFor;
   }

   public void setConsumptionFor(String consumptionFor) {
      this.consumptionFor = consumptionFor;
   }

   public double getQuantityInStock() {
      return this.quantityInStock;
   }

   public void setQuantityInStock(double quantityInStock) {
      this.quantityInStock = quantityInStock;
   }

   public double getMinimumQuantity() {
      return this.minimumQuantity;
   }

   public void setMinimumQuantity(double minimumQuantity) {
      this.minimumQuantity = minimumQuantity;
   }

   public double getItemQuantity() {
      return this.itemQuantity;
   }

   public void setItemQuantity(double itemQuantity) {
      this.itemQuantity = itemQuantity;
   }

   public String getEmpId() {
      return this.empId;
   }

   public void setEmpId(String empId) {
      this.empId = empId;
   }

   public String getRemark() {
      return this.remark;
   }

   public void setRemark(String remark) {
      this.remark = remark;
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
      return "RawItemsIssueEntity [rawItemIssueId=" + this.rawItemIssueId + ", rawIssueId=" + this.rawIssueId + ", itemCode=" + this.itemCode + ", itemName=" + this.itemName + ", issueDate=" + this.issueDate + ", consumptionType=" + this.consumptionType + ", consumptionFor=" + this.consumptionFor + ", quantityInStock=" + this.quantityInStock + ", minimumQuantity=" + this.minimumQuantity + ", itemQuantity=" + this.itemQuantity + ", empId=" + this.empId + ", remark=" + this.remark + ", entryDate=" + this.entryDate + ", terminal=" + this.terminal + "]";
   }
}
