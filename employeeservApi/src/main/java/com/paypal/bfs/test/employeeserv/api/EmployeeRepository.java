package com.paypal.bfs.test.employeeserv.api;


import org.springframework.data.jpa.repository.JpaRepository;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
 
}