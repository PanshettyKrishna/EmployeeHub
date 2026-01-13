package com.krishnainfotech.employee_management_system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailAlreadyExist extends RuntimeException {

	String s="Email Already Exists!";
	
	
	@Override
	public String getMessage() {
		return s;
	}


}
