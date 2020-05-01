package com.mukundmadhav.springboot.springboot.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mukundmadhav.springboot.springboot.modal.Employee;
import com.mukundmadhav.springboot.springboot.modal.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@GetMapping("/employees")
	public Iterable<Employee> get() {
		Iterable<Employee> employees = employeeRepository.findAll();
		
		employees.forEach(e -> System.out.println(e.getName()));
		
		
		return employees;
	}
	
	@PostMapping("/employees")
	public Employee save(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/employees/{id}")
	public Employee get(@PathVariable Long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		return optionalEmployee.orElseThrow(EntityNotFoundException::new);
	}
	
	@DeleteMapping("/employees/{id}")
	public String delete(@PathVariable Long id) {
		
		employeeRepository.deleteById(id);
		
		return "Employee removed with id "+id;
		
	}
	
	@PutMapping("/employees")
	public Employee update(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

} 
