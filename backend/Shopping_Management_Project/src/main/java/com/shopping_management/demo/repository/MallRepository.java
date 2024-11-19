package com.shopping_management.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping_management.demo.model.Mall;
import com.shopping_management.demo.model.Shop;

@Repository
public interface MallRepository extends JpaRepository<Mall, Integer> {
}