package com.appdev.MindFlow.model;
import jakarta.persistence.*;
@Entity
@Table(name = "users")
public class User {
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   
   private Long id; 
   @Column(nullable = false, unique = true, length = 45)
   
   private String email;
   @Column(nullable = false, length = 50)
   
   private String password;
 
  
   
   // Getters and Setters
   public Long getId() {
       return id;
   }
   public void setId(Long id) {
   	this.id = id;
   }
   public String getEmail() {
       return email;
   }
   public void setEmail(String email) {
       this.email = email;
   }
   public String getPassword() {
       return password;
   }
   public void setPassword(String password) {
       this.password = password;
   }
  

}
   

