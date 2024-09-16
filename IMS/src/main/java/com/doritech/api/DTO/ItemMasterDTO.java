 package com.doritech.api.DTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class ItemMasterDTO {
   private String itemCode;
   private String itemName;
   private String itemType;
   private String itemSource;
   private String itemSourceName;
   private String itemUom;
   private double minimumStock;
   private String itemGroup;
   private String hsnCode;
   private String warehouse;
   private String description;
   private String imageId;
   private BigDecimal unitPrice;
   private double quantityInStock;
   private String status;
   private String userId;
   private String remarks;
   private Date entryDate;
   private String terminal;
   private byte[] image;
   private String imageName;

   public String getItemSourceName() {
      return this.itemSourceName;
   }

   public void setItemSourceName(String itemSourceName) {
      this.itemSourceName = itemSourceName;
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

   public String getItemType() {
      return this.itemType;
   }

   public void setItemType(String itemType) {
      this.itemType = itemType;
   }

   public String getItemSource() {
      return this.itemSource;
   }

   public void setItemSource(String itemSource) {
      this.itemSource = itemSource;
   }

   public String getItemUom() {
      return this.itemUom;
   }

   public void setItemUom(String itemUom) {
      this.itemUom = itemUom;
   }

   public double getMinimumStock() {
      return this.minimumStock;
   }

   public void setMinimumStock(double minimumStock) {
      this.minimumStock = minimumStock;
   }

   public String getItemGroup() {
      return this.itemGroup;
   }

   public void setItemGroup(String itemGroup) {
      this.itemGroup = itemGroup;
   }

   public String getHsnCode() {
      return this.hsnCode;
   }

   public void setHsnCode(String hsnCode) {
      this.hsnCode = hsnCode;
   }

   public String getWarehouse() {
      return this.warehouse;
   }

   public void setWarehouse(String warehouse) {
      this.warehouse = warehouse;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getImageId() {
      return this.imageId;
   }

   public void setImageId(String imageId) {
      this.imageId = imageId;
   }

   public BigDecimal getUnitPrice() {
      return this.unitPrice;
   }

   public void setUnitPrice(BigDecimal unitPrice) {
      this.unitPrice = unitPrice;
   }

   public double getQuantityInStock() {
      return this.quantityInStock;
   }

   public void setQuantityInStock(double quantityInStock) {
      this.quantityInStock = quantityInStock;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getUserId() {
      return this.userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getRemarks() {
      return this.remarks;
   }

   public void setRemarks(String remarks) {
      this.remarks = remarks;
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

   public byte[] getImage() {
      return this.image;
   }

   public void setImage(byte[] image) {
      this.image = image;
   }

   public String getImageName() {
      return this.imageName;
   }

   public void setImageName(String imageName) {
      this.imageName = imageName;
   }

   public String toString() {
      return "ItemMasterDTO [itemCode=" + this.itemCode + ", itemName=" + this.itemName + ", itemType=" + this.itemType + ", itemSource=" + this.itemSource + ", itemSourceName=" + this.itemSourceName + ", itemUom=" + this.itemUom + ", minimumStock=" + this.minimumStock + ", itemGroup=" + this.itemGroup + ", hsnCode=" + this.hsnCode + ", warehouse=" + this.warehouse + ", description=" + this.description + ", imageId=" + this.imageId + ", unitPrice=" + this.unitPrice + ", quantityInStock=" + this.quantityInStock + ", status=" + this.status + ", userId=" + this.userId + ", remarks=" + this.remarks + ", entryDate=" + this.entryDate + ", terminal=" + this.terminal + ", image=" + Arrays.toString(this.image) + ", imageName=" + this.imageName + "]";
   }
}