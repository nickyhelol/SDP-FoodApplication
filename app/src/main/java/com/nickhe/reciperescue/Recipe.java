package com.nickhe.reciperescue;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Recipe implements Serializable {

    private int id;
    private String recipeTitle;
    private String[] recipeIngredients;
    private String recipePublisher;
    private String time;
    private String calories;
    private String[] recipeInstruction;
    private Rating recipeRating;
    private Bitmap recipeImage;

    public Recipe(int id, String recipeTitle, String[] recipeIngredients, String recipePublisher, String time, String calories, String[] recipeInstruction, Bitmap recipeImage) {
        this.id = id;
        this.recipeTitle = recipeTitle;
        this.recipeIngredients = recipeIngredients;
        this.recipePublisher = recipePublisher;
        this.time = time;
        this.calories = calories;
        this.recipeInstruction = recipeInstruction;
        this.recipeImage = recipeImage;
    }

    public Bitmap getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(Bitmap recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String[] getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String[] recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getRecipePublisher() {
        return recipePublisher;
    }

    public void setRecipePublisher(String recipePublisher) {
        this.recipePublisher = recipePublisher;
    }

    public String[] getRecipeInstruction() {
        return recipeInstruction;
    }

    public void setRecipeInstruction(String[] recipeInstruction) {
        this.recipeInstruction = recipeInstruction;
    }

    public Rating getRecipeRating() {
        return recipeRating;
    }

    public void setRecipeRating(Rating recipeRating) {
        this.recipeRating = recipeRating;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}