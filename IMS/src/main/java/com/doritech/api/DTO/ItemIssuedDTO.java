 package com.doritech.api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.util.Date;

public class ItemIssuedDTO {
   @JsonProperty("itemIssueId")
   private int itemIssueId;
   @JsonProperty("issueId")
   private String issueId;
   @JsonProperty("issueDate")
   private Date issueDate;
   @JsonProperty("consumptionType")
   private String consumptionType;
   @JsonProperty("consumptionFor")
   private String consumptionFor;
   @JsonProperty("itemCode")
   private String itemCode;
   @JsonProperty("itemName")
   private String itemName;
   @JsonProperty("itemQuantity")
   private double itemQuantity;
   @JsonProperty("quantityInStock")
   private double quantityInStock;
   @JsonProperty("minimumStock")
   private double minimumStock;
   @JsonProperty("empId")
   private String empId;
   @JsonProperty("remark")
   private String remark;
   @JsonProperty("entryDate")
   private Date entryDate;
   @JsonProperty("terminal")
   private String terminal;
   @JsonProperty("productCode")
   private String productCode;
   @JsonProperty("productName")
   private String productName;
   @JsonProperty("modifiedBy")
   private String modifiedBy;
   @JsonFormat(
      shape = Shape.STRING,
      pattern = "yyyy-MM-dd"
   )
   @JsonProperty("modifiedOn")
   private Date modifiedOn;
   @JsonProperty("rawMaterialStatus")
   private String rawMaterialStatus;

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

   public double getMinimumStock() {
      return this.minimumStock;
   }

   public void setMinimumStock(double minimumStock) {
      this.minimumStock = minimumStock;
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

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public double getItemQuantity() {
      return this.itemQuantity;
   }

   public void setItemQuantity(double itemQuantity) {
      this.itemQuantity = itemQuantity;
   }

   public double getQuantityInStock() {
      return this.quantityInStock;
   }

   public void setQuantityInStock(double quantityInStock) {
      this.quantityInStock = quantityInStock;
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

   public String toString() {
      return "ItemIssuedDTO [itemIssueId=" + this.itemIssueId + ", issueId=" + this.issueId + ", issueDate=" + this.issueDate + ", consumptionType=" + this.consumptionType + ", consumptionFor=" + this.consumptionFor + ", itemCode=" + this.itemCode + ", itemName=" + this.itemName + ", itemQuantity=" + this.itemQuantity + ", quantityInStock=" + this.quantityInStock + ", minimumStock=" + this.minimumStock + ", empId=" + this.empId + ", remark=" + this.remark + ", entryDate=" + this.entryDate + ", terminal=" + this.terminal + ", productCode=" + this.productCode + ", productName=" + this.productName + ", modifiedBy=" + this.modifiedBy + ", modifiedOn=" + this.modifiedOn + ", rawMaterialStatus=" + this.rawMaterialStatus + "]";
   }
}