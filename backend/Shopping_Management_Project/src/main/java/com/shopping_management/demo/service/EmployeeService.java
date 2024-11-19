package com.shopping_management.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping_management.demo.model.Employee;
import com.shopping_management.demo.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get an employee by ID
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // Create a new employee
    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    // Update an existing employee
    public void updateEmployee(Integer id, Employee employee) {
        employee.setEmployeeId(id); 
        employeeRepository.save(employee);
    }

    // Delete an employee by ID
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
    
    public Integer countEmployeesByShopId(Integer shopId) {
        return employeeRepository.countByShopId(shopId);
    }
    
    public List<Employee> getEmployeesByShopId(Integer shopId) {
        return employeeRepository.findByShopId(shopId);
    }
    
    
}
