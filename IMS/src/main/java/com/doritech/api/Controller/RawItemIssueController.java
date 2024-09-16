package com.doritech.api.Controller;

import com.doritech.api.DTO.RawItemsIssueDTO;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Service.RawItemIssueService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RawItemIssueController {
   @Autowired
   RawItemIssueService rawItemIssueService;

   @PostMapping({"issueMultipleWithBOM"})
   @ResponseBody
   public ResponseEntity issueMultipleWithBOM(@RequestBody List<RawItemsIssueDTO> rawItemsIssueDTOs, HttpServletRequest httpServletRequest) throws Exception {
      return this.rawItemIssueService.issueMultipleWithBOM(rawItemsIssueDTOs, httpServletRequest);
   }

   @GetMapping({"getAllRawItemsReport"})
   @ResponseBody
   public ResponseEntity getAllRawItemsReport() throws Exception {
      return this.rawItemIssueService.getAllRawItemsReport();
   }
}
    