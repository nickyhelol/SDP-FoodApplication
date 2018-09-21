package com.nickhe.reciperescue;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapterFilterable extends RecyclerView.Adapter implements Filterable {

    private List<Recipe> originalRecipes;
    private List<Recipe> filteredRecipes;
    private RecipeNameFilter recipeNameFilter;
    private RecipeIngredientsFilter recipeIngredientsFilter;

    /**
     * Public constructor for the RecipeListAdapter
     * Sets the recipe list to the repository before filtering.
     *
     * @param context Application context
     * @param recipes Recipe list from the repository
     */
    public RecipeListAdapterFilterable(Activity context, List<Recipe> recipes) {
        originalRecipes = recipes;
        filteredRecipes = recipes;
        recipeNameFilter = new RecipeNameFilter();
        recipeIngredientsFilter = new RecipeIngredientsFilter();
    }

    /**
     * Gets the RecipeIngredientsFilter object for filtering recipes by ingredients. Public so it can
     * be accessed by the RecipeList fragment.
     *
     * @return the RecipeIngredientsFilter of the adapter for filtering
     */
    public Filter getRecipeIngredientsFilter() {
        return recipeIngredientsFilter;
    }

    /**
     * Gets the RecipeNameFilter object for filtering recipes by recipe name (recipe title). Public
     * so it is accessible by the RecipeList fragment.
     *
     * @return the RecipeNameFilter of the adapter for filtering
     */
    public Filter getNameFilter() {
        return recipeNameFilter;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final Recipe recipe = filteredRecipes.get(position);
        viewHolder.textView.setText(recipe.getRecipeTitle());
        viewHolder.imageView.setImageBitmap(recipe.getRecipeImage());
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() { //Adds an onclick listener for every recipe image in the adapter
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RecipeViewActivity.class);

                intent.putExtra("id", recipe.getId());

                v.getContext().startActivity(intent);
            }
        });
    }

    /**
     * Gets the count of the result of the filter, in this case the filteredRecipes object.
     *
     * @return the number of recipes contained in the filteredRecipes object
     */
    @Override
    public int getItemCount() {
        return filteredRecipes.size();
    }

    /**
     * Default filter is the recipe name filter
     * @return the RecipeNameFilter of the adapter
     */
    @Override
    public Filter getFilter() {
        return getNameFilter();
    }

    /**
     * Custom ViewHolder class to display the text and image for a recipe object.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.recipeTextView);
            imageView = view.findViewById(R.id.recipeImageView);
        }
    }

    /**
     *
     */
    private class RecipeIngredientsFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String[] ingredients = constraint.toString().split(",");

            FilterResults recipeResults = new FilterResults();
            List<Recipe> recipes = originalRecipes;
            Recipe recipe;
            ArrayList<Recipe> recipeList = new ArrayList<>();

            for (int i = 0; i < recipes.size(); ++i) {
                int counter = 0;
                recipe = recipes.get(i);
                String[] recipeIngredients = recipe.getRecipeIngredients();
                for (int ii = 0; ii < ingredients.length; ++ii) {
                    for (int iii = 0; iii < recipeIngredients.length; ++iii) {
                        if (recipeIngredients[iii].toLowerCase().contains(ingredients[ii].toLowerCase())) {
                            ++counter;
                        }
                    }
                }
                if (counter >= ingredients.length) {
                    recipeList.add(recipe);
                }
            }

            recipeResults.values = recipeList;
            recipeResults.count = recipeList.size();

            return recipeResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredRecipes = (ArrayList<Recipe>) results.values;
            notifyDataSetChanged();
        }
    }

    /**
     *
     */
    private class RecipeNameFilter extends Filter {

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


        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredRecipes = (ArrayList<Recipe>) results.values;
            notifyDataSetChanged();
        }
    }

}

