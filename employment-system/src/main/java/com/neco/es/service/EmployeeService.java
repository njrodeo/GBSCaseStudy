package com.neco.es.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.neco.es.entity.Employee;

@Service
public interface EmployeeService {
	
	
	Employee saveEmployee(Employee employee);
	
	Employee getEmployeeById(Long id);
	Employee updateEmployee(Employee employee);

	void deleteEmployeeById(Long id);

	List<Employee> getAllEmployees();

	//List<Employee> listAll(String keyword);
	
	List<Employee> searchEmployee(String firstName, String lastName, String empPosition);



	
	


}
