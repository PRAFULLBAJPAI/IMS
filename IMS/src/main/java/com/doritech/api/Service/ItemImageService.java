package com.doritech.api.Service;

import com.doritech.api.Common.General;
import com.doritech.api.DTO.ItemImageDTO;
import com.doritech.api.Entity.ItemImageEntity;
import com.doritech.api.Entity.ParamEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.ItemImageRepository;
import com.doritech.api.Repository.ParamRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemImageService {
   @Autowired
   General general;
   @Autowired
   ItemImageRepository imageRepository;
   @Autowired
   ParamRepository paramRepository;
   @Autowired
   ParamService paramService;

   public ResponseEntity saveItemImage(MultipartFile image) {
      ResponseEntity response = new ResponseEntity();

      try {
         if (image != null && !image.isEmpty()) {
            ItemImageEntity entity = new ItemImageEntity();
            ParamEntity paramEntity = this.paramRepository.findByCode("IMAGE_CODE");
            entity.setImageId(this.paramService.getImageDescpValue());
            this.paramService.updateImageDescp2Value(paramEntity);
            entity.setImage(image.getBytes());
            entity.setImageName(image.getOriginalFilename());
            this.imageRepository.save(entity);
            response.setPayload(entity);
            response.setMessage("Item Image saved successfully");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("Invalid input: ItemImageDTO or image is null or empty");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
         }
      } catch (IOException var5) {
         response.setMessage("Error occurred while processing the image");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      } catch (Exception var6) {
         response.setMessage("Error occurred while saving item image");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity editItemImageByImageId(String imageId, MultipartFile newImage) {
      ResponseEntity response = new ResponseEntity();

      try {
         Optional<ItemImageEntity> optionalEntity = this.imageRepository.findById(imageId);
         if (optionalEntity.isPresent()) {
            ItemImageEntity existingEntity = (ItemImageEntity)optionalEntity.get();
            ItemImageEntity newEntity = new ItemImageEntity();
            newEntity.setImageId(existingEntity.getImageId());
            if (newImage != null && !newImage.isEmpty()) {
               newEntity.setImage(newImage.getBytes());
               newEntity.setImageName(newImage.getOriginalFilename());
               this.imageRepository.save(newEntity);
               response.setPayload(newEntity);
               response.setMessage("Item Image edited successfully");
               response.setStatusCode(HttpStatus.OK.value());
            } else {
               response.setMessage("Invalid input: newImage is null or empty");
               response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            }
         } else {
            response.setMessage("Item Image not found with imageId: " + imageId);
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (IOException var7) {
         response.setMessage("Error occurred while processing the new image");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      } catch (Exception var8) {
         response.setMessage("Error occurred while editing item image");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getItemImage() {
      ResponseEntity response = new ResponseEntity();

      try {
         List<ItemImageEntity> itemImage = this.imageRepository.findAll();
         if (!itemImage.isEmpty()) {
            List<ItemImageDTO> itemImageDTOList = new ArrayList();
            Iterator var4 = itemImage.iterator();

            while(var4.hasNext()) {
               ItemImageEntity image = (ItemImageEntity)var4.next();
               ItemImageDTO itemImageDTO = new ItemImageDTO();
               BeanUtils.copyProperties(itemImageDTO, image);
               itemImageDTOList.add(itemImageDTO);
            }

            response.setPayload(itemImageDTOList);
            response.setMessage("Item Image fetched successfully");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("No item image found");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var7) {
         response.setMessage("Error occurred while fetching item image");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity deleteItemImageByImageId(String imageId) {
      ResponseEntity response = new ResponseEntity();

      try {
         Optional<ItemImageEntity> optionalEntity = this.imageRepository.findById(imageId);
         if (optionalEntity.isPresent()) {
            ItemImageEntity existingEntity = (ItemImageEntity)optionalEntity.get();
            this.imageRepository.delete(existingEntity);
            response.setMessage("Item Image deleted successfully");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setMessage("Item Image not found with imageId: " + imageId);
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var5) {
         response.setMessage("Error occurred while deleting item image");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }
}
