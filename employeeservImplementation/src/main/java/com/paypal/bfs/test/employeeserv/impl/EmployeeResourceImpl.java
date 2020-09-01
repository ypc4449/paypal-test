package com.paypal.bfs.test.employeeserv.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.employeeserv.api.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

	
	@Autowired
    private EmployeeRepository employeeRepository;
 
    @Override
    public Employee createEmployee(Employee emp) {
        return employeeRepository.save(emp);
    }
    
/*	@Override
    public Employee employeeGetById(String empId) {
    	  Optional<Employee> optEmp = employeeRepository.findById(empId);
    	  return optEmp.get();
    	 }*/

	@Override
    public Employee getEmployee(Long empId) {
        Optional<Employee> optionalEmp = employeeRepository.findById(empId);
        if (optionalEmp.isPresent()) {
            return optionalEmp.get();
        }
        return null;
    }
	
    
    /*@Override
    public ResponseEntity<Employee> employeeGetById(String id) {

        Employee employee = new Employee();
        employee.setId(Integer.valueOf(id));
        employee.setFirstName("BFS");
        employee.setLastName("Developer");

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }*/
    
   
}
