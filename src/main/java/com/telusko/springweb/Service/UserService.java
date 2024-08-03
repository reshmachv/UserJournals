package com.telusko.springweb.Service;

import com.telusko.springweb.Entity.User;
import com.telusko.springweb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public void saveUsers(User user){
        userRepo.save(user);
    }

    public List<User> getAllUsers(){

        return userRepo.findAll();
    }

    public Optional<User> findByUserName(String name) {
        return userRepo.findByname(name);
    }
}
