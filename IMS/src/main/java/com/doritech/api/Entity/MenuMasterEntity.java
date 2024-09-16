package com.doritech.api.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
   name = "menu_master"
)
public class MenuMasterEntity {
   @Id
   @Column(
      name = "menu_id"
   )
   @GeneratedValue(
      strategy = GenerationType.AUTO
   )
   private int id;
   @Column(
      name = "parent_id"
   )
   private int parentId;
   @Column(
      name = "child_id"
   )
   private int childId;
   @Column(
      name = "menu_name"
   )
   private String menuName;
   @Column(
      name = "menu_handler_name"
   )
   private String menuHandlerName;
   @Column(
      name = "menu_icon"
   )
   private String menuIcon;

   public MenuMasterEntity() {
   }

   public MenuMasterEntity(int id, int parentId, int childId, String menuName, String menuHandlerName, String menuIcon) {
      this.id = id;
      this.parentId = parentId;
      this.childId = childId;
      this.menuName = menuName;
      this.menuHandlerName = menuHandlerName;
      this.menuIcon = menuIcon;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getParentId() {
      return this.parentId;
   }

   public void setParentId(int parentId) {
      this.parentId = parentId;
   }

   public int getChildId() {
      return this.childId;
   }

   public void setChildId(int childId) {
      this.childId = childId;
   }

   public String getMenuName() {
      return this.menuName;
   }

   public void setMenuName(String menuName) {
      this.menuName = menuName;
   }

   public String getMenuHandlerName() {
      return this.menuHandlerName;
   }

   public void setMenuHandlerName(String menuHandlerName) {
      this.menuHandlerName = menuHandlerName;
   }

   public String getMenuIcon() {
      return this.menuIcon;
   }

   public void setMenuIcon(String menuIcon) {
      this.menuIcon = menuIcon;
   }

   public String toString() {
      return "MenuMasterEntity{id=" + this.id + ", parentId=" + this.parentId + ", childId=" + this.childId + ", menuName='" + this.menuName + '\'' + ", menuHandlerName='" + this.menuHandlerName + '\'' + ", menuIcon='" + this.menuIcon + '\'' + '}';
   }
}
