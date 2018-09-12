package com.nickhe.reciperescue;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public SearchFragment() {
        recipeNameFilter = "";
        recipeIngredientsFilter = new ArrayList<>();
        recipeMinRatingFilter = 0;
        recipeTagsFilter = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    public void searchRecipe() {
        TextView recipeNameFilterInput = getView().findViewById(R.id.recipeNameFilterTextInput);
        recipeNameFilter = recipeNameFilterInput.getText().toString();
        Toast.makeText(getContext(), recipeNameFilter, Toast.LENGTH_SHORT);
    }


}
