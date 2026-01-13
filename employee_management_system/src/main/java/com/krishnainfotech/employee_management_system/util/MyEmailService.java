package com.krishnainfotech.employee_management_system.util;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class MyEmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(String to) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(to);
		message.setSubject("Hello");
		message.setText("Your Profile has been created on our Employee Portal");
		
		javaMailSender.send(message);
		
	}
	
	public void sendEmailWithAttachment(String to) throws MessagingException, IOException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(to);
		helper.setSubject("Hello");
		helper.setText("Registered Successfully");

		FileSystemResource file = new FileSystemResource(new File("C:\\Users\\krish\\OneDrive\\Pictures\\Screenshots\\Screenshot 2024-09-29 182905.png"));
		helper.addAttachment("Screenshot 2024-09-29 182905.png", file);

		javaMailSender.send(message);
		}

	public void sendUpdateEmail(String to) throws MessagingException {
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setTo(to);
		helper.setSubject("Employee Details Updated");
		helper.setText("Ypur Details Updated Successfully");
		
		javaMailSender.send(message);

		
		
	}

	public void sendFoundEmail(String to) throws MessagingException {
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setTo(to);
		helper.setSubject("Employee Details Found");
		helper.setText("Ypur Details Found Successfully");
		
		javaMailSender.send(message);

	}
}
