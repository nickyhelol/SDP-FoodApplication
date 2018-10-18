package com.nickhe.reciperescue;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Recipe object to use in the program, should be able to be stored in the database.
 * <p>
 * The ideal recipe object should contain the following attributes:
 * a. id - For identification purposes, e.g. viewing a recipe
 * b. recipeTitle - The name of the recipe
 * c. recipeIngredients - The list of ingredients used by the recipe
 * d. recipePublisher - The user that published the recipe
 * e. time - The cooking time of the recipe
 * f. calories - The amount of calories of the recipe
 * g. recipeInstruction - The list of instructions for the recipe
 * h. recipeRating - The rating of the recipe
 * i. recipeImage - The image of the recipe
 */
public class Recipe implements Parcelable {

    private String recipeTitle;
    private String[] recipeIngredients;
    private String recipePublisher;
    private String time;
    private String calories;
    private String[] recipeInstruction;
    private Rating recipeRating;
    private Uri recipeImageUri;

    /**
     * Constructor for the recipe object, minimum fields for a recipe.
     *
     * @param recipeTitle     desired recipe name
     * @param recipePublisher the publisher of the recipe
     * @param time            time needed for cooking
     */
    public Recipe(String recipeTitle, String recipePublisher, String time) {
        this.time = time;
        this.recipeTitle = recipeTitle;
        this.recipePublisher = recipePublisher;
    }

    public Recipe(String recipeTitle,String[] recipeIngredients,
                  String time, String calories, String[] recipeInstruction){

    }
    /**
     * Proper constructor for the recipe object, contains all the required fields for the recipe.
     * Should be used in all cases.
     *
     * @param recipeTitle       title of the recipe
     * @param recipeIngredients ingredients required for the recipe
     * @param recipePublisher   publisher of the recipe
     * @param time              time required for cooking
     * @param calories          calories contained
     * @param recipeInstruction instructions for cooking
     * @param recipeImageUri       image of the recipe
     */
    public Recipe(String recipeTitle, String[] recipeIngredients, String recipePublisher, String time, String calories, String[] recipeInstruction, Uri recipeImageUri) {
        this.recipeTitle = recipeTitle;
        this.recipeIngredients = recipeIngredients;
        this.recipePublisher = recipePublisher;
        this.time = time;
        this.calories = calories;
        this.recipeInstruction = recipeInstruction;
        this.recipeRating = Rating.THREE;
        this.recipeImageUri = recipeImageUri;
    }

    public Recipe(String recipeTitle, String[] recipeIngredients, String recipePublisher, String time, String calories, String[] recipeInstruction, Rating recipeRating, Uri recipeImageUri) {
        this.recipeTitle = recipeTitle;
        this.recipeIngredients = recipeIngredients;
        this.recipePublisher = recipePublisher;
        this.time = time;
        this.calories = calories;
        this.recipeInstruction = recipeInstruction;
        this.recipeRating = recipeRating;
        this.recipeImageUri = recipeImageUri;
    }

    protected Recipe(Parcel in) {
        recipeTitle = in.readString();
        recipeIngredients = in.createStringArray();
        recipePublisher = in.readString();
        time = in.readString();
        calories = in.readString();
        recipeInstruction = in.createStringArray();
        recipeRating = Rating.valueOf(in.readString());
        recipeImageUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recipeTitle);
        dest.writeStringArray(recipeIngredients);
        dest.writeString(recipePublisher);
        dest.writeString(time);
        dest.writeString(calories);
        dest.writeStringArray(recipeInstruction);
        dest.writeString(recipeRating.name());
        dest.writeParcelable(recipeImageUri, flags);
    }

    public Uri getRecipeImage() {
        return recipeImageUri;
    }

    public void setRecipeImage(Uri recipeImageUri) {
        this.recipeImageUri = recipeImageUri;
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

}