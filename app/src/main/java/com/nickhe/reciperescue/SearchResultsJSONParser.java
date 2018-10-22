package com.nickhe.reciperescue;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsJSONParser {
    private RecipeJSONParser recipeParser = new RecipeJSONParser();

    public List<Recipe> parseResults(JSONObject jsonObject) {
        if (jsonObject == null)
            return null;
        List<Recipe> results = new ArrayList<>();
        JSONArray hits = jsonObject.optJSONArray("hits");
        if (hits == null)
            return null;
        for (int i = 0; i < hits.length(); ++i) {
            JSONObject hit = hits.optJSONObject(i);
            if (hit == null)
                continue;
            Recipe recipe = recipeParser.parse(hit);
            if (recipe == null)
                continue;
            results.add(recipe);
        }
        return results;
    }
}
