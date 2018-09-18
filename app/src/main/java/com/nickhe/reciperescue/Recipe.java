package com.nickhe.reciperescue;

import java.io.Serializable;

public class Recipe implements Serializable {
    private String recipeTitle;
    private String recipeDescription;
    private String[] recipeIngredients;
    private String recipePublisher;
    private String time;
    private String calories;
    private String[] recipeInstruction;
    private Rating recipeRating;


    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
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
}