  package com.doritech.api.Controller;

import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.ParamRepository;
import com.doritech.api.Service.ItemImageService;
import com.doritech.api.Service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ItemImageController {
   @Autowired
   ParamRepository paramRepository;
   @Autowired
   ParamService paramService;
   @Autowired
   ItemImageService imageService;

   @PostMapping({"saveAndEditItemImage"})
   @ResponseBody
   public ResponseEntity saveItemImage(@RequestParam("image") MultipartFile image) throws Exception {
      return this.imageService.saveItemImage(image);
   }

   @PostMapping({"EditItemImage"})
   @ResponseBody
   public ResponseEntity editItemImageByImageId(@RequestParam String imageId, @RequestParam("image") MultipartFile image) throws Exception {
      return this.imageService.editItemImageByImageId(imageId, image);
   }

   @PostMapping({"deleteItemImage"})
   @ResponseBody
   public ResponseEntity deleteItemImageByImageId(@RequestParam String imageId) throws Exception {
      return this.imageService.deleteItemImageByImageId(imageId);
   }

   @GetMapping({"getItemImage"})
   @ResponseBody
   public ResponseEntity getItemImage() {
      return this.imageService.getItemImage();
   }
}