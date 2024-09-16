package com.doritech.api.Controller;

import com.doritech.api.DTO.EmployeeMasterDTO;
import com.doritech.api.Entity.ParamEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.ParamRepository;
import com.doritech.api.Service.EmployeeMasterService;
import com.doritech.api.Service.ParamService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class EmployeeMasterController {
   @Autowired
   EmployeeMasterService employeeMasterService;
   @Autowired
   ParamService paramService;
   @Autowired
   ParamRepository paramRepository;

   @PostMapping({"employeeLogin"})
   @ResponseBody
   public ResponseEntity employeeLogin(@RequestBody EmployeeMasterDTO employeeMasterDTO, HttpSession session) {
      return this.employeeMasterService.employeeLogin(employeeMasterDTO, session);
   }

   @PutMapping({"updatePasswordByEmail"})
   @ResponseBody
   public ResponseEntity updatePasswordByEmail(@RequestParam("email") String email, @RequestParam("newPassword") String newPassword) {
      return this.employeeMasterService.updatePasswordByEmail(email, newPassword);
   }

   @PostMapping({"saveEmployeeDetails"})
   @ResponseBody
   public ResponseEntity saveEmployeeDetails(@RequestBody EmployeeMasterDTO employeeMasterDTO) throws Exception {
      ParamEntity paramEntity = this.paramRepository.findByCode("EMPLOYEE_ID");
      employeeMasterDTO.setEmpId(this.paramService.getEmployeeDescpValue());
      this.paramService.updateEmployeeDescp2Value(paramEntity);
      return this.employeeMasterService.saveEmployeeDetails(employeeMasterDTO);
   }

   @PutMapping({"updateEmployeeDetails"})
   @ResponseBody
   public ResponseEntity updateEmployeeDetails(@RequestBody EmployeeMasterDTO employeeMasterDTO) throws Exception {
      return this.employeeMasterService.updateEmployeeDetails(employeeMasterDTO);
   }

   @PostMapping({"getMenuDetailsByRoleType"})
   @ResponseBody
   public ResponseEntity getMenuDetailsByRoleType(@ModelAttribute @RequestBody String empRole) throws Exception {
      return this.employeeMasterService.getMenuDetailsByRoleType(empRole);
   }

   @GetMapping({"getAllEmployees"})
   @ResponseBody
   public ResponseEntity getAllEmployees() throws Exception {
      return this.employeeMasterService.getAllEmployees();
   }

   @GetMapping({"getMenuAssignedBasedOnEmpId"})
   @ResponseBody
   public ResponseEntity getMenuAssignedBasedOnEmpId(@RequestParam("empId") String empId) throws Exception {
      return this.employeeMasterService.getMenuAssignedBasedOnEmpId(empId);
   }

   @GetMapping({"getEmployeeByGroupId"})
   public ResponseEntity getEmployeeByGroupId(@RequestParam String groupId) {
      return this.employeeMasterService.getEmployeeByGroupId(groupId);
   }

   @GetMapping({"getEmployeeByEmpId"})
   public ResponseEntity getEmployeeByEmpId(@RequestParam String empId) {
      return this.employeeMasterService.getEmployeeByEmpId(empId);
   }

   @GetMapping({"getAllMenuDetailsBasedOnEmpId"})
   @ResponseBody
   public ResponseEntity getAllMenuDetailsBasedOnEmpId(@RequestParam String empId) throws Exception {
      return this.employeeMasterService.getAllMenuDetailsBasedOnEmpId(empId);
   }
}
