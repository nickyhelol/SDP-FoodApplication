package com.nickhe.reciperescue;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class RecipeListFragment extends Fragment {
    private RecipeListAdapterFilterable recipeListAdapterFilterable;
//    private RecipeFirestoreRecyclerAdapter recipeFirestoreRecyclerAdapter;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Query query = db.collection("recipes");

    public RecipeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle searchBundle = getArguments();

        recipeListAdapterFilterable = new RecipeListAdapterFilterable(getActivity(), FakeRecipeRepository.getFakeRecipeRepository(getActivity()).getFakeRepo());

        if (searchBundle.getString("Type").equals("RecipeName")) {
//            query.whereEqualTo("recipeTitle", searchBundle.getString("Name"));
//            FirestoreRecyclerOptions<Recipe> response = new FirestoreRecyclerOptions.Builder<Recipe>()
//                    .setQuery(query, Recipe.class)
//                    .build();
//            recipeFirestoreRecyclerAdapter = new RecipeFirestoreRecyclerAdapter(response);
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
