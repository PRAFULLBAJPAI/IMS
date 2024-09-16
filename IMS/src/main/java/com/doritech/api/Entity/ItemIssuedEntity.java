package com.doritech.api.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
   name = "item_issued"
)
public class ItemIssuedEntity {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   @Column(
      name = "item_issue_id"
   )
   private int itemIssueId;
   @Column(
      name = "issue_id"
   )
   private String issueId;
   @Column(
      name = "product_code"
   )
   private String productCode;
   @Column(
      name = "product_name"
   )
   private String productName;
   @Column(
      name = "issue_date"
   )
   @Temporal(TemporalType.DATE)
   private Date issueDate;
   @Column(
      name = "consumption_type"
   )
   private String consumptionType;
   @Column(
      name = "consumption_for"
   )
   private String consumptionFor;
   @Column(
      name = "item_code"
   )
   private String itemCode;
   @Column(
      name = "quantity_in_stock"
   )
   private double quantityInStock;
   @Column(
      name = "item_quantity"
   )
   private double itemQuantity;
   @Column(
      name = "emp_id"
   )
   private String empId;
   @Column(
      name = "remark"
   )
   private String remark;
   @Column(
      name = "entry_date"
   )
   @Temporal(TemporalType.DATE)
   private Date entryDate;
   @Column(
      name = "terminal"
   )
   private String terminal;
   @Column(
      name = "modified_by"
   )
   private String modifiedBy;
   @Column(
      name = "modified_on"
   )
   @Temporal(TemporalType.TIMESTAMP)
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   private Date modifiedOn;
   @Column(
      name = "raw_material_status"
   )
   private String rawMaterialStatus;

   public ItemIssuedEntity() {
   }

   public ItemIssuedEntity(int itemIssueId, String issueId, String productCode, String productName, Date issueDate, String consumptionType, String consumptionFor, String itemCode, double quantityInStock, double itemQuantity, String empId, String remark, Date entryDate, String terminal, String modifiedBy, Date modifiedOn, String rawMaterialStatus) {
      this.itemIssueId = itemIssueId;
      this.issueId = issueId;
      this.productCode = productCode;
      this.productName = productName;
      this.issueDate = issueDate;
      this.consumptionType = consumptionType;
      this.consumptionFor = consumptionFor;
      this.itemCode = itemCode;
      this.quantityInStock = quantityInStock;
      this.itemQuantity = itemQuantity;
      this.empId = empId;
      this.remark = remark;
      this.entryDate = entryDate;
      this.terminal = terminal;
      this.modifiedBy = modifiedBy;
      this.modifiedOn = modifiedOn;
      this.rawMaterialStatus = rawMaterialStatus;
   }

   public String getModifiedBy() {
      return this.modifiedBy;
   }

   public void setModifiedBy(String modifiedBy) {
      this.modifiedBy = modifiedBy;
   }

   public Date getModifiedOn() {
      return this.modifiedOn;
   }

   public void setModifiedOn(Date modifiedOn) {
      this.modifiedOn = modifiedOn;
   }

   public String getRawMaterialStatus() {
      return this.rawMaterialStatus;
   }

   public void setRawMaterialStatus(String rawMaterialStatus) {
      this.rawMaterialStatus = rawMaterialStatus;
   }

   public String getProductCode() {
      return this.productCode;
   }

   public void setProductCode(String productCode) {
      this.productCode = productCode;
   }

   public String getProductName() {
      return this.productName;
   }

   public void setProductName(String productName) {
      this.productName = productName;
   }

   public double getQuantityInStock() {
      return this.quantityInStock;
   }

   public void setQuantityInStock(double quantityInStock) {
      this.quantityInStock = quantityInStock;
   }

   public int getItemIssueId() {
      return this.itemIssueId;
   }

   public void setItemIssueId(int itemIssueId) {
      this.itemIssueId = itemIssueId;
   }

   public String getIssueId() {
      return this.issueId;
   }

   public void setIssueId(String issueId) {
      this.issueId = issueId;
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

   public String getItemCode() {
      return this.itemCode;
   }

   public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
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
      return "ItemIssuedEntity [itemIssueId=" + this.itemIssueId + ", issueId=" + this.issueId + ", productCode=" + this.productCode + ", productName=" + this.productName + ", issueDate=" + this.issueDate + ", consumptionType=" + this.consumptionType + ", consumptionFor=" + this.consumptionFor + ", itemCode=" + this.itemCode + ", quantityInStock=" + this.quantityInStock + ", itemQuantity=" + this.itemQuantity + ", empId=" + this.empId + ", remark=" + this.remark + ", entryDate=" + this.entryDate + ", terminal=" + this.terminal + ", modifiedBy=" + this.modifiedBy + ", modifiedOn=" + this.modifiedOn + ", rawMaterialStatus=" + this.rawMaterialStatus + "]";
   }
}
