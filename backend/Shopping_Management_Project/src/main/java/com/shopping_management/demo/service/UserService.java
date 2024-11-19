package com.shopping_management.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping_management.demo.model.User;
import com.shopping_management.demo.repository.UserRepository;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUserById(Integer id) {
        return repository.findById(id).orElse(null);
    }
    
 // UserService
    public User getUserByname(String name) {
        System.out.println("Searching for user by name in service: " + name);
        User user = repository.findByName(name);
        if (user == null) {
            System.out.println("User not found with name: " + name);
        } else {
            System.out.println("Found user: " + user);
        }
        return user;
    }



    public void createUser(User user) {
        repository.save(user);
    }

    public void updateUser(Integer id, User user) {
        user.setId(id); 
        repository.save(user);
    }

    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }
    
    
}
