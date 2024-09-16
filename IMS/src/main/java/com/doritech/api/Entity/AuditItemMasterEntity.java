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
   name = "audit_item_master"
)
public class AuditItemMasterEntity {
   @Id
   @GeneratedValue(
      strategy = GenerationType.AUTO
   )
   @Column(
      name = "id"
   )
   private Long id;
   @Column(
      name = "item_code"
   )
   private String itemCode;
   @Column(
      name = "item_name"
   )
   private String itemName;
   @Column(
      name = "item_type"
   )
   private String itemType;
   @Column(
      name = "item_source"
   )
   private String itemSource;
   @Column(
      name = "item_uom"
   )
   private String itemUom;
   @Column(
      name = "minimum_stock"
   )
   private double minimumStock;
   @Column(
      name = "item_group"
   )
   private String itemGroup;
   @Column(
      name = "hsn_code"
   )
   private String hsnCode;
   @Column(
      name = "warehouse"
   )
   private String warehouse;
   @Column(
      name = "description"
   )
   private String description;
   @Column(
      name = "image_id"
   )
   private String imageId;
   @Column(
      name = "unit_price"
   )
   private float unitPrice;
   @Column(
      name = "quantity_in_stock"
   )
   private double quantityInStock;
   @Column(
      name = "status"
   )
   private String status;
   @Column(
      name = "user_id"
   )
   private String userId;
   @Column(
      name = "remarks"
   )
   private String remarks;
   @Column(
      name = "entry_date"
   )
   @Temporal(TemporalType.DATE)
   private Date entryDate;
   @Column(
      name = "terminal"
   )
   private String terminal;

   public AuditItemMasterEntity(String itemCode, String itemName, String itemType, String itemSource, String itemUom, double minimumStock, String itemGroup, String hsnCode, String warehouse, String description, String imageId, float unitPrice, double quantityInStock, String status, String userId, String remarks, Date entryDate, String terminal) {
      this.itemCode = itemCode;
      this.itemName = itemName;
      this.itemType = itemType;
      this.itemSource = itemSource;
      this.itemUom = itemUom;
      this.minimumStock = minimumStock;
      this.itemGroup = itemGroup;
      this.hsnCode = hsnCode;
      this.warehouse = warehouse;
      this.description = description;
      this.imageId = imageId;
      this.unitPrice = unitPrice;
      this.quantityInStock = quantityInStock;
      this.status = status;
      this.remarks = remarks;
      this.entryDate = entryDate;
      this.terminal = terminal;
   }

   public AuditItemMasterEntity() {
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
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

   public float getUnitPrice() {
      return this.unitPrice;
   }

   public void setUnitPrice(float unitPrice) {
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

   public void copyFrom(ItemMasterEntity itemMaster) {
      if (itemMaster != null) {
         this.itemCode = itemMaster.getItemCode();
         this.itemName = itemMaster.getItemName();
         this.itemType = itemMaster.getItemType();
         this.itemSource = itemMaster.getItemSource();
         this.itemUom = itemMaster.getItemUom();
         this.minimumStock = itemMaster.getMinimumStock();
         this.hsnCode = itemMaster.getHsnCode();
         this.warehouse = itemMaster.getWarehouse();
         this.description = itemMaster.getDescription();
         this.imageId = itemMaster.getImageId();
         this.unitPrice = itemMaster.getUnitPrice().floatValue();
         this.quantityInStock = itemMaster.getQuantityInStock();
         this.status = itemMaster.getStatus();
         this.userId = itemMaster.getUserId();
         this.remarks = itemMaster.getRemarks();
         this.entryDate = itemMaster.getEntryDate();
         this.terminal = itemMaster.getTerminal();
      }

   }

   public String toString() {
      return "AuditItemMasterEntity [id=" + this.id + ", itemCode=" + this.itemCode + ", itemName=" + this.itemName + ", itemType=" + this.itemType + ", itemSource=" + this.itemSource + ", itemUom=" + this.itemUom + ", minimumStock=" + this.minimumStock + ", itemGroup=" + this.itemGroup + ", hsnCode=" + this.hsnCode + ", warehouse=" + this.warehouse + ", description=" + this.description + ", imageId=" + this.imageId + ", unitPrice=" + this.unitPrice + ", quantityInStock=" + this.quantityInStock + ", status=" + this.status + ", userId=" + this.userId + ", remarks=" + this.remarks + ", entryDate=" + this.entryDate + ", terminal=" + this.terminal + "]";
   }
}
