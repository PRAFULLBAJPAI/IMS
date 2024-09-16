 package com.doritech.api.Controller;

import com.doritech.api.DTO.EmployeeMasterDTO;
import com.doritech.api.DTO.GroupMasterDTO;
import com.doritech.api.DTO.VendorMasterDTO;
import com.doritech.api.Entity.ParamEntity;
import com.doritech.api.Repository.BranchMasterRepository;
import com.doritech.api.Repository.EmployeeMasterRepository;
import com.doritech.api.Repository.GroupMasterRepository;
import com.doritech.api.Repository.ItemMasterRepository;
import com.doritech.api.Repository.ParamRepository;
import com.doritech.api.Service.ClientEntityService;
import com.doritech.api.Service.DispatchEntityService;
import com.doritech.api.Service.EmployeeMasterService;
import com.doritech.api.Service.GroupMasterService;
import com.doritech.api.Service.HsnMasterService;
import com.doritech.api.Service.ItemIssuedService;
import com.doritech.api.Service.ItemMasterService;
import com.doritech.api.Service.ItemRecievedService;
import com.doritech.api.Service.MenuAccessService;
import com.doritech.api.Service.MenuMasterService;
import com.doritech.api.Service.ParamService;
import com.doritech.api.Service.ProductionEntityService;
import com.doritech.api.Service.RawItemIssueService;
import com.doritech.api.Service.VendorMasterService;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ViewController {
   @Autowired
   EmployeeMasterService employeeService;
   @Autowired
   EmployeeMasterRepository employeeMasterRepository;
   @Autowired
   ParamRepository paramRepository;
   @Autowired
   ParamService paramService;
   @Autowired
   MenuAccessService menuAccessService;
   @Autowired
   MenuMasterService menuMasterService;
   @Autowired
   GroupMasterService groupMasterService;
   @Autowired
   EmployeeMasterService empService;
   @Autowired
   GroupMasterRepository groupMasterRepository;
   @Autowired
   BranchMasterRepository branchMasterRepository;
   @Autowired
   VendorMasterService vendorMasterService;
   @Autowired
   ItemIssuedService itemIssuedService;
   @Autowired
   ItemMasterService itemMasterService;
   @Autowired
   ItemMasterRepository itemMasterRepository;
   @Autowired
   RawItemIssueService rawItemIssueService;
   @Autowired
   HsnMasterService hsnMasterService;
   @Autowired
   ItemRecievedService itemRecievedService;
   @Autowired
   ProductionEntityService productionEntityService;
   @Autowired
   ClientEntityService clientEntityService;
   @Autowired
   DispatchEntityService dispatchEntityService;

   @GetMapping({"/welcome"})
   public String welcome(Model model, HttpSession session) throws Exception {
      String loggedInUser = (String)session.getAttribute("loggedInUser");
      String loggedInUserEmpId = (String)session.getAttribute("empId");
      System.out.println(loggedInUserEmpId);
      if (loggedInUser != null && loggedInUserEmpId != null) {
         model.addAttribute("loggedInUser", loggedInUser);
         model.addAttribute("empId", loggedInUserEmpId);
         model.addAttribute("menuList", this.employeeService.getAllMenuDetailsBasedOnEmpId(loggedInUserEmpId));
         System.out.println("EmpId is :" + this.employeeService.getAllMenuDetailsBasedOnEmpId(loggedInUserEmpId).toString());
         model.addAttribute("paramData", this.paramService.getParamByCodeAndSerial());
         model.addAttribute("paramClient", this.paramService.getParamForClientByCodeAndSerial());
      }

      return "welcome";
   }

   @GetMapping({"/menuAccessScreen"})
   public String roleMasterScreen(Model model) throws Exception {
      model.addAttribute("rolesList", this.menuAccessService.getAllRoles());
      model.addAttribute("employeeMaster", this.employeeService.getAllEmployees());
      model.addAttribute("menuList", this.menuMasterService.getAllMenuDetails());
      return "roleMaster";
   }

   @GetMapping({"/employeeMasterScreen"})
   public String employeeMasterScreen(Model model, @ModelAttribute EmployeeMasterDTO employeeMasterDTO) throws Exception {
      ParamEntity param = this.paramRepository.findByCode("EMPLOYEE_ID");
      model.addAttribute("employeeList", this.employeeMasterRepository.findAll());
      model.addAttribute("employeeMaster", this.empService.getAllEmployees());
      model.addAttribute("empId", param.getDescp1() + param.getDescp2());
      model.addAttribute("groupList", this.groupMasterRepository.findAll());
      model.addAttribute("branchList", this.branchMasterRepository.findAll());
      return "employeeMaster";
   }

   @GetMapping({"/getAllEmployeeMasterScreen"})
   public String getAllEmployeesOnScreen(Model model) throws Exception {
      model.addAttribute("employeeMaster", this.empService.getAllEmployees());
      return "employeeMaster";
   }

   @GetMapping({"/groupMasterScreen"})
   public String groupMasterScreen(Model model, @ModelAttribute GroupMasterDTO groupMasterDTO) throws Exception {
      model.addAttribute("employeeList", this.employeeMasterRepository.findAll());
      model.addAttribute("groupMaster", this.groupMasterService.getAllGroupMaster());
      model.addAttribute("groupMasterDTO", groupMasterDTO);
      return "groupMaster";
   }

   @GetMapping({"/"})
   public String loginIMS(Model model) {
      model.addAttribute("paramData", this.paramService.getParamByCodeAndSerial());
      model.addAttribute("paramClient", this.paramService.getParamForClientByCodeAndSerial());
      return "loginPage";
   }

   @GetMapping({"/changePassword"})
   public String changePassword(Model model, HttpSession session) {
      String email = (String)session.getAttribute("email");
      model.addAttribute("email", email);
      return "changePassword";
   }

   @GetMapping({"/homePage"})
   public String getHomePage(Model model) {
      model.addAttribute("productList", this.itemMasterService.getAllItemMaster());
      model.addAttribute("totalCount", this.itemMasterRepository.getAllItemsCount());
      model.addAttribute("rawCount", this.itemMasterRepository.getRawItemsCount());
      model.addAttribute("subproductCount", this.itemMasterRepository.getSubproductItemsCount());
      model.addAttribute("finalProductCount", this.itemMasterRepository.getFinalProductItemsCount());
      return "homePage";
   }

   @GetMapping({"/allMasters"})
   public String getAllMasters() {
      return "allMasters";
   }

   @GetMapping({"/reports"})
   public String getAllReports() {
      return "reports";
   }

   @GetMapping({"/viewItemMaster"})
   public String viewItemMaster(Model model, HttpSession session) throws Exception {
      String loggedInUser = (String)session.getAttribute("loggedInUser");
      String loggedInUserEmpId = (String)session.getAttribute("empId");
      if (loggedInUser != null && loggedInUserEmpId != null) {
         model.addAttribute("loggedInUser", loggedInUser);
      }

      ParamEntity paramEntity = this.paramRepository.findByCode("ITEM_CODE");
      model.addAttribute("paramItem", this.paramService.getItemDescpValue());
      ParamEntity param = this.paramRepository.findByCode("IMAGE_CODE");
      model.addAttribute("imageId", this.paramService.getImageDescpValue());
      model.addAttribute("hsnMaster", this.hsnMasterService.getAllHsnMaster());
      model.addAttribute("itemTypeList", this.paramService.getAllConsumptionFor());
      model.addAttribute("vendorMasterList", this.vendorMasterService.getAllVendorMaster());
      model.addAttribute("itemUomList", this.paramService.getAllItemUomType());
      model.addAttribute("warehouseList", this.paramService.getAllWarehouse());
      return "itemMaster";
   }

   @GetMapping({"/vendorMaster"})
   public String getAllVendors(Model model, @ModelAttribute VendorMasterDTO vendorMasterDTO, HttpServletRequest httpServletRequest, HttpSession session) throws Exception {
      String loggedInUser = (String)session.getAttribute("loggedInUser");
      String loggedInUserEmpId = (String)session.getAttribute("empId");
      System.out.println(loggedInUserEmpId);
      if (loggedInUser != null && loggedInUserEmpId != null) {
         model.addAttribute("loggedInUser", loggedInUser);
         model.addAttribute("empId", loggedInUserEmpId);
      }

      if (vendorMasterDTO.getVendorId() == null) {
         ParamEntity param = this.paramRepository.findByCode("VENDOR_ID");
         String vendorId = param.getDescp1() + param.getDescp2();
         vendorMasterDTO.setVendorId(vendorId);
         vendorMasterDTO.setEntryDate(new Date());
         vendorMasterDTO.setTerminal(httpServletRequest.getRemoteAddr());
         model.addAttribute("vendorMasterDTO", vendorMasterDTO);
      }

      model.addAttribute("vendorList", this.vendorMasterService.getAllVendorMaster());
      return "vendorMaster";
   }

   @GetMapping({"/filterItemIssued"})
   public String filterItemIssued(Model model) throws Exception {
      model.addAttribute("issuedItemsList", this.itemIssuedService.getAllIssuedItems());
      return "filterItemIssued";
   }

   @GetMapping({"/itemIssue"})
   public String itemsIssue(Model model, HttpSession session) throws Exception {
      String loggedInUserEmpId = (String)session.getAttribute("empId");
      ParamEntity paramEntity = this.paramRepository.findByCode("ISSUE_ID");
      String issueId = this.paramService.getIssueIdDescpValue();
      model.addAttribute("empId", loggedInUserEmpId);
      model.addAttribute("issueId", issueId);
      model.addAttribute("productCodeList", this.itemMasterRepository.findByConsumptionType());
      model.addAttribute("issuedItemsList", this.itemIssuedService.getAllIssuedItems());
      model.addAttribute("consumptionForList", this.paramService.getAllConsumptionType());
      model.addAttribute("consumptionTypeList", this.paramService.getAllConsumptionFor());
      model.addAttribute("itemMastersList", this.itemMasterService.getAllItemMaster());
      model.addAttribute("itemList", this.itemMasterService.getAllItemMaster());
      return "itemIssue";
   }

   @GetMapping({"/itemPurchase"})
   public String itemPurchase(Model model, HttpSession session) throws Exception {
      String loggedInUserEmpId = (String)session.getAttribute("empId");
      ParamEntity paramEntity = this.paramRepository.findByCode("RECEIVE_ID");
      String receiveId = this.paramService.getReceiveIdDescpValue();
      model.addAttribute("employeeId", loggedInUserEmpId);
      model.addAttribute("receiveId", receiveId);
      model.addAttribute("vendorList", this.vendorMasterService.getAllVendorMaster());
      model.addAttribute("warehouseList", this.paramService.getAllWarehouse());
      model.addAttribute("itemMastersList", this.itemMasterService.getAllItemMaster());
      return "itemPurchase";
   }

   @GetMapping({"/deviceMaster"})
   public String deviceMaster() throws Exception {
      return "deviceMaster";
   }

   @GetMapping({"/productionEntity"})
   public String productionEntity(Model model, HttpSession session) throws Exception {
      String loggedInUser = (String)session.getAttribute("loggedInUser");
      String loggedInUserEmpId = (String)session.getAttribute("empId");
      ParamEntity paramEntity = this.paramRepository.findByCode("PRODUCT_ID");
      String productId = paramEntity.getDescp1() + paramEntity.getDescp2();
      System.out.println(loggedInUserEmpId);
      if (loggedInUser != null && loggedInUserEmpId != null) {
         model.addAttribute("loggedInUser", loggedInUser);
         model.addAttribute("empId", loggedInUserEmpId);
      }

      model.addAttribute("productId", productId);
      model.addAttribute("itemMastersList", this.itemMasterService.getAllItemMaster());
      model.addAttribute("consumptionForList", this.paramService.getAllConsumptionType());
      model.addAttribute("itemUomList", this.paramService.getAllItemUomType());
      model.addAttribute("hsnMaster", this.hsnMasterService.getAllHsnMaster());
      return "productionEntity";
   }

   @GetMapping({"/stockQuantityReports"})
   public String stockQuantityReports(Model model) throws Exception {
      model.addAttribute("itemMasterList", this.itemMasterService.getAllItemMaster());
      return "stockQuantityReport";
   }

   @GetMapping({"/subproductReport"})
   public String subproductReport(Model model) throws Exception {
      model.addAttribute("itemList", this.itemMasterService.getAllRawItemsForSubFinalProduct());
      return "subproductReport";
   }

   @GetMapping({"/purchasedStock"})
   public String purchasedStock(Model model) throws Exception {
      model.addAttribute("purchasedItemList", this.itemRecievedService.getAllItemRecieved());
      return "purchasedStock";
   }

   @GetMapping({"/dailyProductionReport"})
   public String dailyProductionReport(Model model) throws Exception {
      model.addAttribute("productionItemsList", this.productionEntityService.getAllProductionEntity());
      return "dailyProductionReport";
   }

   @GetMapping({"/dispatchEntity"})
   public String dispatchEntity(Model model) throws Exception {
      ParamEntity paramEntity = this.paramRepository.findByCode("DISPATCH_ID");
      model.addAttribute("dispatchId", paramEntity.getDescp1() + paramEntity.getDescp2());
      model.addAttribute("clientList", this.clientEntityService.getAllClientDetails());
      return "dispatchEntity";
   }

   @GetMapping({"/clientEntity"})
   public String getClientScreen(Model model, HttpSession session) throws Exception {
      String loggedInUser = (String)session.getAttribute("loggedInUser");
      String loggedInUserEmpId = (String)session.getAttribute("empId");
      if (loggedInUser != null && loggedInUserEmpId != null) {
         model.addAttribute("loggedInUser", loggedInUser);
         model.addAttribute("empId", loggedInUserEmpId);
      }

      ParamEntity paramEntity = this.paramRepository.findByCode("CLIENT_ID");
      model.addAttribute("clientId", paramEntity.getDescp1() + paramEntity.getDescp2());
      model.addAttribute("clientList", this.clientEntityService.getAllClientDetails());
      return "clientEntity";
   }

   @GetMapping({"/dispatchReport"})
   public String dispatchReport(Model model) throws Exception {
      model.addAttribute("dispatchedItemList", this.dispatchEntityService.getAllDispatchEntity());
      return "dispatchReport";
   }

   @GetMapping({"/rawItemsIssue"})
   public String rawItemsIssue(Model model) throws Exception {
      ParamEntity paramEntity = this.paramRepository.findByCode("RAW_ISSUE_ID");
      model.addAttribute("rawIssueId", paramEntity.getDescp1() + paramEntity.getDescp2());
      model.addAttribute("consumptionTypeList", this.paramService.getAllConsumptionFor());
      model.addAttribute("consumptionForList", this.paramService.getAllConsumptionType());
      return "rawItemsIssue";
   }

   @GetMapping({"/rawItemsIssueReport"})
   public String rawItemsIssueReport(Model model, HttpSession session) throws Exception {
      String loggedInUser = (String)session.getAttribute("loggedInUser");
      String loggedInUserEmpId = (String)session.getAttribute("empId");
      if (loggedInUser != null && loggedInUserEmpId != null) {
         model.addAttribute("loggedInUser", loggedInUser);
         model.addAttribute("empId", loggedInUserEmpId);
      }

      model.addAttribute("rawItemsIssueList", this.rawItemIssueService.getAllRawItemsReport());
      return "rawItemsIssueReport";
   }
}