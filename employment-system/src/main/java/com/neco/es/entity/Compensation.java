package com.neco.es.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="compensation")

public class Compensation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long compId;
	
	@Column(nullable=false)
	private String compType;
	
	@Column(nullable=false)
	private Float amount;
	
	@Column
	private String compDescription;
	
	@Column(nullable=false)
	private Date compDate;
	
	private Long employeeId;

	public Compensation() {}
	
	public Compensation(Long compId, String compType, Float amount, String compDescription, Date compDate, Long employeeId) {
		super();
		this.compId = compId;
		this.compType = compType;
		this.amount = amount;
		this.compDescription = compDescription;
		this.compDate = compDate;
		this.employeeId = employeeId;
	}


	public Long getCompId() {
		return compId;
	}

	public void setCompId(Long compId) {
		this.compId = compId;
	}

	public String getCompType() {
		return compType;
	}

	public void setCompType(String compType) {
		this.compType = compType;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getCompDescription() {
		return compDescription;
	}

	public void setCompDescription(String compDescription) {
		this.compDescription = compDescription;
	}

	public Date getCompDate() {
		return compDate;
	}

	public void setCompDate(Date compDate) {
		this.compDate = compDate;
	}
	
	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
}
