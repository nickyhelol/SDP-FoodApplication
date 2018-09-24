package com.nickhe.reciperescue;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * This is the model for user with different attributes.
 *
 * TODO add new feature to the database including recipes. Probably need to extend Json tree.
 */
public class User {

    private String age;
    private String email;
    private String name;
    private String description;        //New added feature
    private Bitmap profileImage;
    private ArrayList<Recipe> personalRepo;

    public User(){

    }

    public User(String name, String age, String email) {
        this.age = age;
        this.email = email;
        this.name = name;
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

    public Bitmap getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Bitmap profileImage) {
        this.profileImage = profileImage;
    }

    public ArrayList<Recipe> getPersonalRepo() {
        return personalRepo;
    }

    public void setPersonalRepo(ArrayList<Recipe> personalRepo) {
        this.personalRepo = personalRepo;
    }
}
