package com.nickhe.reciperescue;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.ToggleButton;

import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;


/**
 * A Fragment subclass responsible for the searching methods of the Recipe Rescue application.
 * Links to RecipeListFragment for display while filtering data goes to RecipeListAdapterFilterable
 * for filtering.
 */
public class SearchFragment extends Fragment {

    private View searchView;
    private int recipeMinRatingFilter; //TODO SPRINT 2
    private ArrayList<String> recipeTagsFilter; //TODO SPRINT 2
    private Button searchButton;
    private Button addIngredientsButton;
    private int numberOfIngredients;
    private SearchView recipeSearchView;
    private IngredientTokensAdapter ingredientTokensAdapter = new IngredientTokensAdapter(getActivity());
    /**
     * The constructor for SearchFragment fragment. Initialises the variables, notably the numberOfIngredients
     * variable to use with the addIngredients method.
     */
    public SearchFragment() {
        recipeMinRatingFilter = 0;
        recipeTagsFilter = new ArrayList<>();
        numberOfIngredients = 0;
    }

    /**
     * Overrides the onCreateView method from superclass. Initialises searchView to the view of the
     * fragment.
     *
     * @param inflater The LayoutInflater of the search fragment
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
     * TODO
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        searchButton = searchView.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                searchRecipeIngredients(v);
            }
        });
        addIngredientsButton = searchView.findViewById(R.id.addIngredientsButton);
        addIngredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIngredientToList(v);
            }
        });
        recipeSearchView = searchView.findViewById(R.id.recipeSearchView);

        recipeSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (searchRecipeName(query)) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    /**
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
    public boolean searchRecipeIngredients(View view) {
        Bundle recipeIngredientsBundle = new Bundle();
        String ingredients = "";
        recipeIngredientsBundle.putString("Type", "Ingredients");
        FlowLayout layout = searchView.findViewById(R.id.searchFlowLayout);
        for (int i = 0; i < layout.getChildCount(); ++i) {
//            LinearLayout ingredientLayout = (LinearLayout) layout.getChildAt(i);
            ToggleButton ingredientInput = (ToggleButton) layout.getChildAt(i);
            if (ingredientInput.isChecked()) {
                ingredients += ingredientInput.getText();
                ingredients += ",";
            }
        }
        recipeIngredientsBundle.putString("Ingredients", ingredients);

        RecipeListFragment recipeListFragment = new RecipeListFragment();
        recipeListFragment.setArguments(recipeIngredientsBundle);
        getFragmentManager().beginTransaction().replace(R.id.contentLayout, recipeListFragment).commit();

        if (recipeIngredientsBundle.get("Ingredients").equals(ingredients)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * The addIngredients method adds a new EditText field under the last ingredient EditText field.
     * This method takes the Layout of the ingredients field and adds another into it, with the id
     * dynamically assigned.
     *
     * @param view The view of the search fragment
     */
    public boolean addIngredients(View view) {
        LinearLayout layout = this.searchView.findViewById(R.id.ingredientsLinearLayout);
        EditText nextIngredient = new EditText(getActivity());
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        nextIngredient.setLayoutParams(p);
        nextIngredient.setTextSize(14);
        nextIngredient.setText("Additional ingredient");
        nextIngredient.setEms(10);
        p.setMargins(0, 10, 0, 0);
        p.setMarginStart(80);
        p.setMarginEnd(80);
        nextIngredient.setId(numberOfIngredients + 1);
        layout.addView(nextIngredient);
        ++numberOfIngredients;

        if (nextIngredient != null) {
            return true;
        } else {
            return false;
        }
    }

    public void addIngredientToList(View view) {
        EditText ingredientText = this.searchView.findViewById(R.id.recipeIngredientsFilterInput1);
        String buttonText = ingredientText.getText().toString();
//        ingredientTokensAdapter.addToken(buttonText);
        ToggleButton ingredientToken = new ToggleButton(getActivity());
        FlowLayout flowLayout = this.searchView.findViewById(R.id.searchFlowLayout);
        ingredientToken.setId(numberOfIngredients);
        ingredientToken.setText(buttonText);
        ingredientToken.setTextOff(buttonText);
        ingredientToken.setTextOn(buttonText);
        ingredientToken.setBackgroundResource(R.drawable.token_states);
        ingredientToken.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ingredientToken.setGravity(Gravity.CENTER);
        ingredientToken.setPadding(5,5,5,5);
        ingredientToken.setMaxHeight(10);
        flowLayout.addView(ingredientToken);
        ++numberOfIngredients;
    }

    /**
     * TODO Add rating filter - Sprint 2
     * TODO Add tags filter - Sprint 2
     */
}
