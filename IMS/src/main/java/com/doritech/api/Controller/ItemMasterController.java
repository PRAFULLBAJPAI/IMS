  package com.doritech.api.Controller;

import com.doritech.api.DTO.ItemMasterDTO;
import com.doritech.api.Entity.ParamEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.ItemMasterRepository;
import com.doritech.api.Repository.ParamRepository;
import com.doritech.api.Service.ItemMasterService;
import com.doritech.api.Service.ParamService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemMasterController {
   @Autowired
   ItemMasterService itemMasterService;
   @Autowired
   ParamService paramService;
   @Autowired
   ParamRepository paramRepository;
   @Autowired
   ItemMasterRepository itemMasterRepository;

   @PostMapping({"saveItemMaster"})
   @ResponseBody
   public ResponseEntity saveItemMaster(@RequestBody ItemMasterDTO itemMasterDTO, HttpServletRequest httpServletRequest) throws Exception {
      ParamEntity param = this.paramRepository.findByCode("IMAGE_CODE");
      itemMasterDTO.setImageId(this.paramService.getImageDescpValue());
      ParamEntity paramItemUom = this.paramRepository.findByCode("IMAGE_UOM");
      return this.itemMasterService.saveItemMaster(itemMasterDTO, httpServletRequest);
   }

   @PostMapping({"editItemMaster"})
   @ResponseBody
   public ResponseEntity editItemMaster(ItemMasterDTO itemMasterDTO, HttpServletRequest httpServletRequest) throws Exception {
      ParamEntity param = this.paramRepository.findByCode("IMAGE_CODE");
      itemMasterDTO.setImageId(this.paramService.getImageDescpValue());
      return this.itemMasterService.editItemMaster(itemMasterDTO, httpServletRequest);
   }

   @GetMapping({"getAllItemMaster"})
   @ResponseBody
   public ResponseEntity getAllItemMaster() {
      return this.itemMasterService.getAllItemMaster();
   }

   @GetMapping({"getRawItemMaster"})
   @ResponseBody
   public ResponseEntity getRawItemMaster() {
      return this.itemMasterService.getRawItemMaster();
   }

   @GetMapping({"getSubproductItemMaster"})
   @ResponseBody
   public ResponseEntity getSubproductItemMaster() {
      return this.itemMasterService.getSubproductItemMaster();
   }

   @GetMapping({"getFinalProductItemMaster"})
   @ResponseBody
   public ResponseEntity getFinalProductItemMaster() {
      return this.itemMasterService.getFinalProductItemMaster();
   }

   @GetMapping({"getItemByCode"})
   @ResponseBody
   public ResponseEntity getItemByCode(String itemCode) {
      return this.itemMasterService.getItemByCode(itemCode);
   }

   @GetMapping({"getItemByType"})
   @ResponseBody
   public ResponseEntity getItemByType(String itemType) {
      return this.itemMasterService.getItemByType(itemType);
   }

   @GetMapping({"getItemByItemCode"})
   @ResponseBody
   public ResponseEntity getItemByItemCode(String itemCode) {
      return this.itemMasterService.getItemByItemCode(itemCode);
   }

   @GetMapping({"getItemByItemName"})
   @ResponseBody
   public ResponseEntity getItemByItemName(String itemName) {
      return this.itemMasterService.getItemByItemName(itemName);
   }

   @PostMapping({"deleteItemMaster"})
   @ResponseBody
   public ResponseEntity deleteItemMaster(String itemCode) {
      return this.itemMasterService.deleteItemMaster(itemCode);
   }

   @PostMapping({"deactivateItemByCode"})
   @ResponseBody
   public ResponseEntity deactivateItemByCode(String itemCode) {
      return this.itemMasterService.deactivateItemByCode(itemCode);
   }

   @GetMapping({"getItemByConsmptionType"})
   @ResponseBody
   public ResponseEntity getItemByConsmptionType(String itemCode) {
      return this.itemMasterService.getItemByConsumptionType(itemCode);
   }

   @GetMapping({"getAllSubProductsByPattern"})
   @ResponseBody
   public ResponseEntity getAllSubProductsByPattern(@RequestParam String itemCode) throws Exception {
      return this.itemMasterService.getAllSubProductsByPattern(itemCode);
   }

   @GetMapping({"getAllRawItemsForSubFinalProduct"})
   @ResponseBody
   public ResponseEntity getAllRawItemsForSubFinalProduct() throws Exception {
      return this.itemMasterService.getAllRawItemsForSubFinalProduct();
   }

   @GetMapping({"getAllRawItemsForSubFinalProductByProductCode"})
   @ResponseBody
   public ResponseEntity getAllRawItemsForSubFinalProductByProductCode(@RequestParam String productCode) throws Exception {
      return this.itemMasterService.getAllRawItemsForSubFinalProductByProductCode(productCode);
   }
}