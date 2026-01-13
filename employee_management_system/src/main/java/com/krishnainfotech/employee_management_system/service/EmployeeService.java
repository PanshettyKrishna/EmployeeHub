package com.krishnainfotech.employee_management_system.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.krishnainfotech.employee_management_system.dao.EmployeeDao;
import com.krishnainfotech.employee_management_system.dto.Employee;
import com.krishnainfotech.employee_management_system.exception.EmailAlreadyExist;
import com.krishnainfotech.employee_management_system.exception.EmailNotFound;
import com.krishnainfotech.employee_management_system.exception.EmployeeIdNotFound;
import com.krishnainfotech.employee_management_system.exception.MaximumUploadSizeExceed;
import com.krishnainfotech.employee_management_system.exception.PasswordNotFound;
import com.krishnainfotech.employee_management_system.util.MyEmailService;
import com.krishnainfotech.employee_management_system.util.ResponseStructure;
import com.krishnainfotech.employee_management_system.util.ResponseStructureList;

import jakarta.mail.MessagingException;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao dao;
	
	@Autowired
	private MyEmailService emailService;
	
	private ResponseStructure<Employee> structure=new ResponseStructure<Employee>();
	
	public ResponseEntity<ResponseStructure<Employee>> register(Employee employee) throws MessagingException, IOException {
		Employee employee1=dao.findByEmail(employee.getEmail());
		if(employee1==null)
		{
			structure.setMsg("Data Stored");
			structure.setData(dao.register(employee));
			structure.setStatusCode(HttpStatus.CREATED.value());
//			emailService.sendEmail(employee.getEmail());
			emailService.sendEmailWithAttachment(employee.getEmail());
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.CREATED);
		}
		else
		{
			throw new EmailAlreadyExist();
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(Employee employee) throws MessagingException {
		Employee employee1=dao.findEmployeeByID(employee.getEid());
		if(employee1!=null)
		{  
			employee.setImg(employee1.getImg());
			dao.updateEmployee(employee);
			structure.setMsg("Data Updated");
			structure.setData(employee);
			structure.setStatusCode(HttpStatus.OK.value());
//			emailService.sendUpdateEmail(employee1.getEmail());
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new EmployeeIdNotFound("Employee Id not found");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> findEmployeeById(int eid) throws MessagingException {
		Employee employee=dao.findEmployeeByID(eid);
		if(employee!=null)
		{
			structure.setMsg("Data Found");
			structure.setData(employee);
			structure.setStatusCode(HttpStatus.FOUND.value());
			emailService.sendFoundEmail(employee.getEmail());
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.FOUND);
		}
		else
		{
			throw new EmployeeIdNotFound("Employee Id not found");
		}
	}

	
	public ResponseEntity<ResponseStructureList<Employee>> findAllEmployees() {
		ResponseStructureList<Employee> structure1=new ResponseStructureList<Employee>();
		List<Employee> employeeList=dao.findAllEmployees();
		if(employeeList!=null)
		{
		   structure1.setMsg("Data found"); 
		   structure1.setData(employeeList);
		   structure1.setStatusCode(HttpStatus.FOUND.value());
		   return new ResponseEntity<ResponseStructureList<Employee>>(structure1,HttpStatus.FOUND);
		}
		else
		{
			throw new EmployeeIdNotFound("Employees are not found");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateImg(int eid,MultipartFile file) throws IOException {
		
		Employee employee=dao.findEmployeeByID(eid);
			if(employee.getImg()!=null || employee.getImg()==null)
			{
				employee.setEid(eid);
				employee.setImg(file.getBytes());
				structure.setData(dao.updateEmployee(employee));
				structure.setMsg("Image Uploaded");
				structure.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
			}
			else
			{
				throw new EmployeeIdNotFound("Image not found.....Employee Id not found");
			}
		}
		
	public byte[] getImage(int eid) {
		Employee employee=dao.findEmployeeByID(eid);
		if(employee!=null)
		{
			if(employee.getImg()!=null)
			{
				return employee.getImg();
			}
			else
			{
				throw new EmployeeIdNotFound("Image not found");
			}
		}
		else
		{
		    throw new EmployeeIdNotFound("Employee Id not found");
		}
		
	}
	
	public ResponseEntity<ResponseStructure<Employee>> changeImage(int eid,MultipartFile file) throws IOException {
		Employee employee=dao.findEmployeeByID(eid);
		if(employee!=null)
		{
			long maxSize=5*1024*1024;
			if(file.getSize()>maxSize)
			{
				throw new MaximumUploadSizeExceed("File Size exceeds limit!");
			}
		if(employee.getImg()!=null)
		{
			employee.setEid(eid);
			employee.setImg(file.getBytes());
			structure.setData(dao.updateEmployee(employee));
			structure.setMsg("Image Changed");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new EmployeeIdNotFound("Image not found.....Employee Id no+t found");
		}

	   }
		else
		{
			throw new EmployeeIdNotFound("Employee Id not found");
		}
	
   }

	public ResponseEntity<ResponseStructure<Employee>> login(String email, String password) {
		Employee employee=dao.login(email);
		if(employee!=null)
		{
			if(employee.getPassword().equals(password))
            {
            	structure.setData(employee);
            	structure.setMsg("Employee Details are correct");
            	structure.setStatusCode(HttpStatus.OK.value());
            	return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
            }
			else
			{
				throw new PasswordNotFound("Give Correct Password");
			}
		}
		else
		{
			throw new EmailNotFound("Email Id not found");
		}
		
	}

	public ResponseEntity<byte[]> fetchImage(int eid, MultipartFile file) {
		
		Employee employee=dao.findEmployeeByID(eid);
		byte[] dbImg=employee.getImg();
		if(dbImg!=null)
		{
			HttpHeaders headers=new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			return new ResponseEntity<byte[]>(dbImg,headers,HttpStatus.OK);
		}
		else
		{
			throw new EmployeeIdNotFound("Image not found.....Employee Id not found");
		}
	}

	public void deleteEmployee(int eid) {
		Employee employee=dao.findEmployeeByID(eid);
        if(employee!=null)
        {	
            dao.deleteEmployee(eid);
        }
        else
        {
        	throw new EmployeeIdNotFound("Employee not found");
        }
		
	}	

}
