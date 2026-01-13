package com.krishnainfotech.employee_management_system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordNotFound extends RuntimeException {

String s="Password not found";
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return s;
	}

}
