package com.oguzarapkirli.employeemanagement.service;

import com.oguzarapkirli.employeemanagement.model.Employee;
import com.oguzarapkirli.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
         return employeeRepository.findAll();
    }

    public Employee getEmployeeById(String id) {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalStateException("There is no employee with this id in the database");
        }
        return employeeRepository.findById(id).get();
    }

    public Employee addEmployee(Employee employee) {
        if (employeeRepository.existsById(employee.getId().toString())) {
            throw new IllegalStateException("There is already an employee with this id in the database");
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        if (!employeeRepository.existsById(employee.getId().toString())) {
            throw new IllegalStateException("There is no employee with this id in the database");
        }
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(String id) {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalStateException("There is no employee with this id in the database");
        }
        employeeRepository.deleteById(id);
    }

}
