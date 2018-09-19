package com.nickhe.reciperescue;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.security.PublicKey;

public class Recipe {

        private String recipeTitle;
        private String[] recipeIngredients;
        private String recipePublisher;
        private String time;
        private String calories;
        private String[] recipeInstruction;
        private Rating recipeRating;
        private Bitmap recipeImage;

        public Recipe(String recipeTitle, String[] recipeIngredients, String recipePublisher, String time, String calories, String[] recipeInstruction, Bitmap recipeImage) {
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


//        @Override
//        public int describeContents() {
//            return 0;
//        }
//
//        /**
//         * Storing the data of the recipe to parcel
//         * @param dest
//         * @param flags
//         */
//        @Override
//        public void writeToParcel(Parcel dest, int flags) {
//
//            dest.writeString(recipeTitle);
//            dest.writeStringArray(recipeIngredients);
//            dest.writeString(recipePublisher);
//            dest.writeString(time);
//            dest.writeString(calories);
//            dest.writeStringArray(recipeInstruction);
//            dest.writeParcelable(recipeImage, 0);
//        }
//
//        /**
//         * Retrieving Recipe data from Parcel object.
//         * This constructor is invoked by createFromParcel method
//         * in CREATOR object.
//         * @param in
//         */
//        private Recipe(Parcel in)
//        {
//            recipeTitle = in.readString();
//            recipeIngredients = in.createStringArray();
//            recipePublisher = in.readString();
//            time = in.readString();
//            calories = in.readString();
//            recipeInstruction = in.createStringArray();
//            recipeImage = in.readParcelable(Bitmap.class.getClassLoader());
//        }
//
//        public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
//            @Override
//            public Recipe createFromParcel(Parcel in) {
//                return new Recipe(in);
//            }
//
//            @Override
//            public Recipe[] newArray(int size) {
//                return new Recipe[size];
//            }
//        };

}
