package com.shopping_management.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping_management.demo.model.Mall;
import com.shopping_management.demo.model.Shop;
import com.shopping_management.demo.repository.MallRepository;
import com.shopping_management.demo.repository.ShopRepository;

@Service
@Transactional
public class MallService {

    @Autowired
    private MallRepository mallRepository;
    @Autowired
    private ShopRepository shopRepository;

    // Get all malls
    public List<Mall> getAllMalls() {
        return mallRepository.findAll();
    }

    // Get a mall by ID
    public Mall getMallById(Integer id) {
        return mallRepository.findById(id).orElse(null);
    }

    // Create a new mall
    public void createMall(Mall mall) {
        mallRepository.save(mall);
    }

    // Update an existing mall
    public void updateMall(Integer id, Mall mall) {
        mall.setMallId(id); 
        mallRepository.save(mall);
    }

    // Delete a mall by ID
    public void deleteMall(Integer id) {
        mallRepository.deleteById(id);
    }
    public Integer getNumberOfShopsByMallId(Integer mallId) {
        return shopRepository.countByMallId(mallId); 
    }

    



    public List<Shop> getShopsByMallId(Integer mallId) {
        // Fetch the shops for the mall from the repository
        return shopRepository.findByMallId(mallId);
    }
}
