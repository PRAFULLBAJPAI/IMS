 package com.doritech.api.DTO;

public class UserEntityDTO {
   private int userId;
   private String username;
   private String password;
   private String userRole;
   private String email;
   private String firstName;
   private String lastName;

   public UserEntityDTO() {
   }

   public UserEntityDTO(int userId, String username, String password, String userRole, String email, String firstName, String lastName) {
      this.userId = userId;
      this.username = username;
      this.password = password;
      this.userRole = userRole;
      this.email = email;
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public int getUserId() {
      return this.userId;
   }

   public void setUserId(int userId) {
      this.userId = userId;
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getUserRole() {
      return this.userRole;
   }

   public void setUserRole(String userRole) {
      this.userRole = userRole;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String toString() {
      return "UserEntityDTO{userId='" + this.userId + '\'' + ", username='" + this.username + '\'' + ", password='" + this.password + '\'' + ", userRole='" + this.userRole + '\'' + ", email='" + this.email + '\'' + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName + '\'' + '}';
   }
}