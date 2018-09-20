package com.nickhe.reciperescue;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
<<<<<<< HEAD
import android.widget.TextView;
import android.widget.Toast;
=======
>>>>>>> a99891c25975d9b8995c126f5a605fbcb95df46e

import java.util.ArrayList;


/**
 * A Fragment subclass responsible for the searching methods of the Recipe Rescue application.
 * Links to RecipeListFragment for display while filtering data goes to RecipeListAdapterFilterable
 * for filtering.
 */
public class SearchFragment extends Fragment {

<<<<<<< HEAD
    private String recipeNameFilter;
    private ArrayList<String> recipeIngredientsFilter;
=======
    private View searchView;
>>>>>>> a99891c25975d9b8995c126f5a605fbcb95df46e
    private int recipeMinRatingFilter;
    private ArrayList<String> recipeTagsFilter;
    private Button searchButton;
    private Button addIngredientsButton;
    private int numberOfIngredients;
    private SearchView recipeSearchView;
<<<<<<< HEAD
    protected View searchView;

    /**
     * TODO WRITE COMMENT
     */
    public SearchFragment() {
        recipeNameFilter = "";
        recipeIngredientsFilter = new ArrayList<>();
=======


    /**
     * The constructor for SearchFragment fragment. Initialises the variables, notably the numberOfIngredients
     * variable to use with the addIngredients method.
     */
    public SearchFragment() {
>>>>>>> a99891c25975d9b8995c126f5a605fbcb95df46e
        recipeMinRatingFilter = 0;
        recipeTagsFilter = new ArrayList<>();
        numberOfIngredients = 1;
    }


    /**
<<<<<<< HEAD
     * TODO WRITE COMMENT
=======
     * Overrides the onCreateView method from superclass. Initialises searchView to the view of the
     * fragment.
     *
     * @param inflater The LayoutInflater of the search fragment
>>>>>>> a99891c25975d9b8995c126f5a605fbcb95df46e
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        super.onCreateView(inflater, container, savedInstanceState);
        this.searchView = view;
        return view;
    }

    /**
<<<<<<< HEAD
     *
=======
     * TODO
>>>>>>> a99891c25975d9b8995c126f5a605fbcb95df46e
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        searchButton = searchView.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
<<<<<<< HEAD
                searchRecipe(v);
=======
                searchRecipeIngredients(v);
>>>>>>> a99891c25975d9b8995c126f5a605fbcb95df46e
            }
        });
        addIngredientsButton = searchView.findViewById(R.id.addIngredientsButton);
        addIngredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIngredients(v);
            }
        });
        recipeSearchView = searchView.findViewById(R.id.recipeSearchView);
        recipeSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
<<<<<<< HEAD
                return false;
=======
                if (searchRecipeName(query)) {
                    return true;
                } else {
                    return false;
                }
>>>>>>> a99891c25975d9b8995c126f5a605fbcb95df46e
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    /**
<<<<<<< HEAD
     * TODO WRITE COMMENT
     */
    public void searchRecipeName(View view) {

    }

    /**
     * Dummy function for now, displays the recipe name
     * @param view
     */
    public void searchRecipe(View view) {
        LinearLayout linearLayout = searchView.findViewById(R.id.ingredientsLinearLayout);
        for (int i = 0; i < linearLayout.getChildCount(); ++i) {

        }
    }

    /**
     * Adds an extra TextView for additional ingredients.
     * @param view The view of the fragment
=======
     * The searchRecipeName method searches the repository for recipes whose recipe titles are similar
     * to the entered query.
     *
     * @param query The query for the recipe name
     */
    public boolean searchRecipeName(String query) {
        Bundle recipeNameBundle = new Bundle();
        recipeNameBundle.putString("Type", "RecipeName");
        recipeNameBundle.putString("Name", query);
        RecipeListFragment recipeListFragment = new RecipeListFragment();
        recipeListFragment.setArguments(recipeNameBundle);
        getFragmentManager().beginTransaction().replace(R.id.contentLayout, recipeListFragment).commit();

        return true;
    }

    /**
     * The searchRecipeIngredients method searches the repository for recipes that contain all the
     * ingredients specified.
     * Implementation wise, it is linked to the Search button below the ingredients.
     *
     * @param view The view of the searching activity / fragment
     */
    public void searchRecipeIngredients(View view) {
        Bundle recipeIngredientsBundle = new Bundle();
        String ingredients = "";
        recipeIngredientsBundle.putString("Type", "Ingredients");
        LinearLayout linearLayout = searchView.findViewById(R.id.ingredientsLinearLayout);
        for (int i = 1; i < linearLayout.getChildCount(); ++i) {
            EditText ingredientInput = (EditText) linearLayout.getChildAt(i);
            ingredients += ingredientInput.getText();
            ingredients += ",";
        }
        recipeIngredientsBundle.putString("Ingredients", ingredients);

        RecipeListFragment recipeListFragment = new RecipeListFragment();
        recipeListFragment.setArguments(recipeIngredientsBundle);
        getFragmentManager().beginTransaction().replace(R.id.contentLayout, recipeListFragment).commit();
    }

    /**
     * The addIngredients method adds a new EditText field under the last ingredient EditText field.
     * This method takes the Layout of the ingredients field and adds another into it, with the id
     * dynamically assigned.
     *
     * @param view The view of the search fragment
>>>>>>> a99891c25975d9b8995c126f5a605fbcb95df46e
     */
    public void addIngredients(View view) {
        LinearLayout layout = this.searchView.findViewById(R.id.ingredientsLinearLayout);
        EditText nextIngredient = new EditText(getActivity());
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        nextIngredient.setLayoutParams(p);
        nextIngredient.setTextSize(14);
        nextIngredient.setText("Additional ingredient");
        nextIngredient.setEms(10);
<<<<<<< HEAD
        p.setMargins(0,10,0,0);
        p.setMarginStart(80);
        p.setMarginEnd(80);
        nextIngredient.setId(numberOfIngredients+1);
=======
        p.setMargins(0, 10, 0, 0);
        p.setMarginStart(80);
        p.setMarginEnd(80);
        nextIngredient.setId(numberOfIngredients + 1);
>>>>>>> a99891c25975d9b8995c126f5a605fbcb95df46e
        layout.addView(nextIngredient);
        ++numberOfIngredients;
    }

    /**
     * TODO Add rating filter - Sprint 2
     */
}
