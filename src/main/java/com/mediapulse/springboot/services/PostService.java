package com.mediapulse.springboot.services;

import com.mediapulse.springboot.models.Post;
import com.mediapulse.springboot.models.Reply;
import com.mediapulse.springboot.models.User;
import com.mediapulse.springboot.repository.postRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mediapulse.springboot.repository.userRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PostService {

    @Autowired
    private postRepository repo;
    @Autowired
    private userRepository userRepository;


    public ResponseEntity<Post> savePost(Post post) {
        try {

            User postedByUser = userRepository.findById(post.getPostedBy().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            post.setPostedBy(postedByUser);


            List<User> likedUsers = post.getLikes().stream()
                    .map(user -> userRepository.findById(user.getId())
                            .orElseThrow(() -> new RuntimeException("User not found")))
                    .collect(Collectors.toList());
            post.setLikes(likedUsers);


            for (Reply reply : post.getReplies()) {
                User replyUser = userRepository.findById(reply.getUserId().getId())
                        .orElseThrow(() -> new RuntimeException("User not found"));
                reply.setUserId(replyUser);
            }

            Post savedPost = repo.save(post);
            return new ResponseEntity<>(savedPost, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deletePost(String id) {
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public  ResponseEntity<List<Post>> getAllPosts() {
        List<Post> all = repo.findAll();
        return new ResponseEntity<>(all, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Optional<Post>> findPost(String id){
        Optional<Post> find = repo.findById(id);
        return new ResponseEntity<>(find, HttpStatus.OK);
    }

    // likeAndUnlikePosts
    // replyPost
}
