package com.doritech.api.DTO;

public class ItemImageDTO {
   private String imageId;
   private byte[] image;
   private String imageName;

   public String getImageId() {
      return this.imageId;
   }

   public void setImageId(String imageId) {
      this.imageId = imageId;
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
}