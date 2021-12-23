package com.example.demo_crud.service;

import com.example.demo_crud.entity.User;
import com.example.demo_crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public User getUserById(int id){
        return userRepository.getById(id);
    }
    public void saveUser(User user){
        userRepository.save(user);
    }
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
}
