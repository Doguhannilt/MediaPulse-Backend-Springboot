package com.mediapulse.springboot.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@SuppressWarnings("unused")
@Document(collection = "posts")
public class Post {
    @Id
    private String id;

    @DBRef
    private User postedBy;

    private String text;
    private String img;

    @DBRef
    private List<User> likes;  // List of Users who liked the post

    private List<Reply> replies;


    public Post(String id, User postedBy, String text, String img, List<User> likes, List<Reply> replies) {
        this.id = id;
        this.postedBy = postedBy;
        this.text = text;
        this.img = img;
        this.likes = likes;
        this.replies = replies;
    }

    public Post() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }
}