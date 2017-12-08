package com.spring.demo.controller.customer;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.demo.dao.EmployeeRepository;
import com.spring.demo.model.Employee;



@Controller
public class EmployeeController {

	@Autowired
    private EmployeeRepository empRepository;
	 		
	@RequestMapping("/employees")
	public String index(Model model) {
		List<Employee> employeeList = (List<Employee>) empRepository.findAll();
		model.addAttribute("employees", employeeList);
    	return "/employee/employees";
    }
	
	  @RequestMapping(value = "/employeeAdd")
	    public String addEmployee(Model model){
	    	model.addAttribute("employee", new Employee());
	        return "/employee/addEmployee";
	    }	

	    @RequestMapping(value = "/employeeEdit/{id}")
	    public String editStudent(@PathVariable("id") Long empId, Model model){
	    	model.addAttribute("employee", empRepository.findOne(empId));
	        return "/employee/editEmployee";
	    }	    
	    
	    @RequestMapping(value = "employeeUpdate", method = RequestMethod.POST)
	    public String save(Employee emp){
	    	emp.setDate(new Date());
	    	empRepository.save(emp);
	    	return "redirect:/employees";
	    }
	    
	    @RequestMapping(value = "/employeeDelete/{id}", method = RequestMethod.GET)
	    public String deleteEmployee(@PathVariable("id") Long empId, Model model) {
	    	empRepository.delete(empId);
	        return "redirect:/employees";
	    }    
}
