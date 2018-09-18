/*
package com.nickhe.reciperescue;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapterFilterable extends RecipeListAdapter implements Filterable {

    private LayoutInflater mInflater;
    private List<Recipe> originalRecipes;
    private List<Recipe> filteredRecipes;

    private class recipeNameFilter extends Filter {

*
         * TODO WRITE COMMENT


*
         *
         * @param constraint Text to look for
         * @return


        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String nameFilter = constraint.toString().toLowerCase();

            FilterResults recipeResults = new FilterResults();
            List<Recipe> recipes = originalRecipes;
            ArrayList<Recipe> recipeList = new ArrayList<>();

            Recipe recipe;

            for (int i = 0; i < recipes.size(); ++i) {
                recipe = recipes.get(i);
                if (recipe.getRecipeTitle().toLowerCase().contains(nameFilter)) {
                    recipeList.add(recipe);
                }
            }

            recipeResults.values = recipeList;
            recipeResults.count = recipeList.size();

            return recipeResults;
        }

*
         * TODO WRITE COMMENT


        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredRecipes = (ArrayList<Recipe>) results.values;
            notifyDataSetChanged();
        }
    }

*
     * TODO WRITE COMMENT


public RecipeListAdapterFilterable(Activity context, String[] titles, int[] images) {
        super(context, titles, images);
        mInflater = LayoutInflater.from(context);
    }

}
*/
