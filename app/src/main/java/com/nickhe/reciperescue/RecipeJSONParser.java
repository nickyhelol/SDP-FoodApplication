package com.nickhe.reciperescue;

import org.json.JSONObject;

public class RecipeJSONParser {
    public Recipe parse(JSONObject jsonObject) {
        String recipeTitle = jsonObject.optString("recipeTitle");
        String[] recipeIngredients = jsonObject.optString("recipeIngredients").split(",");
        String time = jsonObject.optString("time");
        String calories = jsonObject.optString("calories");
        String[] recipeInstruction = jsonObject.optString("recipeInstruction").split(",");
        Recipe recipe = new Recipe(recipeTitle,recipeIngredients, time, calories, recipeInstruction);

        return recipe;
    }
}
