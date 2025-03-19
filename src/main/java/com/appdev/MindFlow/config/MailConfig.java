/*
 * package com.appdev.MindFlow.config;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.mail.javamail.JavaMailSender; import
 * org.springframework.mail.javamail.JavaMailSenderImpl;
 * 
 * import java.util.Properties;
 * 
 * @Configuration public class MailConfig {
 * 
 * @Bean public JavaMailSender javaMailSender() { JavaMailSenderImpl mailSender
 * = new JavaMailSenderImpl(); mailSender.setHost("smtp.gmail.com");
 * mailSender.setPort(587); mailSender.setUsername("your-email@gmail.com"); //
 * Replace with your actual email mailSender.setPassword("your-app-password");
 * // Replace with your app password
 * 
 * Properties props = mailSender.getJavaMailProperties();
 * props.put("mail.smtp.auth", "true"); props.put("mail.smtp.starttls.enable",
 * "true"); props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
 * 
 * return mailSender; } }
 */