package com.doritech.api.Service;

import com.doritech.api.Common.General;
import com.doritech.api.Common.Message;
import com.doritech.api.DTO.EmployeeMasterDTO;
import com.doritech.api.DTO.MenuMasterDTO;
import com.doritech.api.Entity.EmployeeMasterEntity;
import com.doritech.api.Entity.MenuAccessEntity;
import com.doritech.api.Entity.MenuMasterEntity;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Repository.EmployeeMasterRepository;
import com.doritech.api.Repository.MenuAccessRepository;
import com.doritech.api.Repository.MenuMasterRepository;
import com.doritech.api.Repository.ParamRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMasterService {
   private static final Logger logger = LoggerFactory.getLogger(EmployeeMasterService.class);
   @Autowired
   EmployeeMasterRepository empMasterRepo;
   @Autowired
   MenuAccessRepository menuAccessRepository;
   @Autowired
   MenuMasterRepository menuMasterRepository;
   @Autowired
   ParamService paramService;
   @Autowired
   ParamRepository paramRepository;
   @Autowired
   General general;
   Random random = new Random();

   public ResponseEntity employeeLogin(EmployeeMasterDTO employeeMasterDTO, HttpSession session) {
      ResponseEntity response = new ResponseEntity();

      try {
         if (!employeeMasterDTO.getEmail().isEmpty() && !employeeMasterDTO.getPassword().isEmpty()) {
            EmployeeMasterEntity employee;
            if ("1".equals(this.empMasterRepo.findByEmail(employeeMasterDTO.getEmail()).getLoginFlag())) {
               employee = this.empMasterRepo.getEmployeeMasterByEmailAndPass(employeeMasterDTO.getEmail(), employeeMasterDTO.getPassword());
               if (employee == null) {
                  logger.info("Authentication failed");
                  response.setStatusCode(HttpStatus.UNAUTHORIZED);
                  response.setMessage("Authentication failed");
               } else if ("Active".equalsIgnoreCase(employee.getStatus())) {
                  if (employeeMasterDTO.getEmpRole().equalsIgnoreCase(employee.getEmpRole())) {
                     session.setAttribute("loggedInUser", employee.getEmpName());
                     session.setAttribute("empId", employee.getEmpId());
                     BeanUtils.copyProperties(employee, employeeMasterDTO);
                     employeeMasterDTO.setPassword("");
                     logger.info("Authentication Successful");
                     response.setPayload(employeeMasterDTO);
                     response.setStatusCode(HttpStatus.OK.value());
                     response.setMessage("Authentication Successful");
                  } else {
                     response.setMessage("Wrong employee role");
                     response.setStatusCode(HttpStatus.FORBIDDEN.value());
                  }
               } else {
                  response.setStatusCode(HttpStatus.BAD_REQUEST.value());
                  response.setMessage("You are not an active employee");
               }
            } else if ("0".equals(this.empMasterRepo.findByEmail(employeeMasterDTO.getEmail()).getLoginFlag())) {
               employee = this.empMasterRepo.getEmployeeMasterByEmailAndMobile(employeeMasterDTO.getEmail());
               if (employee == null) {
                  logger.info("Authentication failed");
                  response.setStatusCode(HttpStatus.UNAUTHORIZED);
                  response.setMessage("Authentication failed");
               } else if ("Active".equalsIgnoreCase(employee.getStatus())) {
                  session.setAttribute("loggedInUser", employee.getEmpName());
                  session.setAttribute("empId", employee.getEmpId());
                  BeanUtils.copyProperties(employee, employeeMasterDTO);
                  employeeMasterDTO.setMobile(employee.getMobile());
                  logger.info("Authentication Successful");
                  response.setPayload(employeeMasterDTO);
                  response.setStatusCode(HttpStatus.OK.value());
                  response.setMessage("Authentication Successful");
               } else {
                  response.setStatusCode(HttpStatus.BAD_REQUEST.value());
                  response.setMessage("You are not an active employee");
               }
            }
         } else {
            response.setMessage("Email or Password should not be empty.");
         }
      } catch (Exception var5) {
         logger.error("Error in Login", var5);
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         response.setMessage("Login failed: " + var5.getLocalizedMessage());
      }

      return response;
   }

   public ResponseEntity sendOTP(String email, HttpSession session) {
      ResponseEntity response = new ResponseEntity();

      try {
         EmployeeMasterEntity employee = this.empMasterRepo.findByEmail(email);
         session.setAttribute("email", email);
         if (employee != null) {
            int otp = this.random.nextInt(9999);
            String subject = "OTP from Digitals";
            String message = "<div style='border:1px solid #e2e2e2;padding:20px'>Your One Time Password: <b>" + otp + "</b></div>";
            boolean flag = MailService.sendOTPToEmail(subject, message, email);
            if (flag) {
               response.setPayload(otp);
               response.setMessage("OTP sent successfully.");
               response.setStatusCode(HttpStatus.OK.value());
            } else {
               response.setMessage("Check OTP again");
               response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            }
         } else {
            response.setPayload(email);
            response.setMessage("Email ID is not present in the database!");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var10) {
         var10.printStackTrace();
         response.setMessage("Error sending OTP");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity verifyOtp(Integer otps, HttpSession session) {
      ResponseEntity response = new ResponseEntity();

      try {
         Integer myOtp = (Integer)session.getAttribute("otp");
         String email = (String)session.getAttribute("email");
         System.out.println("from frontend" + otps);
         System.out.println("from session" + myOtp);
         if (myOtp.equals(otps)) {
            response.setMessage("OTP verified successfully!");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            session.setAttribute("message", "You have entered the wrong OTP!..");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("You have entered wrong OTP!");
         }
      } catch (Exception var6) {
         var6.printStackTrace();
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         response.setMessage("Error verifying OTP");
      }

      return response;
   }

   public ResponseEntity updatePasswordByEmail(String email, String newPassword) {
      ResponseEntity response = new ResponseEntity();

      try {
         EmployeeMasterEntity employee = this.empMasterRepo.findByEmail(email);
         if (employee != null) {
            employee.setPassword(newPassword);
            employee.setLoginFlag("1");
            this.empMasterRepo.save(employee);
            response.setPayload(true);
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Password updated successfully");
         } else {
            response.setPayload(false);
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("Email ID is not present in database. Please try again");
         }
      } catch (Exception var5) {
         var5.printStackTrace();
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         response.setMessage("Error in updating password. Please Try again !");
      }

      return response;
   }

   public ResponseEntity saveEmployeeDetails(EmployeeMasterDTO employeeMasterDTO) {
      ResponseEntity response = new ResponseEntity();

      try {
         List<Message> validationMessages = this.validations(employeeMasterDTO);
         if (validationMessages.isEmpty()) {
            Optional<EmployeeMasterEntity> existingEmployee = this.empMasterRepo.findById(employeeMasterDTO.getEmpId());
            String empId = "";
            EmployeeMasterEntity employee;
            if (!existingEmployee.isPresent()) {
               employee = new EmployeeMasterEntity();
               BeanUtils.copyProperties(employeeMasterDTO, employee);
               employee.setPassword(employee.getMobile());
               employee.setStatus("Active");
               employee.setLoginFlag("0");
               this.empMasterRepo.save(employee);
               response.setPayload(employee);
               response.setMessage("New employee added successfully.");
               response.setStatusCode(HttpStatus.OK.value());
               logger.info("New employee added successfully with code: {}", employee.getEmpId());
            } else {
               employee = (EmployeeMasterEntity)existingEmployee.get();
               BeanUtils.copyProperties(employeeMasterDTO, employee);
               this.empMasterRepo.save(employee);
               response.setPayload(employee);
               response.setMessage("Employee updated successfully.");
               response.setStatusCode(HttpStatus.OK.value());
               logger.info("Employee updated successfully with code: {}", employee.getEmpId());
            }
         } else {
            response.setMessage("Employee requested is Invalid.");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setPayload(validationMessages);
            logger.warn("Invalid request for employee with code: {}", employeeMasterDTO.getEmpId());
         }
      } catch (Exception var7) {
         logger.error("Error processing employee data", var7);
         response.setMessage("Employee can't be added in the database");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity updateEmployeeDetails(EmployeeMasterDTO employeeMasterDTO) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         EmployeeMasterEntity employee = (EmployeeMasterEntity)this.empMasterRepo.getById(employeeMasterDTO.getEmpId());
         if (employee == null) {
            response.setMessage("employee not present in teh database");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }

         EmployeeMasterEntity employeeResponse = new EmployeeMasterEntity();
         BeanUtils.copyProperties(employeeMasterDTO, employeeResponse);
         employeeResponse.setStatus(employee.getStatus());
         employeeResponse.setLoginFlag(employee.getLoginFlag());
         employeeResponse.setPassword(employee.getPassword());
         employeeResponse.setGroupId(employee.getGroupId());
         this.empMasterRepo.save(employeeResponse);
         response.setMessage("employee updated successfully.");
         response.setPayload(employeeResponse);
         response.setStatusCode(HttpStatus.OK.value());
      } catch (Exception var5) {
         var5.printStackTrace();
         response.setMessage("Error updating employee details");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getAllEmployees() {
      ResponseEntity response = new ResponseEntity();

      try {
         List<EmployeeMasterEntity> employees = this.empMasterRepo.findAll();
         List<EmployeeMasterDTO> employeeMasterDTOList = new ArrayList();
         Iterator var4 = employees.iterator();

         while(var4.hasNext()) {
            EmployeeMasterEntity employee = (EmployeeMasterEntity)var4.next();
            EmployeeMasterDTO employeeMasterDTO = new EmployeeMasterDTO();
            employee.setPassword("");
            BeanUtils.copyProperties(employee, employeeMasterDTO);
            employeeMasterDTOList.add(employeeMasterDTO);
         }

         response.setPayload(employeeMasterDTOList);
         response.setMessage("Employees retrieved successfully.");
         response.setStatusCode(HttpStatus.OK.value());
         logger.info("Employees retrieved successfully.");
      } catch (Exception var7) {
         logger.error("Error processing employee data", var7);
         response.setMessage("Employees can't be retrieved from the database");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }

   public ResponseEntity getMenuAssignedBasedOnEmpId(String empId) {
      ResponseEntity response = new ResponseEntity();

      try {
         List<MenuMasterDTO> menuMasterList = new ArrayList();
         String empRole = this.empMasterRepo.findByEmpId(empId).getEmpRole();
         if (this.general.checkNull(empRole) != null) {
            List<MenuAccessEntity> userRolesList = this.menuAccessRepository.findByRoleName(empRole);
            userRolesList.forEach((x) -> {
               MenuMasterEntity menuMaster = (MenuMasterEntity)this.menuMasterRepository.getById(x.getMenuId());
               MenuMasterDTO menuDto = new MenuMasterDTO();
               BeanUtils.copyProperties(menuMaster, menuDto);
               menuMasterList.add(menuDto);
            });
         }

         response.setPayload(menuMasterList);
         response.setStatusCode(HttpStatus.OK.value());
         response.setMessage("Menu Details based on user ID fetched successfully.");
      } catch (Exception var6) {
         var6.printStackTrace();
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
         response.setMessage("Failed to get Menu Details based on User ID: " + var6.getLocalizedMessage());
      }

      return response;
   }

   public ResponseEntity getMenuDetailsByRoleType(String empRole) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         HashMap<String, List<MenuMasterDTO>> roleAndMenu = new HashMap();
         if (this.general.checkNull(empRole) != null) {
            List<MenuAccessEntity> employeeRolesList = this.menuAccessRepository.findByRoleName(empRole);
            List<MenuMasterDTO> menuMasterList = new ArrayList();
            employeeRolesList.forEach((role) -> {
               MenuMasterEntity menuMaster = (MenuMasterEntity)this.menuMasterRepository.getById(role.getMenuId());
               MenuMasterDTO menuDto = new MenuMasterDTO();
               BeanUtils.copyProperties(menuMaster, menuDto);
               menuMasterList.add(menuDto);
            });
            roleAndMenu.put(empRole, menuMasterList);
            response.setPayload(roleAndMenu);
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("User Type and Roles fetched successfully.");
         } else {
            response.setMessage("User Type not found: " + empRole);
            response.setStatusCode(HttpStatus.BAD_REQUEST);
         }
      } catch (Exception var6) {
         var6.printStackTrace();
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
         response.setMessage("User Role fetching failed: " + var6.getLocalizedMessage());
      }

      return response;
   }

   public List<Message> validations(EmployeeMasterDTO employeeMasterDTO) {
      List<Message> messageList = new ArrayList();
      if (StringUtils.isEmpty(employeeMasterDTO.getEmpRole()) || employeeMasterDTO.getEmpRole() == null) {
         messageList.add(new Message("Employee Role", "Employee Roll can't be null or empty"));
      }

      if (StringUtils.isEmpty(employeeMasterDTO.getEmpName()) || employeeMasterDTO.getEmpName() == null) {
         messageList.add(new Message("Employee Name", "Employee Name can't be null or empty"));
      }

      if (StringUtils.isEmpty(employeeMasterDTO.getEmpPan()) || employeeMasterDTO.getEmpPan() == null) {
         messageList.add(new Message("PAN Number", "Employee PAN can't be null or empty"));
      }

      if (StringUtils.isEmpty(employeeMasterDTO.getAddress()) || employeeMasterDTO.getAddress() == null) {
         messageList.add(new Message("Address", "Address can't be null or empty"));
      }

      if (StringUtils.isEmpty(employeeMasterDTO.getCity()) || employeeMasterDTO.getCity() == null) {
         messageList.add(new Message("City", "City can't be null or empty"));
      }

      if (StringUtils.isEmpty(employeeMasterDTO.getPin()) || employeeMasterDTO.getPin() == null) {
         messageList.add(new Message("Pin Code", "Pin Code can't be null or empty"));
      }

      if (StringUtils.isEmpty(employeeMasterDTO.getState()) || employeeMasterDTO.getState() == null) {
         messageList.add(new Message("State", "State can't be null or empty"));
      }

      if (StringUtils.isEmpty(employeeMasterDTO.getCountry()) || employeeMasterDTO.getCountry() == null) {
         messageList.add(new Message("Country", "Country can't be null or empty"));
      }

      Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
      if (employeeMasterDTO.getEmail() == null || !emailPattern.matcher(employeeMasterDTO.getEmail()).matches()) {
         messageList.add(new Message("Email ID", "Invalid Email ID"));
      }

      if (StringUtils.isEmpty(employeeMasterDTO.getBranch()) || employeeMasterDTO.getBranch() == null) {
         messageList.add(new Message("Branch", "Branch can't be null or empty"));
      }

      return messageList;
   }

   public ResponseEntity getEmployeeByGroupId(String groupId) {
      ResponseEntity response = new ResponseEntity();

      try {
         List<EmployeeMasterDTO> responseList = new ArrayList();
         List<EmployeeMasterEntity> employeeMaster = this.empMasterRepo.findByGroupId(groupId);
         if (!employeeMaster.isEmpty()) {
            Iterator var5 = employeeMaster.iterator();

            while(var5.hasNext()) {
               EmployeeMasterEntity entity = (EmployeeMasterEntity)var5.next();
               EmployeeMasterDTO dto = new EmployeeMasterDTO();
               BeanUtils.copyProperties(entity, dto);
               dto.setEmpId(entity.getEmpId());
               responseList.add(dto);
            }

            response.setPayload(responseList);
            response.setMessage("Employee Master data fetched successfully");
            response.setStatusCode(HttpStatus.OK.value());
            logger.info("Employee Master data fetched successfully.", responseList.size());
         } else {
            response.setMessage("Employee Master data not found");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            logger.warn("Employee Master data not found");
         }
      } catch (Exception var8) {
         response.setMessage("Error fetching data");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         logger.error("Error fetching Employee Master data", var8);
      }

      return response;
   }

   public ResponseEntity getEmployeeByEmpId(String empId) {
      ResponseEntity response = new ResponseEntity();

      try {
         Optional<EmployeeMasterEntity> optional = this.empMasterRepo.findById(empId);
         if (optional.isPresent()) {
            EmployeeMasterEntity employeeMaster = (EmployeeMasterEntity)optional.get();
            EmployeeMasterDTO dto = new EmployeeMasterDTO();
            BeanUtils.copyProperties(employeeMaster, dto);
            response.setPayload(dto);
            response.setMessage("Employee Master data fetched successfully");
            response.setStatusCode(HttpStatus.OK.value());
            logger.info("Employee Master data fetched successfully.", response);
         } else {
            response.setMessage("Employee Master data not found");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            logger.warn("Employee Master data not found");
         }
      } catch (Exception var6) {
         response.setMessage("Error fetching data");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         logger.error("Error fetching Employee Master data", var6);
      }

      return response;
   }

   public ResponseEntity getAllMenuDetailsBasedOnEmpId(String empId) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         if (empId != null) {
            String empRole = this.empMasterRepo.findByEmpId(empId).getEmpRole();
            List<MenuAccessEntity> menuAccessEntities = this.menuAccessRepository.findByRoleName(empRole);
            List<MenuMasterEntity> menuMaster = this.menuMasterRepository.findAll();
            List<MenuMasterEntity> responseList = new ArrayList();
            Iterator var7 = menuAccessEntities.iterator();

            while(var7.hasNext()) {
               MenuAccessEntity menu = (MenuAccessEntity)var7.next();
               Iterator var9 = menuMaster.iterator();

               while(var9.hasNext()) {
                  MenuMasterEntity master = (MenuMasterEntity)var9.next();
                  if (menu.getMenuId() == master.getId()) {
                     responseList.add(master);
                  }
               }
            }

            response.setPayload(responseList);
            response.setMessage("All menu details have been fetched");
            response.setStatusCode(HttpStatus.OK.value());
         } else {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Provided empId is not present in the database");
         }
      } catch (Exception var11) {
         var11.printStackTrace();
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         response.setMessage("Error fetching all menu details");
      }

      return response;
   }

   public ResponseEntity changeUserPassword(String empId) throws Exception {
      ResponseEntity response = new ResponseEntity();

      try {
         EmployeeMasterEntity employee = this.empMasterRepo.findByEmpId(empId);
         if (employee != null) {
            if (!employee.getLoginFlag().equals("0")) {
               response.setMessage("");
            }
         } else {
            response.setMessage("Employee is not present in database.");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
         }
      } catch (Exception var4) {
         var4.printStackTrace();
         response.setMessage("Error is Setting Password");
         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }

      return response;
   }
}
