package com.neco.es.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.neco.es.entity.Employee;
import com.neco.es.repository.EmployeeRepository;
import com.neco.es.service.EmployeeService;

@Service
@Controller
public class EmployeeController {
	
	@Autowired

	private EmployeeService employeeService;
	private EmployeeRepository employeeRepository;

	public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
		super();
		this.employeeService = employeeService;
		this.employeeRepository = employeeRepository;

	}
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String viewHomePage() {
		return "index";
	}
	
	@RequestMapping("/employees")
	public String viewEmployeeList(Model model) {
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "employees";
	}
	
	@PostMapping("/employees/search")
	public String searchEmployee(Model model, @ModelAttribute("filter") Employee filter) {
		model.addAttribute("employees", employeeService.searchEmployee(filter.getFirstName(), filter.getLastName(), filter.getEmpPosition()));
		model.addAttribute("firstName", filter.getFirstName());
		model.addAttribute("lastName", filter.getLastName());
		model.addAttribute("empPosition", filter.getEmpPosition());
		return "employees";
	}

	public Long duplicateEmployee(String firstName, String lastName, String middleName, Date birthDate) {
		 Long duplicate = employeeRepository.searchDuplicate(firstName, lastName, middleName, birthDate);
		 if(duplicate == null) {
		 duplicate = (long) 0;
		 }
		 return duplicate;
	}
	
	@GetMapping("/employees/new")
	
	// create employee object to hold employee form data
	public String createEmployeeForm(Model model) {
		
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "create_employee";
		
	}
	
	@PostMapping("/employees")
	public String saveEmployee(@ModelAttribute("employee") Employee employee, RedirectAttributes redirAttrs) {
		 //Next lines and first condition checks if there is a duplicate entry within the database.
		 Long check = duplicateEmployee(employee.getFirstName(), employee.getLastName(), employee.getMiddleName(), employee.getBirthDate());
		 if (check > 0) {
		 redirAttrs.addFlashAttribute("duplicate", " Employee already exists!");
		 return "redirect:/employees/new";
		 }else {
		 employeeService.saveEmployee(employee);
		 redirAttrs.addFlashAttribute("added", " Employee added successfully!");
		 return "redirect:/employees";
		 }
	}
	
	
	
	@GetMapping("/employees/edit/{id}")
	public String editEmployeeForm(@PathVariable Long id, Model model) {
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		return "edit_employee";
	}
	
	@PostMapping("/employees/{id}")
	 public String updateEmployee(@PathVariable Long id,
	 @ModelAttribute ("employee") Employee employee,
	 Model model, RedirectAttributes redirAttrs) {
	 Long check = duplicateEmployee(employee.getFirstName(), employee.getLastName(), employee.getMiddleName(), employee.getBirthDate());
	 if (check == employee.getId() || check == 0) {
	 //Get employee from database by ID
	 Employee existingEmployee = employeeService.getEmployeeById(id);
	 existingEmployee.setFirstName(employee.getFirstName());
	 existingEmployee.setMiddleName(employee.getMiddleName());
	 existingEmployee.setLastName(employee.getLastName());
	 existingEmployee.setBirthDate(employee.getBirthDate());
	 existingEmployee.setEmpPosition(employee.getEmpPosition());
	 //save updated employee object
	 employeeService.updateEmployee(existingEmployee);
	 redirAttrs.addFlashAttribute("updated", " Employee updated successfully!");
	 return "redirect:/employees";
	 } else {
	 redirAttrs.addFlashAttribute("duplicate", " Employee already exists!");
	 return "redirect:/employees/edit/{id}";
	 }
	}
	
	// handler method to handle delete employee request
	@GetMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/employees";
	}
	
	
	
	
	
	
}
