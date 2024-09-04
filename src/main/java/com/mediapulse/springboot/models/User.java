package com.mediapulse.springboot.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Array;
import java.util.List;

@Document(collection = "users")
public class User {
    // name String, username String Unique, email String Unique, password String minLength:6, profilePic String, followers [string], following [string],bio String,

    @Id
    private String id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String profilePic;
    private List<String> followers;
    private List<String> following;
    private String bio;


    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String name, String username, String id,String email, String password, String profilePic, List<String> followers, List<String> following, String bio) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePic = profilePic;
        this.followers = followers;
        this.following = following;
        this.bio = bio;
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", followers=" + followers +
                ", following=" + following +
                ", bio='" + bio + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


}
