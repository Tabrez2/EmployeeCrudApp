package com.tab.EmployeeCrudApp.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tab.EmployeeCrudApp.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
