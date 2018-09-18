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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private String recipeNameFilter;
    private ArrayList<String> recipeIngredientsFilter;
    private int recipeMinRatingFilter;
    private ArrayList<String> recipeTagsFilter;
    private Button searchButton;
    private Button addIngredientsButton;
    private int numberOfIngredients;
    private SearchView recipeSearchView;
    protected View searchView;

    /**
     * TODO WRITE COMMENT
     */
    public SearchFragment() {
        recipeNameFilter = "";
        recipeIngredientsFilter = new ArrayList<>();
        recipeMinRatingFilter = 0;
        recipeTagsFilter = new ArrayList<>();
        numberOfIngredients = 1;
    }


    /**
     * TODO WRITE COMMENT
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
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        searchButton = searchView.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                searchRecipe(v);
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
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    /**
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
     */
    public void addIngredients(View view) {
        LinearLayout layout = this.searchView.findViewById(R.id.ingredientsLinearLayout);
        EditText nextIngredient = new EditText(getActivity());
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        nextIngredient.setLayoutParams(p);
        nextIngredient.setTextSize(14);
        nextIngredient.setText("Additional ingredient");
        nextIngredient.setEms(10);
        p.setMargins(0,10,0,0);
        p.setMarginStart(80);
        p.setMarginEnd(80);
        nextIngredient.setId(numberOfIngredients+1);
        layout.addView(nextIngredient);
        ++numberOfIngredients;
    }

}
