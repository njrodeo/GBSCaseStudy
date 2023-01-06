package com.neco.es.service.impl;

import java.sql.Date;
import java.util.List;
import org.springframework.stereotype.Service;

import com.neco.es.entity.Compensation;
import com.neco.es.repository.CompensationRepository;
import com.neco.es.service.CompensationService;

@Service
public class CompensationServiceImpl implements CompensationService{

	private CompensationRepository compensationRepository;
	
	public CompensationServiceImpl(CompensationRepository compensationRepository) {
		super();
		this.compensationRepository = compensationRepository;
	}

	@Override
	public List<Compensation> getCompensationHistory(Long employee_id) {
		return compensationRepository.getCompensationHistory(employee_id);
	}

	@Override
	public Compensation saveCompensation(Compensation compensation) {
		return compensationRepository.save(compensation);
	}

	@Override
	public Compensation getCompensationById(Long compId) {
		return compensationRepository.findById(compId).get();
	}

	@Override
	public List<Compensation> getCompensationBreakdown(Date compDate) {
		return compensationRepository.getCompensationBreakdown(compDate);
	}

	@Override
	public Long checkEmployeeSalary(Date compDate, Long employeeId) {
		return compensationRepository.checkEmployeeSalary(compDate, employeeId);
	}

}