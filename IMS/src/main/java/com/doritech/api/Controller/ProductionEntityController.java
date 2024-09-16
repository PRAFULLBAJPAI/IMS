   package com.doritech.api.Controller;

import com.doritech.api.DTO.ProductionEntityDTO;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Service.ProductionEntityService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductionEntityController {
   @Autowired
   ProductionEntityService productionEntityService;

   @PostMapping({"addMultipleProductsLogs"})
   @ResponseBody
   public ResponseEntity addMultipleProductsLogs(@RequestBody List<ProductionEntityDTO> productionEntityDTOs, HttpServletRequest httpServletRequest) throws Exception {
      return this.productionEntityService.addMultipleProductsLogs(productionEntityDTOs, httpServletRequest);
   }

   @GetMapping({"getAllProductionEntity"})
   @ResponseBody
   public ResponseEntity getAllProductionEntity() throws Exception {
      return this.productionEntityService.getAllProductionEntity();
   }
}