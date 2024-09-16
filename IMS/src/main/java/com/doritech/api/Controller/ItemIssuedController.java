   package com.doritech.api.Controller;

import com.doritech.api.DTO.ItemIssuedDTO;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.ParamRepository;
import com.doritech.api.Service.ItemIssuedService;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ItemIssuedController {
   @Autowired
   ItemIssuedService issuedService;
   @Autowired
   ParamRepository paramRepository;

   @GetMapping({"getAllIssuedItems"})
   @ResponseBody
   public ResponseEntity getAllIssuedItems() throws Exception {
      return this.issuedService.getAllIssuedItems();
   }

   @GetMapping({"getItemIssuedByDate"})
   @ResponseBody
   public ResponseEntity getItemIssuedByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date issueDate) throws Exception {
      return this.issuedService.getItemIssuedByDate(issueDate);
   }

   @PostMapping({"issueItems"})
   @ResponseBody
   public ResponseEntity issueItems(@RequestBody ItemIssuedDTO itemIssuedEntity, HttpServletRequest servletRequest) throws Exception {
      return this.issuedService.issueItems(itemIssuedEntity, servletRequest);
   }

   @GetMapping({"getAllIssuedItemsByDateRange"})
   @ResponseBody
   public ResponseEntity getAllIssuedItemsByDateRange(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) throws Exception {
      return this.issuedService.getAllIssuedItemsByDateRange(startDate, endDate);
   }

   @GetMapping({"getByItemIssuedId"})
   @ResponseBody
   public ResponseEntity getByItemIssuedId(@RequestParam int itemIssueId) throws Exception {
      return this.issuedService.getByItemIssuedId(itemIssueId);
   }

   @PostMapping({"issueMultipleItems"})
   @ResponseBody
   public ResponseEntity issueMultipleItems(@RequestBody List<ItemIssuedDTO> issuedDTOs, HttpServletRequest httpServletRequest) throws Exception {
      return this.issuedService.issueMultipleItems(issuedDTOs, httpServletRequest);
   }

   @GetMapping({"getIssuedItemsByItemCode"})
   @ResponseBody
   public ResponseEntity getIssuedItemsByItemCode(@RequestParam String itemCode) throws Exception {
      return this.issuedService.getIssuedItemsByItemCode(itemCode);
   }

   @GetMapping({"getIssuedItemsByItemName"})
   @ResponseBody
   public ResponseEntity getIssuedItemsByItemName(@RequestParam String itemName) throws Exception {
      return this.issuedService.getIssuedItemsByItemName(itemName);
   }

   @GetMapping({"getProductDetailsByProductCode"})
   @ResponseBody
   public ResponseEntity getProductDetailsByProductCode(@RequestParam String productCode) throws Exception {
      return this.issuedService.getProductDetailsByProductCode(productCode);
   }

   @GetMapping({"getProductDetailsByProductName"})
   @ResponseBody
   public ResponseEntity getProductDetailsByProductName(@RequestParam String productName) throws Exception {
      return this.issuedService.getProductDetailsByProductName(productName);
   }

   @GetMapping({"getAllProductCodesByPattern"})
   @ResponseBody
   public ResponseEntity getAllProductCodesByPattern(@RequestParam String productCode) throws Exception {
      return this.issuedService.getAllProductCodesByPattern(productCode);
   }

   @GetMapping({"getAllProductNamesByPattern"})
   @ResponseBody
   public ResponseEntity getAllProductNamesByPattern(@RequestParam String productName) throws Exception {
      return this.issuedService.getAllProductNamesByPattern(productName);
   }

   @PutMapping({"updateSubproductDefination"})
   @ResponseBody
   public ResponseEntity updateSubproductDefination(@RequestBody List<ItemIssuedDTO> itemIssuedDTOs, HttpServletRequest httpServletRequest) throws Exception {
      return this.issuedService.updateSubproductDefinition(itemIssuedDTOs, httpServletRequest);
   }
}