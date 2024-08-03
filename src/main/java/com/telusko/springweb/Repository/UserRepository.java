package com.telusko.springweb.Repository;

import com.telusko.springweb.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
   Optional<User> findByname(String name);
}
