package com.doritech.api.DTO;

public class HsnMasterDTO {
   private String hsnCode;
   private String description;
   private double cgst;
   private double sgst;
   private double igst;
   private double ugst;

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
      return "HsnMasterDTO [hsnCode=" + this.hsnCode + ", description=" + this.description + ", cgst=" + this.cgst + ", sgst=" + this.sgst + ", igst=" + this.igst + ", ugst=" + this.ugst + "]";
   }
}