 package com.doritech.api.DTO;

import java.util.Date;

public class ProductionEntityDTO {
   private String productId;
   private Date productionDate;
   private String itemCode;
   private String itemName;
   private String terminal;
   private int quantityInStock;
   private int quantity;
   private String userId;
   private Date entryDate;

   public Date getEntryDate() {
      return this.entryDate;
   }

   public void setEntryDate(Date entryDate) {
      this.entryDate = entryDate;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public String getUserId() {
      return this.userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public int getQuantityInStock() {
      return this.quantityInStock;
   }

   public void setQuantityInStock(int quantityInStock) {
      this.quantityInStock = quantityInStock;
   }

   public int getQuantity() {
      return this.quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public String getProductId() {
      return this.productId;
   }

   public void setProductId(String productId) {
      this.productId = productId;
   }

   public Date getProductionDate() {
      return this.productionDate;
   }

   public void setProductionDate(Date productionDate) {
      this.productionDate = productionDate;
   }

   public String getItemCode() {
      return this.itemCode;
   }

   public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
   }

   public String getTerminal() {
      return this.terminal;
   }

   public void setTerminal(String terminal) {
      this.terminal = terminal;
   }

   public String toString() {
      return "ProductionEntityDTO [productId=" + this.productId + ", productionDate=" + this.productionDate + ", itemCode=" + this.itemCode + ", itemName=" + this.itemName + ", terminal=" + this.terminal + ", quantityInStock=" + this.quantityInStock + ", quantity=" + this.quantity + ", userId=" + this.userId + ", entryDate=" + this.entryDate + "]";
   }
}
    