package com.krishnainfotech.employee_management_system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.krishnainfotech.employee_management_system.dto.Employee;
import com.krishnainfotech.employee_management_system.exception.EmployeeIdNotFound;
import com.krishnainfotech.employee_management_system.repository.EmployeeRepository;
import com.krishnainfotech.employee_management_system.util.ResponseStructure;

@Repository
public class EmployeeDao {

	@Autowired
	private EmployeeRepository repository;
	
	public Employee register(Employee employee) {
		return repository.save(employee);
	}

	public Employee updateEmployee(Employee employee) {
		Optional<Employee> databaseEmployee=repository.findById(employee.getEid());
		if(databaseEmployee.isPresent())
		{
			return repository.save(employee);
		}
		else
		{
			return null;
		}
	}

	public Employee findEmployeeByID(int eid) {
		Optional<Employee> databaseEmployee=repository.findById(eid);
		if(databaseEmployee.isPresent())
		{
			return databaseEmployee.get();
		}
		else
		{
			return null;
		}
	}

	
	public List<Employee> findAllEmployees() {

		return repository.findAll();
	}


    public Employee login(String email) {
    	return repository.findByEmail(email);
    }
    
    public Employee findByEmail(String email) {
    	return repository.findByEmail(email);
		
	}

	public void deleteEmployee(int eid) {
		Optional<Employee> databaseEmp=repository.findById(eid);
		if(databaseEmp.isPresent())
		{
			repository.deleteById(eid);
		}
		else
		{
			throw new EmployeeIdNotFound("Employee not found");
		}
	}

}