package com.neco.es.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neco.es.entity.Employee; 
import com.neco.es.repository.EmployeeRepository;
import com.neco.es.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	

	/*
	 * public List<Employee> listAll(String keyword){ if(keyword !=null) { return
	 * employeeRepository.search(keyword); } return employeeRepository.findAll();
	 * 
	 * }
	 */
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	
	@Override
	public List<Employee> getAllEmployees(){
	  return employeeRepository.findAll();
	}
	 
	@Override
	public List<Employee> searchEmployee(String firstName, String lastName, String empPosition) {
		if(firstName != null || lastName != null || empPosition != null) {
			return employeeRepository.searchEmployee( firstName, lastName, empPosition);
		}
		return employeeRepository.findAll();
	}
	
	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).get();
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployeeById(Long id) {
		employeeRepository.deleteById(id);
	}
	



	 
	
}
