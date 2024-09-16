package com.doritech.api.Entity;

import java.io.Serializable;

public class ParamEntityPk implements Serializable {
   private int id;
   private String code;
   private String serial;

   public ParamEntityPk() {
   }

   public ParamEntityPk(int id, String code, String serial) {
      this.id = id;
      this.code = code;
      this.serial = serial;
   }

   public String getCode() {
      return this.code;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getSerial() {
      return this.serial;
   }

   public void setSerial(String serial) {
      this.serial = serial;
   }
}
