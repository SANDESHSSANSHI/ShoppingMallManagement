package com.shopping_management.demo.repository;

import com.shopping_management.demo.model.MallAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MallAdminRepository extends JpaRepository<MallAdmin, Integer> {
}
