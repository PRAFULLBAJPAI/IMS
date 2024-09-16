 package com.doritech.api.Controller;

import com.doritech.api.DTO.MenuAccessDTO;
import com.doritech.api.Entity.ResponseEntity;
import com.doritech.api.Service.MenuAccessService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MenuAccessController {
   @Autowired
   private MenuAccessService menuAccessService;

   @PostMapping({"saveOrEditRoleMaster"})
   @ResponseBody
   public ResponseEntity saveOrEditRoleMaster(@RequestBody MenuAccessDTO menuAccessDTO) {
      return this.menuAccessService.saveOrEditRoleMaster(menuAccessDTO);
   }

   @GetMapping({"getAllRoles"})
   @ResponseBody
   public ResponseEntity getAllRoles() {
      return this.menuAccessService.getAllRoles();
   }

   @PutMapping({"assignMenuDetailsToRole"})
   @ResponseBody
   public ResponseEntity assignMenuDetailsToRole(@RequestParam("empRole") String empRole, @RequestParam("menuId") int id) {
      return this.menuAccessService.assignMenuDetailsToRole(empRole, id);
   }

   @PutMapping({"revokeMenuDetailsFromTheRole"})
   @ResponseBody
   public ResponseEntity revokeMenuDetailsFromTheRole(@RequestParam("empRole") String empRole, @RequestParam("menuId") int id) {
      return this.menuAccessService.revokeMenuDetailsFromTheRole(empRole, id);
   }

   @GetMapping({"getMenuDetailsByRole"})
   @ResponseBody
   public ResponseEntity getMenuDetailsByRole(@RequestParam String empRole) throws Exception {
      return this.menuAccessService.getMenuDetailsByRole(empRole);
   }
}
    