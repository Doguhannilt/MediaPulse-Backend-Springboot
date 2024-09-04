package com.mediapulse.springboot.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "replies")
public class Reply {
    @Id
    private String id;

    @DBRef
    private User userId;  // User who replied

    private String text;
    private String userProfilePic;
    private String username;

    public Reply(String id, User userId, String text, String userProfilePic, String username) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.userProfilePic = userProfilePic;
        this.username = username;
    }

    public Reply() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserProfilePic() {
        return userProfilePic;
    }

    public void setUserProfilePic(String userProfilePic) {
        this.userProfilePic = userProfilePic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}