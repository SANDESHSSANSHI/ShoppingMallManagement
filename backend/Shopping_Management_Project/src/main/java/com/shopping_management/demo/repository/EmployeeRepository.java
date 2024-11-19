package com.shopping_management.demo.repository;

import com.shopping_management.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Method to count employees by shop ID
    Integer countByShopId(Integer shopId);

    // Method to find all employees by shop ID
    List<Employee> findByShopId(Integer shopId);
}
