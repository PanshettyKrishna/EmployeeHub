package com.krishnainfotech.employee_management_system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeIdNotFound extends RuntimeException{	
	
String s="Id not found";
	
		
	@Override
	public String getMessage() {
		return s;
	}

}
