package com.shopping_management.demo.service;

import com.shopping_management.demo.model.MallAdmin;
import com.shopping_management.demo.repository.MallAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MallAdminService {

    @Autowired
    private MallAdminRepository mallAdminRepository;

    public List<MallAdmin> getMallAdmins() {
        return mallAdminRepository.findAll();
    }

    public MallAdmin getMallAdminById(Integer id) {
        return mallAdminRepository.findById(id).orElse(null);
    }

    public void createMallAdmin(MallAdmin mallAdmin) {
        mallAdminRepository.save(mallAdmin);
    }

    public void updateMallAdmin(Integer id, MallAdmin mallAdmin) {
        mallAdmin.setMallAdminId(id);
        mallAdminRepository.save(mallAdmin);
    }

    public void deleteMallAdmin(Integer id) {
        mallAdminRepository.deleteById(id);
    }
}
