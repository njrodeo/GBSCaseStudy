package com.neco.es.repository;

import java.sql.Date;
import java.util.List;
//import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neco.es.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	
	//Filter Employees
	@Query("SELECT e FROM Employee e WHERE " +
			"e.firstName LIKE CONCAT('%',:firstName,'%')" +
			"AND e.lastName LIKE CONCAT('%',:lastName,'%')"+
			"AND e.empPosition LIKE CONCAT('%',:empPosition,'%')")
	public List<Employee> searchEmployee(String firstName, String lastName, String empPosition);
	
	//Checks Duplicates
	@Query(value ="SELECT * FROM employees e WHERE e.first_name LIKE CONCAT ('', :first_name, '')"
			 + "AND e.middle_name LIKE CONCAT('', :middle_name, '')"
			 + "AND e.last_name LIKE CONCAT('', :last_name, '')"
			 + "AND e.birth_date LIKE CONCAT('', :birth_date, '')", nativeQuery=true)
	Long searchDuplicate(String first_name, String last_name, String middle_name, Date birth_date);
}
