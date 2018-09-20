package com.nickhe.reciperescue;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class RecipeListFragment extends Fragment {
    private RecipeListAdapterFilterable recipeListAdapterFilterable = new RecipeListAdapterFilterable(getActivity(), FakeRecipeRepository.getFakeRecipeRepository(getActivity()).getFakeRepo());


    public RecipeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle searchBundle = getArguments();
        if (searchBundle.getString("Type").equals("RecipeName")) {
            recipeListAdapterFilterable.getNameFilter().filter(searchBundle.getString("Name"));
        } else if (searchBundle.getString("Type").equals("Ingredients")) {
            recipeListAdapterFilterable.getRecipeIngredientsFilter().filter(searchBundle.getString("Ingredients"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recipeRecyclerView = (RecyclerView) getView().findViewById(R.id.searchRecyclerView);
        RecyclerViewItemDecorator recyclerViewItemDecorator = new RecyclerViewItemDecorator(10);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recipeRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        recipeRecyclerView.addItemDecoration(recyclerViewItemDecorator);
        recipeRecyclerView.setAdapter(recipeListAdapterFilterable);
    }

    private class RecyclerViewItemDecorator extends RecyclerView.ItemDecoration {
        private int space;

        public RecyclerViewItemDecorator(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.bottom = space;
            outRect.left = space;
            outRect.right = space;

            if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        }
    }
}
//
//