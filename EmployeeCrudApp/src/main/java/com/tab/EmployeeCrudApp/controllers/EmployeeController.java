package com.tab.EmployeeCrudApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tab.EmployeeCrudApp.models.Employee;
import com.tab.EmployeeCrudApp.models.EmployeeDto;
import com.tab.EmployeeCrudApp.services.EmployeeRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    
    @Autowired
    private EmployeeRepository repo;
    
    @GetMapping({"", "/"})
    public String showEmployeeList(Model model) {
        List<Employee> employees = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("employees", employees);
        return "employees/index";
    }

    @GetMapping("/add")
    public String showCreatePage(Model model) {
        model.addAttribute("employeeDto", new EmployeeDto());
        return "employees/AddEmployee";
    }
    
    @PostMapping("/add")
    public String addEmployee(
            @Valid @ModelAttribute EmployeeDto employeeDto,
            BindingResult result) {
        if (result.hasErrors()) {
            return "employees/AddEmployee";
        }
        
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setAddress(employeeDto.getAddress());
        employee.setPhone(employeeDto.getPhone());
        
        repo.save(employee);
        return "redirect:/employees";
    }
    
    @GetMapping("/edit")
    public String showEditPage(Model model, 
    		@RequestParam int id) {
        try {
            Employee employee = repo.findById(id).get();
            model.addAttribute("employee", employee);
            
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setName(employee.getName());
            employeeDto.setEmail(employee.getEmail());
            employeeDto.setAddress(employee.getAddress());
            employeeDto.setPhone(employee.getPhone());
            
            model.addAttribute("employeeDto", employeeDto);
        }
          	catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/employees";
        }
        
        return "employees/EditEmployee";
    }
    
    @PostMapping("/edit")
    public String updateEmployee(
    		Model model,
    		@RequestParam int id,
            @Valid @ModelAttribute EmployeeDto employeeDto,
            BindingResult result) {
                
        try {
            Employee employee = repo.findById(id).get();
            model.addAttribute("employee", employee);
            
            if(result.hasErrors())
            	return "employees/EditEmployee";
            
            employee.setName(employeeDto.getName());
            employee.setEmail(employeeDto.getEmail());
            employee.setAddress(employeeDto.getAddress());
            employee.setPhone(employeeDto.getPhone());
            
            repo.save(employee);
            
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            // Consider adding an error handling mechanism here
        }
        
        return "redirect:/employees";
    }
    
    @GetMapping("/delete")
    public String deleteEmployee(
    		@RequestParam int id
    		) {
    	
    		try {
    			
    			Employee employee = repo.findById(id).get();
				
        		repo.delete(employee);

    			
			} catch (Exception ex) {

				System.out.println("Exception: "+ex.getMessage());
			}
    	
		return "redirect:/employees";
    	
    }
    
    
}




















