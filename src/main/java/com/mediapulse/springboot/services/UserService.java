package com.mediapulse.springboot.services;

import com.mediapulse.springboot.models.User;
import com.mediapulse.springboot.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private userRepository repo;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public ResponseEntity<User> saveUser(User user) {
        try {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            User savedUser = repo.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<String> login(User user) {
        try {
            User foundUser = repo.findByEmail(user.getEmail());

            if (foundUser != null && passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
                return new ResponseEntity<>("Login successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid email or password", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<User>> getUser() {
        try {
            List<User> getUser = repo.findAll();
            System.out.println(getUser);
            return new ResponseEntity<>(getUser,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteUser(String id) {
        try {
            if (repo.existsById(id)) {
                repo.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    public ResponseEntity<User> findUser(String id) {
        try {
            if (repo.existsById(id)) {
                repo.findById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }
}


