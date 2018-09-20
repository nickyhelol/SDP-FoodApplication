package com.nickhe.reciperescue;

<<<<<<< HEAD
import android.content.Context;
import android.net.Uri;
=======
import android.graphics.Rect;
>>>>>>> a99891c25975d9b8995c126f5a605fbcb95df46e
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecipeListFragment extends Fragment {
<<<<<<< HEAD
=======
    private RecipeListAdapterFilterable recipeListAdapterFilterable = new RecipeListAdapterFilterable(getActivity(), FakeRecipeRepository.getFakeRecipeRepository(getActivity()).getFakeRepo());
>>>>>>> a99891c25975d9b8995c126f5a605fbcb95df46e

    public RecipeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD

=======
        Bundle searchBundle = getArguments();
        if (searchBundle.getString("Type").equals("RecipeName")) {
            recipeListAdapterFilterable.getNameFilter().filter(searchBundle.getString("Name"));
        } else if (searchBundle.getString("Type").equals("Ingredients")) {
            recipeListAdapterFilterable.getRecipeIngredientsFilter().filter(searchBundle.getString("Ingredients"));
        }
>>>>>>> a99891c25975d9b8995c126f5a605fbcb95df46e
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
<<<<<<< HEAD
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recipeRecyclerView.setLayoutManager(staggeredGridLayoutManager);
    }
}
=======
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
>>>>>>> a99891c25975d9b8995c126f5a605fbcb95df46e
