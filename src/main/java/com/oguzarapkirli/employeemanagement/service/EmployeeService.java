package com.oguzarapkirli.employeemanagement.service;

import com.oguzarapkirli.employeemanagement.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(String id);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(String id);

}
