package com.nickhe.reciperescue;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    protected View searchView;

    public SearchFragment() {
        recipeNameFilter = "";
        recipeIngredientsFilter = new ArrayList<>();
        recipeMinRatingFilter = 0;
        recipeTagsFilter = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        super.onCreateView(inflater, container, savedInstanceState);
        this.searchView = view;
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        searchButton = searchView.findViewById(R.id.button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                searchRecipe(v);
            }
        });
    }


    public void searchRecipe(View view) {
        TextView recipeNameFilterInput = this.searchView.findViewById(R.id.recipeNameFilterTextInput);
        recipeNameFilter = recipeNameFilterInput.getText().toString();
        Toast.makeText(getActivity(), recipeNameFilter, Toast.LENGTH_SHORT).show();
    }


}
