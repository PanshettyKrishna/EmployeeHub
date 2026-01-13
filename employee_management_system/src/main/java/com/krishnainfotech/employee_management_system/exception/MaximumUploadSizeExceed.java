package com.krishnainfotech.employee_management_system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaximumUploadSizeExceed extends RuntimeException {

	String s="File size exceeds limit";
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return s;
	}
}
