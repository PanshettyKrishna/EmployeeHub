package com.krishnainfotech.employee_management_system.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.krishnainfotech.employee_management_system.exception.EmailAlreadyExist;
import com.krishnainfotech.employee_management_system.exception.EmailNotFound;
import com.krishnainfotech.employee_management_system.exception.EmployeeIdNotFound;
import com.krishnainfotech.employee_management_system.exception.MaximumUploadSizeExceed;
import com.krishnainfotech.employee_management_system.exception.PasswordNotFound;
import com.krishnainfotech.employee_management_system.util.ResponseStructure;

@RestControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler(EmployeeIdNotFound.class)
	public ResponseEntity<ResponseStructure<String>> idNotFound(EmployeeIdNotFound employeeIdNotFound) {
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMsg("Data not found");
		structure.setData(employeeIdNotFound.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
	}

	@ExceptionHandler(MaximumUploadSizeExceed.class)
	public ResponseEntity<ResponseStructure<String>> maximumUploadSizeExceed(MaximumUploadSizeExceed maximumUploadSizeExceed) {
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMsg("Maximum upload FileSize Exceeded");
		structure.setData(maximumUploadSizeExceed.getMessage());
		structure.setStatusCode(HttpStatus.PAYLOAD_TOO_LARGE.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.PAYLOAD_TOO_LARGE);
		
	}
	
	@ExceptionHandler(EmailNotFound.class)
	public ResponseEntity<ResponseStructure<String>> emailNotFound(EmailNotFound emailNotFound) {
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMsg("Email not found");
		structure.setData(emailNotFound.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(PasswordNotFound.class)
	public ResponseEntity<ResponseStructure<String>> passwordNotFound(PasswordNotFound passwordNotFound) {
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMsg("Give correct Password");
		structure.setData(passwordNotFound.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(EmailAlreadyExist.class)
	public ResponseEntity<ResponseStructure<String>> emailAlreadyExist(EmailAlreadyExist emailAlreadyExist) {
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMsg("Email Already Exists!");
		structure.setData(emailAlreadyExist.getMessage());
		structure.setStatusCode(HttpStatus.ALREADY_REPORTED.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.ALREADY_REPORTED);
		
	}



}
