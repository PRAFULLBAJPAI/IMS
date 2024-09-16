package com.doritech.api.Entity;

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
   name = "production_entity"
)
public class ProductionEntity {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   @Column(
      name = "id"
   )
   private int id;
   @Column(
      name = "product_id"
   )
   private String productId;
   @Column(
      name = "production_date"
   )
   @Temporal(TemporalType.DATE)
   private Date productionDate;
   @Column(
      name = "item_code"
   )
   private String itemCode;
   @Column(
      name = "terminal"
   )
   private String terminal;
   @Column(
      name = "quantity_in_stock"
   )
   private int quantityInStock;
   @Column(
      name = "quantity"
   )
   private int quantity;
   @Column(
      name = "user_id"
   )
   private String userId;
   @Column(
      name = "entry_date"
   )
   @Temporal(TemporalType.DATE)
   private Date entryDate;

   public ProductionEntity() {
   }

   public ProductionEntity(int id, String productId, Date productionDate, String itemCode, String terminal, int quantityInStock, int quantity, String userId, Date entryDate) {
      this.id = id;
      this.productId = productId;
      this.productionDate = productionDate;
      this.itemCode = itemCode;
      this.terminal = terminal;
      this.quantityInStock = quantityInStock;
      this.quantity = quantity;
      this.userId = userId;
      this.entryDate = entryDate;
   }

   public Date getEntryDate() {
      return this.entryDate;
   }

   public void setEntryDate(Date entryDate) {
      this.entryDate = entryDate;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
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
      return "ProductionEntity [id=" + this.id + ", productId=" + this.productId + ", productionDate=" + this.productionDate + ", itemCode=" + this.itemCode + ", terminal=" + this.terminal + ", quantityInStock=" + this.quantityInStock + ", quantity=" + this.quantity + ", userId=" + this.userId + ", entryDate=" + this.entryDate + "]";
   }
}
