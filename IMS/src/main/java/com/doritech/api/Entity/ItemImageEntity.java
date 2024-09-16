package com.doritech.api.Entity;

import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(
   name = "item_image"
)
public class ItemImageEntity {
   @Id
   @Column(
      name = "image_id"
   )
   private String imageId;
   @Lob
   @Column(
      name = "image"
   )
   private byte[] image;
   @Column(
      name = "image_name"
   )
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

   public String toString() {
      return "ItemImageEntity [imageId=" + this.imageId + ", image=" + Arrays.toString(this.image) + ", imageName=" + this.imageName + "]";
   }

   public ItemImageEntity() {
   }

   public ItemImageEntity(String imageId, byte[] image) {
      this.imageId = imageId;
      this.image = image;
   }
}
