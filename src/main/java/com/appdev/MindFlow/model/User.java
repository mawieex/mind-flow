package com.appdev.MindFlow.model;
import jakarta.persistence.*;
@Entity
@Table(name = "users2")
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id; // Changed from Integer to Long
   @Column(nullable = false, unique = true, length = 45)
   private String email;
   @Column(nullable = false, length = 50)
   private String password;
   @Column(nullable = false, length = 45)
   private String username;
   
   @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
   private boolean verified = false;
   
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
   public String getUsername() {
       return username;
   }
   public void setUsername(String username) {
       this.username = username;
   }
public boolean isVerified() {
	return verified;
}
public void setVerified(boolean verified) {
	this.verified = verified;
}
   
}
