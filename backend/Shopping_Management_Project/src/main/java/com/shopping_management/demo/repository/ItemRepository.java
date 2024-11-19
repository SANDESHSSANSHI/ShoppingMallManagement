
package com.shopping_management.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping_management.demo.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByShopId(Integer shopId);
    
    Integer countByShopId(Integer shopId);

}
