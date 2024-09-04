package com.mediapulse.springboot.controller;

import com.mediapulse.springboot.models.User;
import com.mediapulse.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user){
        return service.login(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUser() {
        return service.getUser();
    }

    @DeleteMapping("/find/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        return service.deleteUser(id);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> findUser(@PathVariable("id") String id) {
        return service.findUser(id);
    }

}
