package com.neco.es.repository;

import java.util.List;
import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.neco.es.entity.Compensation;

public interface CompensationRepository extends JpaRepository<Compensation, Long> {	
	
	@Query(value="SELECT comp_id, SUM(amount) AS amount, comp_description, comp_date, comp_type, employee_id "
			+ "FROM compensation "
			+ "WHERE employee_id = ?1 GROUP BY MONTH(comp_date) , YEAR(comp_date)", nativeQuery=true)
	public List<Compensation> getCompensationHistory(Long employee_id);
	
	@Query(value="SELECT * FROM compensation WHERE comp_date LIKE CONCAT(YEAR(?1), '-', MONTH(?1), '-%') ORDER BY comp_date DESC", nativeQuery=true)
	public List<Compensation> getCompensationBreakdown(Date compDate);
	
	@Query(value="SELECT comp_id FROM compensation "
			+ "WHERE comp_date LIKE CONCAT(YEAR(?1), '-', MONTH(?1), '-%') AND employee_id = ?2 AND comp_type = 'Salary'", nativeQuery=true)
	public Long checkEmployeeSalary(Date compDate, Long employeeId);

}