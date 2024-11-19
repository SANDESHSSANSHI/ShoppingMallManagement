package com.shopping_management.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping_management.demo.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Integer countByMallId(Integer mallId);

    List<Shop> findByMallId(Integer mallId);
}
