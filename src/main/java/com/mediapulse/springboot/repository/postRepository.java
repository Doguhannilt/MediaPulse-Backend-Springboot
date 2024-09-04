package com.mediapulse.springboot.repository;
import com.mediapulse.springboot.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface postRepository extends MongoRepository<Post, String> {

}
