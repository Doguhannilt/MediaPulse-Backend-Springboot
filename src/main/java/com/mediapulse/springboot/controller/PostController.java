package com.mediapulse.springboot.controller;

import com.mediapulse.springboot.models.Post;
import com.mediapulse.springboot.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService service;

    @PostMapping("/save")
    public ResponseEntity<Post> savePost(@RequestBody @Validated Post post) {
        try {
            return service.savePost(post);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return service.getAllPosts();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Post>> findPost(@PathVariable("id") String id){
        return service.findPost(id);
    }

    @DeleteMapping("/save/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") String id){
        return service.deletePost(id);
    }
}
