package com.neco.es.controller;

import java.util.Arrays;
import java.util.List;
import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.neco.es.entity.Compensation;
import com.neco.es.service.CompensationService;
import com.neco.es.service.EmployeeService;

@Controller
public class CompensationController {
	
	private CompensationService compensationService;
	private EmployeeService employeeService;
	
	public CompensationController(CompensationService compensationService, EmployeeService employeeService) {
		super();
		this.compensationService = compensationService;
		this.employeeService = employeeService;
	}
	
	//view compensation history
	@GetMapping("/employees/compensation/{id}")
	public String listcompensations(@PathVariable Long id, Model model) {
		
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		model.addAttribute("compensations", compensationService.getCompensationHistory(id));

		return "compensationHistory";
		
	}
	
	//Search compensation
	@PostMapping("/employees/compensation/search")
	public String searchCompensation(Model model, Date startDate, Date endDate) {
		//Not yet done
		return null;
	}
	
	
	//Add Compensation
	@GetMapping("/employees/compensation/new")
	public String createEmployeeCompensationForm(Model model) {
		Compensation compensationEntity = new Compensation();
		
		model.addAttribute("employeeList", employeeService.getAllEmployees());
		model.addAttribute("compensation", compensationEntity);
		
		List<String> typeList = Arrays.asList("Salary", "Bonus", "Commission", "Allowance", "Adjustment");
		model.addAttribute("typeList", typeList);
		
		return "create_compensation";
	}
	
	//Save Compensation
	@PostMapping("/employees/compensation/save")
	public String saveEmployeeCompensation(@ModelAttribute("compensation") Compensation compensationEntity, RedirectAttributes redirAttrs) {
		Long check = checkEmployeeSalary(compensationEntity.getCompDate(), compensationEntity.getEmployeeId());
		if(check > 0) {
			redirAttrs.addFlashAttribute("duplicate", "Employee salary already exists for this month...");
			return "redirect:/employees/compensation/new";
		}else {
			compensationService.saveCompensation(compensationEntity);
			
			redirAttrs.addFlashAttribute("added", "New compensation has been added successfully...");
			
			return "redirect:/employees";
		}
	}
	
	//Compensation Breakdown
	@GetMapping("/employees/compensation/breakdown/{employeeId}/{compDate}")
	public String getCompensationBreakdown(@PathVariable("employeeId") Long employeeId, @PathVariable("compDate") Date compDate, Model model) {
		model.addAttribute("date", compDate);
		model.addAttribute("employee", employeeId);
		model.addAttribute("compensations", compensationService.getCompensationBreakdown(compDate));
		return "compensation";
	}
	
	//Edit compensation
	@GetMapping("/employees/compensation/edit/{compId}")
	public String editCompensationForm( @PathVariable("compId") Long compId, Model model) {
		model.addAttribute("compensation", compensationService.getCompensationById(compId));
		return "edit_compensation";
	}
	
	
	
	
	public Long checkEmployeeSalary(Date compDate, Long employeeId) {
		Long salary = compensationService.checkEmployeeSalary(compDate, employeeId);
		if(salary == null) {
			salary = (long) 0;
		}
		return salary;
	}
}