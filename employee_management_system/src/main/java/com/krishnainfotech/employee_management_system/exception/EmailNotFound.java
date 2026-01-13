package com.krishnainfotech.employee_management_system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailNotFound  extends RuntimeException{
  
	String s="Email Id not found";
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return s;
	}
}
