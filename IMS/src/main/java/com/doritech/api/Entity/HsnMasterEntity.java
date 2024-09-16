package com.doritech.api.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
   name = "hsn_master"
)
public class HsnMasterEntity {
   @Id
   @Column(
      name = "hsn_code",
      length = 10
   )
   private String hsnCode;
   @Column(
      name = "description",
      length = 2000
   )
   private String description;
   @Column(
      name = "cgst",
      precision = 5,
      scale = 2
   )
   private double cgst;
   @Column(
      name = "sgst",
      precision = 5,
      scale = 2
   )
   private double sgst;
   @Column(
      name = "igst",
      precision = 5,
      scale = 2
   )
   private double igst;
   @Column(
      name = "ugst",
      precision = 5,
      scale = 2
   )
   private double ugst;

   public HsnMasterEntity() {
   }

   public HsnMasterEntity(String hsnCode, String description, double cgst, double sgst, double igst, double ugst) {
      this.hsnCode = hsnCode;
      this.description = description;
      this.cgst = cgst;
      this.sgst = sgst;
      this.igst = igst;
      this.ugst = ugst;
   }

   public String getHsnCode() {
      return this.hsnCode;
   }

   public void setHsnCode(String hsnCode) {
      this.hsnCode = hsnCode;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public double getCgst() {
      return this.cgst;
   }

   public void setCgst(double cgst) {
      this.cgst = cgst;
   }

   public double getSgst() {
      return this.sgst;
   }

   public void setSgst(double sgst) {
      this.sgst = sgst;
   }

   public double getIgst() {
      return this.igst;
   }

   public void setIgst(double igst) {
      this.igst = igst;
   }

   public double getUgst() {
      return this.ugst;
   }

   public void setUgst(double ugst) {
      this.ugst = ugst;
   }

   public String toString() {
      return "HsnMasterEntity [hsnCode=" + this.hsnCode + ", description=" + this.description + ", cgst=" + this.cgst + ", sgst=" + this.sgst + ", igst=" + this.igst + ", ugst=" + this.ugst + "]";
   }
}
