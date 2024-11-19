package com.shopping_management.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.shopping_management.demo.dto.EmployeeDTO;
import com.shopping_management.demo.model.Employee;
import com.shopping_management.demo.model.Mall;
import com.shopping_management.demo.model.Shop;
import com.shopping_management.demo.service.EmployeeService;
import com.shopping_management.demo.service.MallService;
import com.shopping_management.demo.service.ShopService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private MallService mallService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();

            List<EmployeeDTO> employeeDTOs = employees.stream().map(employee -> {
                Shop shop = shopService.getShopById(employee.getShopId());
                Mall mall = mallService.getMallById(shop.getMallId());

                return new EmployeeDTO(
                    employee.getEmployeeId(),
                    employee.getEmployeeName(),
                    employee.getEmployeeDateOfBirth(),
                    employee.getEmployeeSalary(),
                    employee.getEmployeeAddress(),
                    employee.getEmployeeDesignation(),
                    employee.getShopId(),
                    shop.getShopName(),
                    shop.getMallId(),
                    mall.getMallName()
                );
            }).collect(Collectors.toList());

            return ResponseEntity.ok(employeeDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            if (employee == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            Shop shop = shopService.getShopById(employee.getShopId());
            if (shop == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            Mall mall = mallService.getMallById(shop.getMallId());
            if (mall == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getEmployeeDateOfBirth(),
                employee.getEmployeeSalary(),
                employee.getEmployeeAddress(),
                employee.getEmployeeDesignation(),
                employee.getShopId(),
                shop.getShopName(),
                shop.getMallId(),
                mall.getMallName()
            );

            return ResponseEntity.ok(employeeDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        try {
            employeeService.createEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body("Employee created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating employee: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        try {
            if (employeeService.getEmployeeById(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
            }
            employeeService.updateEmployee(id, employee);
            return ResponseEntity.ok("Employee updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating employee: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        try {
            if (employeeService.getEmployeeById(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
            }
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Employee deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting employee: " + e.getMessage());
        }
    }
}
