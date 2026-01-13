package com.krishnainfotech.employee_management_system.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.krishnainfotech.employee_management_system.dto.Employee;
import com.krishnainfotech.employee_management_system.service.EmployeeService;
import com.krishnainfotech.employee_management_system.util.ResponseStructure;
import com.krishnainfotech.employee_management_system.util.ResponseStructureList;

import jakarta.mail.MessagingException;

@CrossOrigin(origins="*",methods= {RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
//	@CrossOrigin(origins="http://127.0.0.1:5500")
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<Employee>> register(@RequestBody Employee employee) throws MessagingException, IOException {
		return service.register(employee);
	}
	
	@GetMapping("/find/{eid}")
	public ResponseEntity<ResponseStructure<Employee>> findEmployeeById(@PathVariable int eid) throws MessagingException, IOException {
		return service.findEmployeeById(eid);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestBody Employee employee) throws MessagingException {
		return service.updateEmployee(employee);
	}

	@GetMapping("/findall")
	public ResponseEntity<ResponseStructureList<Employee>> findAllEmployees() throws MessagingException, IOException {
		return service.findAllEmployees();
	}

	@PostMapping("/updateimage/{eid}")
	public ResponseEntity<ResponseStructure<Employee>> updateImg(@PathVariable int eid,@RequestBody MultipartFile file) throws IOException {
		return service.updateImg(eid, file);
	}
	
	@GetMapping("/getimage/{eid}")
	public byte[] getImage(@PathVariable int eid) {
		return service.getImage(eid);
	}
	
	@PutMapping("/changeimage/{eid}")
	public ResponseEntity<ResponseStructure<Employee>> changeImg(@PathVariable int eid,@RequestBody MultipartFile file) throws IOException {
		return service.changeImage(eid, file);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<Employee>> login(@RequestBody Employee employee) {
		String email=employee.getEmail();
		String password=employee.getPassword();
		return service.login(email,password);
	}

	@GetMapping("/fetchimage/{eid}")
	public ResponseEntity<byte[]> fetchImg(@PathVariable int eid,@RequestBody MultipartFile file) throws IOException {
		return service.fetchImage(eid, file);
	}
	
	@DeleteMapping("/delete/{eid}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int eid) {
       	service.deleteEmployee(eid);
        return ResponseEntity.ok("Empoyee Deleted Successfully");
	}
	
	
}
