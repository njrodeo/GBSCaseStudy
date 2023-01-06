package com.neco.es.service;

import java.sql.Date;
import java.util.List;

import com.neco.es.entity.Compensation;

public interface CompensationService {
	//Fetch all Compensation Data of an Employee
		List<Compensation> getCompensationHistory(Long employees_id);
		
		//Fetch sum of all compensations per month each year
		//List<Compensation> getCompensationPerMonth();
		
		//Insert New Compensation Data in Database
		Compensation saveCompensation(Compensation compensation);
		
		//Fetch Compensation by ID
		Compensation getCompensationById(Long compId);
		
		//Fetch Compensations breakdown
		List<Compensation> getCompensationBreakdown(Date compDate);
		
		//Search if Salary already exists
		Long checkEmployeeSalary(Date compDate, Long employeeId);
		
}
