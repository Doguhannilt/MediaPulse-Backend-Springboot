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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PostService {

    @Autowired
    private postRepository repo;
    @Autowired
    private userRepository userRepository;
    @Autowired
    private Reply newReply;

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

    public ResponseEntity<String> likePost(String postId, String userId) {
        try {
            // Fetch the post by ID
            Optional<Post> optionalPost = repo.findById(postId);
            if (optionalPost.isEmpty()) {
                return new ResponseEntity<>("Post not found", HttpStatus.BAD_REQUEST);
            }

            Post post = optionalPost.get();

            // Check if the user has already liked the post
            boolean userLikedPost = post.getLikes().stream()
                    .anyMatch(user -> user.getId().equals(userId));

            if (userLikedPost) {
                // Unlike the post (remove user from the likes list)
                post.getLikes().removeIf(user -> user.getId().equals(userId));
                repo.save(post);
                return new ResponseEntity<>("Post unliked successfully", HttpStatus.OK);
            } else {
                // Like the post (add user to the likes list)
                User user = userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));
                post.getLikes().add(user);
                repo.save(post);
                return new ResponseEntity<>("Post liked successfully", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String replyToPost(String postId, String userId, Map<String, String> requestBody) {
        String text = requestBody.get("text");

        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Text is required");
        }

        if (text.length() < 5) {
            throw new IllegalArgumentException("Text must be at least 5 characters");
        }


        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        Post post = repo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));


        newReply.setText(text);
        newReply.setUserId(user);
        newReply.setUserProfilePic(user.getProfilePic());
        newReply.setUsername(user.getUsername());

        // Cevabı gönderiye ekle
        post.getReplies().add(newReply);

        // Güncellenen gönderiyi kaydet
        repo.save(post);

        return "Reply posted successfully";
    }

}
