package com.doritech.api.Service;

import com.doritech.api.Common.General;
import com.doritech.api.Entity.EmployeeMasterEntity;
import com.doritech.api.Entity.ItemIssuedEntity;
import com.doritech.api.Entity.ItemMasterEntity;
import com.doritech.api.Repository.EmployeeMasterRepository;
import com.doritech.api.Repository.ItemIssuedRepository;
import com.doritech.api.Repository.ItemMasterRepository;
import com.doritech.api.Service.FileImportService;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileImportService {
   @Autowired
   EmployeeMasterRepository employeeMasterRepo;
   @Autowired
   ItemMasterRepository itemMasterRepository;
   @Autowired
   ItemIssuedRepository itemIssuedRepository;
   @Autowired
   General general;

   public String importEmployeeMasterCSV(BufferedReader br) throws IOException {
      try {
         int line_no = 0;

         for(String line = ""; (line = br.readLine()) != null; ++line_no) {
            if (!line.isEmpty()) {
               String[] result = line.split("\\|");
               if (result.length == 16) {
                  EmployeeMasterEntity entity = new EmployeeMasterEntity();
                  entity.setEmpId(this.general.checkNull(result[0]));
                  entity.setEmpRole(this.general.checkNull(result[1]));
                  entity.setEmpName(this.general.checkNull(result[2]));
                  entity.setEmpPan(this.general.checkNull(result[3]));
                  entity.setAddress(this.general.checkNull(result[4]));
                  entity.setCity(this.general.checkNull(result[5]));
                  entity.setPin(this.general.checkNull(result[6]));
                  entity.setState(this.general.checkNull(result[7]));
                  entity.setCountry(this.general.checkNull(result[8]));
                  entity.setMobile(this.general.checkNull(result[9]));
                  entity.setEmail(this.general.checkNull(result[10]));
                  entity.setStatus(this.general.checkNull(result[11]));
                  entity.setBranch(this.general.checkNull(result[12]));
                  entity.setLoginFlag(this.general.checkNull(result[13]));
                  entity.setPassword(this.general.checkNull(result[14]));
                  entity.setGroupId(this.general.checkNull(result[15]));
                  this.employeeMasterRepo.save(entity);
               } else {
                  System.out.println("Invalid line format at line number " + line_no + ": " + line);
               }
            }
         }

         return "saved";
      } catch (Exception var6) {
         var6.printStackTrace();
         return "failed";
      }
   }

   public String importEmployeeMasterHSSF(HSSFWorkbook workbook) {
      try {
         HSSFSheet firstSheet = workbook.getSheetAt(0);
         Iterator<Row> rowIterator = firstSheet.rowIterator();

         for(int i = 0; rowIterator.hasNext(); ++i) {
            EmployeeMasterEntity employeeMaster = new EmployeeMasterEntity();
            Row nextRow = (Row)rowIterator.next();
            if (i > 0) {
               Iterator<Cell> cellIterator = nextRow.cellIterator();
               String val = "";
               String var9 = "";

               while(cellIterator.hasNext()) {
                  Cell nextCell = (Cell)cellIterator.next();
                  int columnIndex = nextCell.getColumnIndex();
                  switch(columnIndex) {
                  case 0:
                     val = this.general.getCellValue(nextCell);
                     employeeMaster.setEmpId(this.general.checkNull(val.trim()));
                     break;
                  case 1:
                     val = this.general.getCellValue(nextCell);
                     employeeMaster.setEmpRole(this.general.checkNull(val.trim()));
                     break;
                  case 2:
                     val = this.general.getCellValue(nextCell);
                     employeeMaster.setEmpName(this.general.checkNull(val.trim()));
                     break;
                  case 3:
                     val = this.general.getCellValue(nextCell);
                     employeeMaster.setEmpPan(this.general.checkNull(val.trim()));
                     break;
                  case 4:
                     val = this.general.getCellValue(nextCell);
                     employeeMaster.setAddress(this.general.checkNull(val.trim()));
                     break;
                  case 5:
                     val = this.general.getCellValue(nextCell);
                     employeeMaster.setCity(this.general.checkNull(val.trim()));
                     break;
                  case 6:
                     val = this.general.getCellValue(nextCell);
                     employeeMaster.setPin(this.general.checkNull(val.trim()));
                     break;
                  case 7:
                     val = this.general.getCellValue(nextCell);
                     employeeMaster.setState(this.general.checkNull(val.trim()));
                     break;
                  case 8:
                     val = this.general.getCellValue(nextCell);
                     employeeMaster.setCountry(this.general.checkNull(val.trim()));
                     break;
                  case 9:
                     val = this.general.getCellValue(nextCell);
                     employeeMaster.setMobile(this.general.checkNull(val.trim()));
                     break;
                  case 10:
                     val = this.general.getCellValue(nextCell);
                     employeeMaster.setEmail(this.general.checkNull(val.trim()));
                     break;
                  case 11:
                     val = this.general.getCellValue(nextCell);
                     employeeMaster.setStatus(this.general.checkNull(val.trim()));
                     break;
                  case 12:
                     val = this.general.getCellValue(nextCell);
                     employeeMaster.setBranch(this.general.checkNull(val.trim()));
                     break;
                  case 13:
                     val = this.general.getCellValue(nextCell);
                     employeeMaster.setLoginFlag(this.general.checkNull(val.trim()));
                     break;
                  case 14:
                     val = this.general.getCellValue(nextCell);
                     employeeMaster.setPassword(this.general.checkNull(val.trim()));
                     break;
                  case 15:
                     val = this.general.getCellValue(nextCell);
                     employeeMaster.setGroupId(this.general.checkNull(val.trim()));
                  }
               }

               if (this.employeeMasterRepo.findById(employeeMaster.getEmpId()).isPresent()) {
                  this.employeeMasterRepo.deleteById(employeeMaster.getEmpId());
                  this.employeeMasterRepo.save(employeeMaster);
               } else {
                  System.out.println("save employee");
                  this.employeeMasterRepo.save(employeeMaster);
               }
            }
         }

         return "saved";
      } catch (Exception var12) {
         var12.printStackTrace();
         return "failed";
      }
   }

   public String importEmployeeMasterXSSF(XSSFWorkbook workbook) throws Exception {
      try {
         XSSFSheet firstSheet = workbook.getSheetAt(0);
         Iterator<Row> rowIterator = firstSheet.rowIterator();

         for(int i = 0; rowIterator.hasNext(); ++i) {
            EmployeeMasterEntity employeeMaster = new EmployeeMasterEntity();
            Row nextRow = (Row)rowIterator.next();
            if (i > 0) {
               Iterator<Cell> cellIterator = nextRow.cellIterator();
               String val = "";
               String var9 = "";

               while(cellIterator.hasNext()) {
                  Cell nextCell = (Cell)cellIterator.next();
                  int columnIndex = nextCell.getColumnIndex();
                  switch(columnIndex) {
                  case 0:
                     try {
                        System.out.println("EMP ID");
                        val = this.general.getCellValue(nextCell);
                     } catch (Exception var28) {
                        val = "";
                     }

                     employeeMaster.setEmpId(val.trim());
                     break;
                  case 1:
                     try {
                        System.out.println("EMP ROLE");
                        val = this.general.getCellValue(nextCell);
                     } catch (Exception var27) {
                        val = "";
                     }

                     employeeMaster.setEmpRole(val.trim());
                     break;
                  case 2:
                     try {
                        System.out.println("EMP NAME");
                        val = this.general.getCellValue(nextCell);
                     } catch (Exception var26) {
                        val = "";
                     }

                     employeeMaster.setEmpName(val.trim());
                     break;
                  case 3:
                     try {
                        System.out.println("EMP PAN");
                        val = this.general.getCellValue(nextCell);
                     } catch (Exception var25) {
                        val = "";
                     }

                     employeeMaster.setEmpPan(val.trim());
                     break;
                  case 4:
                     try {
                        System.out.println("Address");
                        val = this.general.getCellValue(nextCell);
                     } catch (Exception var24) {
                        val = "";
                     }

                     employeeMaster.setAddress(val.trim());
                     break;
                  case 5:
                     try {
                        System.out.println("City");
                        val = this.general.getCellValue(nextCell);
                     } catch (Exception var23) {
                        val = "";
                     }

                     employeeMaster.setCity(val.trim());
                     break;
                  case 6:
                     try {
                        System.out.println("Pin");
                        val = this.general.getCellValue(nextCell);
                     } catch (Exception var22) {
                        val = "";
                     }

                     employeeMaster.setPin(val.trim());
                     break;
                  case 7:
                     try {
                        System.out.println("State");
                        val = this.general.getCellValue(nextCell);
                     } catch (Exception var21) {
                        val = "";
                     }

                     employeeMaster.setState(val.trim());
                     break;
                  case 8:
                     try {
                        System.out.println("Country");
                        val = this.general.getCellValue(nextCell);
                     } catch (Exception var20) {
                        val = "";
                     }

                     employeeMaster.setCountry(val.trim());
                     break;
                  case 9:
                     try {
                        System.out.println("Mobile");
                        val = this.general.getCellValue(nextCell);
                     } catch (Exception var19) {
                        val = "";
                     }

                     employeeMaster.setMobile(val.trim());
                     break;
                  case 10:
                     try {
                        System.out.println("Email");
                        val = this.general.getCellValue(nextCell);
                     } catch (Exception var18) {
                        val = "";
                     }

                     employeeMaster.setEmail(val.trim());
                     break;
                  case 11:
                     try {
                        System.out.println("Status");
                        val = this.general.getCellValue(nextCell);
                     } catch (Exception var17) {
                        val = "";
                     }

                     employeeMaster.setStatus(val.trim());
                     break;
                  case 12:
                     try {
                        System.out.println("Branch");
                        val = this.general.getCellValue(nextCell);
                     } catch (Exception var16) {
                        val = "";
                     }

                     employeeMaster.setBranch(val.trim());
                     break;
                  case 13:
                     try {
                        System.out.println("Login Flag");
                        val = this.general.getCellValue(nextCell);
                     } catch (Exception var15) {
                        val = "";
                     }

                     employeeMaster.setLoginFlag(val.trim());
                     break;
                  case 14:
                     try {
                        System.out.println("Password");
                        val = this.general.getCellValue(nextCell);
                     } catch (Exception var14) {
                        val = "";
                     }

                     employeeMaster.setPassword(val.trim());
                     break;
                  case 15:
                     try {
                        System.out.println("Group Id");
                        val = this.general.getCellValue(nextCell);
                     } catch (Exception var13) {
                        val = "";
                     }

                     employeeMaster.setGroupId(val.trim());
                  }
               }

               if (this.employeeMasterRepo.findById(employeeMaster.getEmpId()).isPresent()) {
                  this.employeeMasterRepo.deleteById(employeeMaster.getEmpId());
                  this.employeeMasterRepo.save(employeeMaster);
               } else {
                  System.out.println("save employee");
                  this.employeeMasterRepo.save(employeeMaster);
               }
            }
         }
      } catch (Exception var29) {
         var29.printStackTrace();
      }

      return "saved";
   }

   public String importEmployeeMasterSXSSF(SXSSFWorkbook workbook) {
      try {
         SXSSFSheet firstSheet = workbook.getSheetAt(0);
         Iterator<Row> rowIterator = firstSheet.rowIterator();

         for(int i = 0; rowIterator.hasNext(); ++i) {
            SXSSFRow nextRow = (SXSSFRow)rowIterator.next();
            if (i > 0) {
               Iterator<Cell> cellIterator = nextRow.cellIterator();
               EmployeeMasterEntity employeeMaster = new EmployeeMasterEntity();

               while(cellIterator.hasNext()) {
                  SXSSFCell nextCell = (SXSSFCell)cellIterator.next();
                  int columnIndex = nextCell.getColumnIndex();
                  String val = this.general.getCellValue(nextCell);
                  switch(columnIndex) {
                  case 0:
                     employeeMaster.setEmpId(this.general.checkNull(val.trim()));
                     break;
                  case 1:
                     employeeMaster.setEmpRole(this.general.checkNull(val.trim()));
                     break;
                  case 2:
                     employeeMaster.setEmpName(this.general.checkNull(val.trim()));
                     break;
                  case 3:
                     employeeMaster.setEmpPan(this.general.checkNull(val.trim()));
                     break;
                  case 4:
                     employeeMaster.setAddress(this.general.checkNull(val.trim()));
                     break;
                  case 5:
                     employeeMaster.setCity(this.general.checkNull(val.trim()));
                     break;
                  case 6:
                     employeeMaster.setPin(this.general.checkNull(val.trim()));
                     break;
                  case 7:
                     employeeMaster.setState(this.general.checkNull(val.trim()));
                     break;
                  case 8:
                     employeeMaster.setCountry(this.general.checkNull(val.trim()));
                     break;
                  case 9:
                     employeeMaster.setMobile(this.general.checkNull(val.trim()));
                     break;
                  case 10:
                     employeeMaster.setEmail(this.general.checkNull(val.trim()));
                     break;
                  case 11:
                     employeeMaster.setStatus(this.general.checkNull(val.trim()));
                     break;
                  case 12:
                     employeeMaster.setBranch(this.general.checkNull(val.trim()));
                     break;
                  case 13:
                     employeeMaster.setLoginFlag(this.general.checkNull(val.trim()));
                     break;
                  case 14:
                     employeeMaster.setPassword(this.general.checkNull(val.trim()));
                     break;
                  case 15:
                     employeeMaster.setGroupId(this.general.checkNull(val.trim()));
                  }
               }

               if (this.employeeMasterRepo.findById(employeeMaster.getEmpId()).isPresent()) {
                  this.employeeMasterRepo.deleteById(employeeMaster.getEmpId());
                  this.employeeMasterRepo.save(employeeMaster);
               } else {
                  System.out.println("save employee");
                  this.employeeMasterRepo.save(employeeMaster);
               }
            }
         }

         return "saved";
      } catch (Exception var11) {
         var11.printStackTrace();
         return "failed";
      }
   }

   public String importItemMasterCSV(BufferedReader br, HttpServletRequest httpServletRequest) throws IOException {
      try {
         int line_no = 0;

         for(String line = ""; (line = br.readLine()) != null; ++line_no) {
            if (!line.isEmpty()) {
               String[] result = line.split("\\|");
               if (result.length == 18) {
                  ItemMasterEntity entity = new ItemMasterEntity();
                  entity.setItemCode(this.general.checkNull(result[0]));
                  entity.setItemName(this.general.checkNull(result[1]));
                  entity.setItemType(this.general.checkNull(result[2]));
                  entity.setItemSource(this.general.checkNull(result[3]));
                  entity.setItemUom(this.general.checkNull(result[4]));
                  entity.setMinimumStock(Double.parseDouble(this.general.checkNull(result[5])));
                  entity.setItemGroup(this.general.checkNull(result[6]));
                  entity.setHsnCode(this.general.checkNull(result[7]));
                  entity.setWarehouse(this.general.checkNull(result[8]));
                  entity.setDescription(this.general.checkNull(result[9]));
                  entity.setImageId(this.general.checkNull(result[10]));
                  String unitPriceString = this.general.checkNull(result[11]);
                  BigDecimal unitPrice = unitPriceString != null ? new BigDecimal(unitPriceString) : null;
                  entity.setUnitPrice(unitPrice);
                  entity.setQuantityInStock(Double.parseDouble(this.general.checkNull(result[12])));
                  entity.setStatus("Active");
                  entity.setUserId(this.general.checkNull(result[14]));
                  entity.setRemarks(this.general.checkNull(result[15]));
                  entity.setEntryDate(Date.valueOf(LocalDate.now()));
                  entity.setTerminal(httpServletRequest.getRemoteAddr());
                  this.itemMasterRepository.save(entity);
               } else {
                  System.out.println("Invalid line format at line number " + line_no + ": " + line);
               }
            }
         }

         return "saved";
      } catch (Exception var9) {
         var9.printStackTrace();
         return "failed";
      }
   }

   public String importItemMasterHSSF(HSSFWorkbook workbook, HttpServletRequest httpServletRequest) {
      try {
         HSSFSheet firstSheet = workbook.getSheetAt(0);
         Iterator<Row> rowIterator = firstSheet.rowIterator();

         for(int i = 0; rowIterator.hasNext(); ++i) {
            ItemMasterEntity itemMaster = new ItemMasterEntity();
            Row nextRow = (Row)rowIterator.next();
            if (i > 0) {
               Iterator<Cell> cellIterator = nextRow.cellIterator();
               String val = "";
               String var10 = "";

               while(cellIterator.hasNext()) {
                  Cell nextCell = (Cell)cellIterator.next();
                  int columnIndex = nextCell.getColumnIndex();
                  switch(columnIndex) {
                  case 0:
                     val = this.general.getCellValue(nextCell);
                     itemMaster.setItemCode(this.general.checkNull(val.trim()));
                     break;
                  case 1:
                     val = this.general.getCellValue(nextCell);
                     itemMaster.setItemName(this.general.checkNull(val.trim()));
                     break;
                  case 2:
                     val = this.general.getCellValue(nextCell);
                     itemMaster.setItemType(this.general.checkNull(val.trim()));
                     break;
                  case 3:
                     val = this.general.getCellValue(nextCell);
                     itemMaster.setItemSource(this.general.checkNull(val.trim()));
                     break;
                  case 4:
                     val = this.general.getCellValue(nextCell);
                     itemMaster.setItemUom(this.general.checkNull(val.trim()));
                     break;
                  case 5:
                     this.general.getCellValue(nextCell);
                     String minStockVal = this.getCellValueAsString(nextCell);
                     if (!minStockVal.isEmpty()) {
                        itemMaster.setMinimumStock(Double.parseDouble(minStockVal));
                     }
                     break;
                  case 6:
                     val = this.general.getCellValue(nextCell);
                     itemMaster.setItemGroup(this.general.checkNull(val.trim()));
                     break;
                  case 7:
                     val = this.general.getCellValue(nextCell);
                     itemMaster.setHsnCode(this.general.checkNull(val.trim()));
                     break;
                  case 8:
                     val = this.general.getCellValue(nextCell);
                     itemMaster.setWarehouse(this.general.checkNull(val.trim()));
                     break;
                  case 9:
                     val = this.general.getCellValue(nextCell);
                     itemMaster.setDescription(this.general.checkNull(val.trim()));
                     break;
                  case 10:
                     val = this.general.getCellValue(nextCell);
                     itemMaster.setImageId(this.general.checkNull(val.trim()));
                     break;
                  case 11:
                     this.general.getCellValue(nextCell);
                     String unitPriceVal = this.getCellValueAsString(nextCell);
                     if (!unitPriceVal.isEmpty()) {
                        itemMaster.setUnitPrice(new BigDecimal(unitPriceVal));
                     }
                     break;
                  case 12:
                     this.general.getCellValue(nextCell);
                     String qtyInStockVal = this.getCellValueAsString(nextCell);
                     if (!qtyInStockVal.isEmpty()) {
                        itemMaster.setQuantityInStock(Double.parseDouble(qtyInStockVal));
                     }
                     break;
                  case 13:
                     this.general.getCellValue(nextCell);
                     itemMaster.setStatus("Active");
                     break;
                  case 14:
                     val = this.general.getCellValue(nextCell);
                     itemMaster.setUserId(val.trim());
                     break;
                  case 15:
                     val = this.general.getCellValue(nextCell);
                     itemMaster.setRemarks(this.general.checkNull(val.trim()));
                     break;
                  case 16:
                     this.general.getCellValue(nextCell);
                     itemMaster.setEntryDate(Date.valueOf(LocalDate.now()));
                     break;
                  case 17:
                     this.general.getCellValue(nextCell);
                     itemMaster.setTerminal(httpServletRequest.getRemoteAddr());
                  }
               }

               if (this.itemMasterRepository.findById(itemMaster.getItemCode()).isPresent()) {
                  this.itemMasterRepository.deleteById(itemMaster.getItemCode());
                  this.itemMasterRepository.save(itemMaster);
               } else {
                  System.out.println("save Items");
                  this.itemMasterRepository.save(itemMaster);
               }
            }
         }

         return "saved";
      } catch (Exception var16) {
         var16.printStackTrace();
         return "failed";
      }
   }

   public String importItemMasterXSSF(XSSFWorkbook workbook, HttpServletRequest httpServletRequest) {
      try {
         XSSFSheet firstSheet = workbook.getSheetAt(0);
         Iterator<Row> rowIterator = firstSheet.rowIterator();

         for(int i = 0; rowIterator.hasNext(); ++i) {
            ItemMasterEntity itemMaster = new ItemMasterEntity();
            Row nextRow = (Row)rowIterator.next();
            if (i > 0) {
               Iterator cellIterator = nextRow.cellIterator();

               while(cellIterator.hasNext()) {
                  Cell nextCell = (Cell)cellIterator.next();
                  int columnIndex = nextCell.getColumnIndex();

                  try {
                     switch(columnIndex) {
                     case 0:
                        System.out.println("ITEM CODE");
                        itemMaster.setItemCode(this.general.getCellValue(nextCell).trim());
                        break;
                     case 1:
                        System.out.println("ITEM NAME");
                        itemMaster.setItemName(this.general.getCellValue(nextCell).trim());
                        break;
                     case 2:
                        System.out.println("ITEM TYPE");
                        itemMaster.setItemType(this.general.getCellValue(nextCell).trim());
                        break;
                     case 3:
                        System.out.println("ITEM SOURCE");
                        itemMaster.setItemSource(this.general.getCellValue(nextCell).trim());
                        break;
                     case 4:
                        System.out.println("ITEM UOM");
                        itemMaster.setItemUom(this.general.getCellValue(nextCell).trim());
                        break;
                     case 5:
                        System.out.println("MINIMUM STOCK");
                        String minStockVal = this.getCellValueAsString(nextCell);
                        if (!minStockVal.isEmpty()) {
                           itemMaster.setMinimumStock(Double.parseDouble(minStockVal));
                        }
                        break;
                     case 6:
                        System.out.println("ITEM GROUP");
                        itemMaster.setItemGroup(this.general.getCellValue(nextCell).trim());
                        break;
                     case 7:
                        System.out.println("HSN CODE");
                        int cellType = nextCell.getCellType();
                        if (cellType == 0) {
                           double numericValue = nextCell.getNumericCellValue();
                           itemMaster.setHsnCode(String.valueOf((int)numericValue));
                        } else if (cellType == 1) {
                           itemMaster.setHsnCode(nextCell.getStringCellValue().trim());
                        }
                        break;
                     case 8:
                        System.out.println("WAREHOUSE");
                        itemMaster.setWarehouse(this.general.getCellValue(nextCell).trim());
                        break;
                     case 9:
                        System.out.println("DESCRIPTION");
                        itemMaster.setDescription(this.general.getCellValue(nextCell).trim());
                        break;
                     case 10:
                        System.out.println("IMAGE ID");
                        itemMaster.setImageId(this.general.getCellValue(nextCell).trim());
                        break;
                     case 11:
                        System.out.println("UNIT PRICE");
                        String unitPriceVal = this.getCellValueAsString(nextCell);
                        if (!unitPriceVal.isEmpty()) {
                           itemMaster.setUnitPrice(new BigDecimal(unitPriceVal));
                        }
                        break;
                     case 12:
                        System.out.println("QUANTITY IN STOCK");
                        String qtyInStockVal = this.getCellValueAsString(nextCell);
                        if (!qtyInStockVal.isEmpty()) {
                           itemMaster.setQuantityInStock(Double.parseDouble(qtyInStockVal));
                        }
                        break;
                     case 13:
                        System.out.println("STATUS");
                        itemMaster.setStatus("Active");
                        break;
                     case 14:
                        System.out.println("USER ID");
                        itemMaster.setUserId(this.general.getCellValue(nextCell).trim());
                        break;
                     case 15:
                        System.out.println("REMARKS");
                        itemMaster.setRemarks(this.general.getCellValue(nextCell).trim());
                        break;
                     case 16:
                        System.out.println("ENTRY DATE");
                        itemMaster.setEntryDate(Date.valueOf(LocalDate.now()));
                        break;
                     case 17:
                        System.out.println("TERMINAL");
                        itemMaster.setTerminal(httpServletRequest.getRemoteAddr());
                     }
                  } catch (NullPointerException | NumberFormatException var15) {
                     var15.printStackTrace();
                  }
               }

               try {
                  String itemCode = itemMaster.getItemCode();
                  if (itemCode != null && !itemCode.isEmpty()) {
                     if (this.itemMasterRepository.findById(itemCode).isPresent()) {
                        this.itemMasterRepository.deleteById(itemCode);
                     }

                     this.itemMasterRepository.save(itemMaster);
                  }
               } catch (Exception var16) {
                  var16.printStackTrace();
                  return "failed";
               }
            }
         }

         return "saved";
      } catch (Exception var17) {
         var17.printStackTrace();
         return "failed";
      }
   }

   private String getCellValueAsString(Cell cell) {
      if (cell == null) {
         return "";
      } else {
         DataFormatter dataFormatter = new DataFormatter();
         return dataFormatter.formatCellValue(cell);
      }
   }

   public String importItemMasterSXSSF(SXSSFWorkbook workbook, HttpServletRequest httpServletRequest) {
      try {
         SXSSFSheet firstSheet = workbook.getSheetAt(0);
         Iterator<Row> rowIterator = firstSheet.rowIterator();

         for(int i = 0; rowIterator.hasNext(); ++i) {
            SXSSFRow nextRow = (SXSSFRow)rowIterator.next();
            if (i > 0) {
               Iterator<Cell> cellIterator = nextRow.cellIterator();
               ItemMasterEntity itemMaster = new ItemMasterEntity();

               while(cellIterator.hasNext()) {
                  SXSSFCell nextCell = (SXSSFCell)cellIterator.next();
                  int columnIndex = nextCell.getColumnIndex();
                  String val = this.general.getCellValue(nextCell);
                  switch(columnIndex) {
                  case 0:
                     itemMaster.setItemCode(this.general.checkNull(val.trim()));
                     break;
                  case 1:
                     itemMaster.setItemName(this.general.checkNull(val.trim()));
                     break;
                  case 2:
                     itemMaster.setItemType(this.general.checkNull(val.trim()));
                     break;
                  case 3:
                     itemMaster.setItemSource(this.general.checkNull(val.trim()));
                     break;
                  case 4:
                     itemMaster.setItemUom(this.general.checkNull(val.trim()));
                     break;
                  case 5:
                     itemMaster.setMinimumStock(Double.parseDouble(this.general.checkNull(val.trim())));
                     break;
                  case 6:
                     itemMaster.setItemGroup(this.general.checkNull(val.trim()));
                     break;
                  case 7:
                     itemMaster.setHsnCode(this.general.checkNull(val.trim()));
                     break;
                  case 8:
                     itemMaster.setWarehouse(this.general.checkNull(val.trim()));
                     break;
                  case 9:
                     itemMaster.setDescription(this.general.checkNull(val.trim()));
                     break;
                  case 10:
                     itemMaster.setImageId(this.general.checkNull(val.trim()));
                     break;
                  case 11:
                     BigDecimal unitPrice = new BigDecimal(val.trim());
                     itemMaster.setUnitPrice(unitPrice);
                     break;
                  case 12:
                     itemMaster.setQuantityInStock(Double.parseDouble(this.general.checkNull(val.trim())));
                     break;
                  case 13:
                     itemMaster.setStatus("Y");
                     break;
                  case 14:
                     itemMaster.setUserId(this.general.checkNull(val.trim()));
                     break;
                  case 15:
                     itemMaster.setRemarks(this.general.checkNull(val.trim()));
                     break;
                  case 16:
                     itemMaster.setEntryDate(Date.valueOf(LocalDate.now()));
                     break;
                  case 17:
                     itemMaster.setTerminal(httpServletRequest.getRemoteAddr());
                  }
               }

               if (this.itemMasterRepository.findById(itemMaster.getItemCode()).isPresent()) {
                  this.itemMasterRepository.deleteById(itemMaster.getItemCode());
                  this.itemMasterRepository.save(itemMaster);
               } else {
                  System.out.println("save Items");
                  this.itemMasterRepository.save(itemMaster);
               }
            }
         }

         return "saved";
      } catch (Exception var13) {
         var13.printStackTrace();
         return "failed";
      }
   }

   public String importItemIssueXSSF(XSSFWorkbook workbook, HttpServletRequest httpServletRequest) {
      try {
         XSSFSheet firstSheet = workbook.getSheetAt(0);
         Iterator<Row> rowIterator = firstSheet.iterator();

         for(int i = 0; rowIterator.hasNext(); ++i) {
            Row nextRow = (Row)rowIterator.next();
            if (i > 0) {
               System.out.println("Row:---> " + i);
               ItemIssuedEntity itemIssue = new ItemIssuedEntity();
               Iterator cellIterator = nextRow.cellIterator();

               while(cellIterator.hasNext()) {
                  Cell nextCell = (Cell)cellIterator.next();
                  int columnIndex = nextCell.getColumnIndex();
                  switch(1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[nextCell.getCellTypeEnum().ordinal()]) {
                  case 1:
                     String stringValue = nextCell.getStringCellValue().trim();
                     switch(columnIndex) {
                     case 1:
                        itemIssue.setIssueId(stringValue);
                        continue;
                     case 2:
                        itemIssue.setProductCode(stringValue);
                        continue;
                     case 3:
                        itemIssue.setProductName(stringValue);
                     case 4:
                     case 8:
                     case 9:
                     case 12:
                     case 15:
                     default:
                        continue;
                     case 5:
                        itemIssue.setConsumptionType(stringValue);
                        continue;
                     case 6:
                        itemIssue.setConsumptionFor(stringValue);
                        continue;
                     case 7:
                        itemIssue.setItemCode(stringValue);
                        continue;
                     case 10:
                        itemIssue.setEmpId(stringValue);
                        continue;
                     case 11:
                        itemIssue.setRemark(stringValue);
                        continue;
                     case 13:
                        itemIssue.setTerminal(stringValue);
                        continue;
                     case 14:
                        itemIssue.setModifiedBy(stringValue);
                        continue;
                     case 16:
                        itemIssue.setRawMaterialStatus(stringValue);
                        continue;
                     }
                  case 2:
                     double numericValue = nextCell.getNumericCellValue();
                     switch(columnIndex) {
                     case 0:
                        itemIssue.setItemIssueId((int)numericValue);
                        break;
                     case 4:
                        itemIssue.setIssueDate(nextCell.getDateCellValue());
                        break;
                     case 8:
                        itemIssue.setQuantityInStock(numericValue);
                        break;
                     case 9:
                        itemIssue.setItemQuantity(numericValue);
                        break;
                     case 15:
                        itemIssue.setModifiedOn(nextCell.getDateCellValue());
                     }
                  }
               }

               try {
                  System.out.println(itemIssue);
                  Optional<ItemMasterEntity> item = this.itemMasterRepository.findByItemCode(itemIssue.getItemCode());
                  if (item.isPresent()) {
                     itemIssue.setQuantityInStock(((ItemMasterEntity)item.get()).getQuantityInStock());
                  } else {
                     itemIssue.setQuantityInStock(0.0D);
                  }

                  itemIssue.setRawMaterialStatus("A");
                  itemIssue.setEntryDate(new java.util.Date());
                  itemIssue.setIssueDate(new java.util.Date());
                  itemIssue.setTerminal(httpServletRequest.getRemoteAddr());
                  itemIssue.setModifiedOn(new java.util.Date());
                  this.itemIssuedRepository.save(itemIssue);
               } catch (Exception var14) {
                  var14.printStackTrace();
                  return "Failed to save data: " + var14.getMessage();
               }
            }
         }

         workbook.close();
         return "saved";
      } catch (Exception var15) {
         var15.printStackTrace();
         return "Failed to import data: " + var15.getMessage();
      }
   }
}
