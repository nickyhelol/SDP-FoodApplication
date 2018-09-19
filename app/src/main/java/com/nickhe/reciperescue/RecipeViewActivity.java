package com.nickhe.reciperescue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class RecipeViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);

        int position = getIntent().getIntExtra("position", -1);

        Recipe recipe = null;

        if(position != -1)
        {
            recipe = HomeFragment.fakeRecipeRepository.getFakeRepo().get(position);
        }

        Toast.makeText(this, recipe.getRecipeTitle()+" "+recipe.getRecipePublisher(), Toast.LENGTH_SHORT).show();
    }
}
