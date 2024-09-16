  package com.doritech.api.Controller;

import com.doritech.api.DTO.ItemRecievedDTO;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Service.ItemRecievedService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemRecievedController {
   @Autowired
   ItemRecievedService itemRecievedService;

   @GetMapping({"getAllItemRecieved"})
   @ResponseBody
   public ResponseEntity getAllItemRecieved() {
      return this.itemRecievedService.getAllItemRecieved();
   }

   @PostMapping({"addItemRecived"})
   @ResponseBody
   public ResponseEntity addItemRecived(@RequestBody ItemRecievedDTO itemRecievedRequest, HttpServletRequest httpServletRequest) {
      return this.itemRecievedService.addItemRecived(itemRecievedRequest, httpServletRequest);
   }

   @PostMapping({"addMultipleReceivedItem"})
   @ResponseBody
   public ResponseEntity addMultipleReceivedItem(@RequestBody List<ItemRecievedDTO> itemsReceivedRequests, HttpServletRequest httpServletRequest) throws Exception {
      return this.itemRecievedService.addMultipleReceivedItem(itemsReceivedRequests, httpServletRequest);
   }
}