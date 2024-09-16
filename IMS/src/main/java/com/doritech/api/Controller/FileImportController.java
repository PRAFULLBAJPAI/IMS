 package com.doritech.api.Controller;

import com.doritech.api.Service.FileImportService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileImportController {
   @Autowired
   FileImportService fileImportService;

   @PostMapping({"importEmployeeMasterCSV"})
   @ResponseBody
   public String importEmployeeMasterCSV(@RequestParam("employeeMasterFile") MultipartFile file) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
      return this.fileImportService.importEmployeeMasterCSV(br);
   }

   @PostMapping({"importEmployeeMaster"})
   @ResponseBody
   public String importEmployeeMaster(@RequestParam("employeeMasterFile") MultipartFile file) throws Exception {
      try {
         if (file.getOriginalFilename().endsWith(".xlsx")) {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            if (workbook instanceof XSSFWorkbook) {
               return this.fileImportService.importEmployeeMasterXSSF((XSSFWorkbook)workbook);
            } else {
               return workbook instanceof HSSFWorkbook ? this.fileImportService.importEmployeeMasterHSSF((HSSFWorkbook)workbook) : "Import failed";
            }
         } else if (file.getOriginalFilename().endsWith(".xls")) {
            Workbook workbook = new HSSFWorkbook(file.getInputStream());
            return this.fileImportService.importEmployeeMasterHSSF((HSSFWorkbook)workbook);
         } else {
            return "Unsupported file format";
         }
      } catch (Exception var3) {
         var3.printStackTrace();
         return "Import failed";
      }
   }

   @PostMapping({"importItemMasterCSV"})
   @ResponseBody
   public String importItemMasterCSV(@RequestParam("itemMasterFile") MultipartFile file, HttpServletRequest httpServletRequest) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
      return this.fileImportService.importItemMasterCSV(br, httpServletRequest);
   }

   @PostMapping({"importItemMaster"})
   @ResponseBody
   public String importItemMaster(@RequestParam("itemMasterFile") MultipartFile file, HttpServletRequest httpServletRequest) throws Exception {
      try {
         if (file.getOriginalFilename().endsWith(".xlsx")) {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            if (workbook instanceof XSSFWorkbook) {
               return this.fileImportService.importItemMasterXSSF((XSSFWorkbook)workbook, httpServletRequest);
            } else {
               return workbook instanceof HSSFWorkbook ? this.fileImportService.importItemMasterHSSF((HSSFWorkbook)workbook, httpServletRequest) : "Import failed";
            }
         } else if (file.getOriginalFilename().endsWith(".xls")) {
            Workbook workbook = new HSSFWorkbook(file.getInputStream());
            return this.fileImportService.importItemMasterHSSF((HSSFWorkbook)workbook, httpServletRequest);
         } else {
            return "Unsupported file format";
         }
      } catch (Exception var4) {
         var4.printStackTrace();
         return "Import failed";
      }
   }

   @PostMapping({"importItemIssue"})
   @ResponseBody
   public String importItemIssue(@RequestParam("itemIssueFile") MultipartFile file, HttpServletRequest httpServletRequest) {
      try {
         if (file.getOriginalFilename().endsWith(".xlsx")) {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            return this.fileImportService.importItemIssueXSSF(workbook, httpServletRequest);
         } else {
            return "Unsupported file format";
         }
      } catch (IOException var4) {
         var4.printStackTrace();
         return "Import failed";
      }
   }
}