package com.krishnainfotech.employee_management_system.util;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ResponseStructure<T> {

	private String msg;
	private int statusCode;
	private T data;
	private LocalDateTime dateTime=LocalDateTime.now();
			
	
}
