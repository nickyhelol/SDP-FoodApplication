package com.nickhe.reciperescue;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.algolia.search.saas.AlgoliaException;
import com.algolia.search.saas.Client;
import com.algolia.search.saas.CompletionHandler;
import com.algolia.search.saas.IndexQuery;
import com.algolia.search.saas.Query;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * RecipeListAdapterFilterable class for RecyclerView implementation. Provides filter by:
 * a. Recipe Title
 * b. Recipe Ingredient(s)
 * <p>
 * On-click listener for each recipe item is included in the custom ViewHolder class.
 */
public class RecipeListAdapterFilterable extends RecyclerView.Adapter implements Filterable {

    private List<Recipe> originalRecipes;
    private List<Recipe> filteredRecipes;
    private RecipeNameFilter recipeNameFilter;
    private RecipeIngredientsFilter recipeIngredientsFilter;
    private FirebaseFirestore firebaseFirestore;
    private Query query;
    private Algolia algolia;
    private SearchResultsJSONParser searchResultsJSONParser;
    private Activity context;

    /**
     * Public constructor for the RecipeListAdapter
     * Sets the recipe list to the repository before filtering.
     *
     * @param context Application context
     * @param recipes Recipe list from the repository
     */
    public RecipeListAdapterFilterable(Activity context, List<Recipe> recipes) {
        this.context = context;
        originalRecipes = recipes;
        filteredRecipes = recipes;
        recipeNameFilter = new RecipeNameFilter();
        recipeIngredientsFilter = new RecipeIngredientsFilter();
        firebaseFirestore = FirebaseFirestore.getInstance();
        algolia = new Algolia();
        searchResultsJSONParser = new SearchResultsJSONParser();

    }

    /**
     * The getOriginalRecipes method returns the unfiltered recipe list
     *
     * @return unfiltered recipe list when adapter was created
     */
    public List<Recipe> getOriginalRecipes() {
        return originalRecipes;
    }

    /**
     * The getFilteredRecipes method returns the filtered recipe list after one of the filters is used
     * to search the recipe list
     *
     * @return the result of the filter
     */
    public List<Recipe> getFilteredRecipes() {
        return filteredRecipes;
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
        if (recipe.getRecipeImage() != null) {
            Bitmap bitmap = ImageProcessor.convertUriToBitmap(context, recipe.getRecipeImage());
            viewHolder.imageView.setImageBitmap(bitmap);
        } else {
            Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.drawable.no_picture);
            viewHolder.imageView.setImageBitmap(image);
        }

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() { //Adds an onclick listener for every recipe image in the adapter
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RecipeViewActivity.class);

                intent.putExtra("recipe", recipe); //Adds an extra to the intent, the recipe id

                v.getContext().startActivity(intent); //Starts the view recipe activity
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
     *
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
     * RecipeIngredientsFilter class for filtering recipes by ingredients
     */
    private class RecipeIngredientsFilter extends Filter {

        /**
         * Performs the filtering for the RecipeIngredientsFilter class, used in the adapter to filter
         * by ingredients
         *
         * @param constraint the list of ingredients to be filtered, will be separated by commas
         * @return the result of the filtering
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String[] ingredients = constraint.toString().split(",");

            FilterResults recipeResults = new FilterResults();
            List<Recipe> recipes = originalRecipes;
            Recipe recipe;
            final List<Recipe> recipeList = new ArrayList<>();

            List<IndexQuery> queries = new ArrayList<>();
            for (String s : ingredients) {
                queries.add(new IndexQuery("recipeIngredients", new Query(s)));
            }
            algolia.client.multipleQueriesAsync(queries, Client.MultipleQueriesStrategy.NONE, new CompletionHandler() {
                @Override
                public void requestCompleted(JSONObject jsonObject, AlgoliaException e) {
                    List<Recipe> recipes = searchResultsJSONParser.parseResults(jsonObject);
                    recipeList.addAll(recipes);
                }
            });

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
     * RecipeNameFilter class to filter recipes by recipe title, by default, the getFilter method
     * returns this object.
     */
    private class RecipeNameFilter extends Filter {

        /**
         * Performs the filtering for RecipeNameFilter class, used in the adapter to filter by recipe
         * title.
         *
         * @param constraint the name / part of the name of the desired recipe
         * @return the result of the filtering
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String nameFilter = constraint.toString().toLowerCase();

            FilterResults recipeResults = new FilterResults();
            List<Recipe> recipes = originalRecipes;
            final List<Recipe> recipeList = new ArrayList<>();
            Recipe recipe;
            query = new Query(nameFilter).setRestrictSearchableAttributes("recipeTitle");

            for (int i = 0; i < recipes.size(); ++i) {
                recipe = recipes.get(i);
                if (recipe.getRecipeTitle().toLowerCase().contains(nameFilter)) {
                    recipeList.add(recipe);
                }
            }
            algolia.index.searchAsync(query, new CompletionHandler() {
                @Override
                public void requestCompleted(JSONObject jsonObject, AlgoliaException e) {
                    List<Recipe> recipes = searchResultsJSONParser.parseResults(jsonObject);
                    recipeList.addAll(recipes);
                }
            });
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

