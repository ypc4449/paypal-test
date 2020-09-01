package com.paypal.bfs.test.employeeserv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeResource employeeResource;

	/*
	 * @RequestMapping("/v1/bfs/employees/{id}") ResponseEntity<Employee>
	 * employeeGetById(@PathVariable("id") String id);
	 */

	@PostMapping(path = "/v1/bfs/employees", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp) {

		if (emp == null) {
			return ResponseEntity.noContent().build();
		} else if (emp.getId() != null && employeeResource.getEmployee(emp.getId()) != null) {

			return ResponseEntity.noContent().build();
		}

		else
			return new ResponseEntity<>(employeeResource.createEmployee(emp), HttpStatus.CREATED);
	}

	@GetMapping(path = "/v1/bfs/employees/{empId}", produces = "application/json")
	public ResponseEntity<Employee> employeeGetById(@PathVariable(value = "empId") Long empId) {
		return new ResponseEntity<>(employeeResource.getEmployee(empId), HttpStatus.OK);
	}

	/*
	 * @GetMapping(path = "/employees", produces = "application/json") public
	 * ResponseEntity<List<Employee>> getAllEmployees() { return new
	 * ResponseEntity<>(employeeResource.getAllEmployee(), HttpStatus.OK); }
	 */

}