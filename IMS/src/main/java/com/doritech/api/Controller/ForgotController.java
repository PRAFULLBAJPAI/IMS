   package com.doritech.api.Controller;

import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Service.EmployeeMasterService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgotController {
   @Autowired
   private EmployeeMasterService employeeService;

   @GetMapping({"sendOTP"})
   @ResponseBody
   public ResponseEntity sendOTP(@RequestParam("email") String email, HttpSession session) {
      return this.employeeService.sendOTP(email, session);
   }

   @PostMapping({"verifyOTP"})
   @ResponseBody
   public ResponseEntity verifyOtp(@RequestParam("otps") Integer otps, HttpSession session) {
      return this.employeeService.verifyOtp(otps, session);
   }

   @PostMapping({"updatePasswordByEmail"})
   @ResponseBody
   public ResponseEntity updatePasswordByEmail(@RequestParam("email") String email, @RequestParam("password") String newPassword) {
      return this.employeeService.updatePasswordByEmail(email, newPassword);
   }
}