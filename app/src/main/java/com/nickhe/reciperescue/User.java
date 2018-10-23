package com.nickhe.reciperescue;

import android.net.Uri;

import java.util.ArrayList;

/**
 * This is the model for user with different attributes.
 */
public class User {

    private String age;
    private String email;
    private String name;
    private String description;        //New added feature
    private Uri profileImage;
    private ArrayList<User> personalRepo;
    private int score;

    public User(String name, String age, String email) {
        this.age = age;
        this.email = email;
        this.name = name;
    }

    public User(String name, int score, Uri profileImage) {
        this.name = name;
        this.score = score;
        this.profileImage = profileImage;
    }


    public User() {

    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public ArrayList<User> getPersonalRepo() {
        return personalRepo;
    }

    public void setPersonalRepo(ArrayList<User> personalRepo) {
        this.personalRepo = personalRepo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
