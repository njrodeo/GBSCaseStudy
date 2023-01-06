package com.neco.es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import com.neco.es.entity.Employee;
import com.neco.es.repository.EmployeeRepository;

@SpringBootApplication
public class EmploymentSystemApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EmploymentSystemApplication.class, args);
	}
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public void run (String... args) throws Exception{
		
		/*
		 * Employee employee1 = new Employee("Neco", "Juan", "Juan", null ,"Staff");
		 * employeeRepository.save(employee1);
		 * 
		 */
		
	}
}
