package com.doritech.api.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
   name = "menu_access"
)
public class MenuAccessEntity {
   @Id
   @GeneratedValue(
      strategy = GenerationType.AUTO
   )
   @Column(
      name = "id"
   )
   private int id;
   @Column(
      name = "role_id"
   )
   private int roleId;
   @Column(
      name = "role_name"
   )
   private String roleName;
   @Column(
      name = "menu_id"
   )
   private int menuId;

   public MenuAccessEntity() {
   }

   public MenuAccessEntity(int id, int roleId, String roleName, int menuId) {
      this.id = id;
      this.roleId = roleId;
      this.roleName = roleName;
      this.menuId = menuId;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getRoleId() {
      return this.roleId;
   }

   public void setRoleId(int roleId) {
      this.roleId = roleId;
   }

   public String getRoleName() {
      return this.roleName;
   }

   public void setRoleName(String roleName) {
      this.roleName = roleName;
   }

   public int getMenuId() {
      return this.menuId;
   }

   public void setMenuId(int menuId) {
      this.menuId = menuId;
   }

   public String toString() {
      return "MenuAccessEntity{id=" + this.id + ", roleId=" + this.roleId + ", roleName='" + this.roleName + '\'' + ", menuId=" + this.menuId + '}';
   }
}
