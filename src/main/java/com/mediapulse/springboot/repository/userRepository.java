package com.mediapulse.springboot.repository;

import com.mediapulse.springboot.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userRepository extends MongoRepository<User, String> {


        User findByEmail(String email);
}
