package com.nickhe.reciperescue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);

        int id = getIntent().getIntExtra("id", -1);

        Recipe recipe = null;

        if(id != -1)
        {
            recipe = HomeFragment.fakeRecipeRepository.getFakeRepo().get(id);
        }

        Toast.makeText(this, recipe.getRecipeTitle()+" "+recipe.getRecipePublisher(), Toast.LENGTH_SHORT).show();

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(recipe.getRecipeImage());

        TextView recipeName = (TextView) findViewById(R.id.recipeName);
        recipeName.setText(recipe.getRecipeTitle());

        TextView calorieValue = (TextView) findViewById(R.id.calorieText);
        calorieValue.setText(recipe.getCalories());




       /* ArrayAdapter<String> ingredientsListViewAdapter = new ArrayAdapter<String>(
                this,
                R.layout.listview_layout,
                R.id.ingredientNameTextView,
                recipe.getRecipeIngredients()
        );

        ListView ingredientsListView = (ListView) findViewById(R.id.ingredientsListView);
        ingredientsListView.setAdapter(ingredientsListViewAdapter);

        ArrayAdapter<String> procedureslistViewAdapter = new ArrayAdapter<String>(
                this,
                R.layout.listview_layout,
                R.id.ingredientNameTextView,
                recipe.getRecipeInstruction()
        );

        ListView proceduresListView = (ListView) findViewById(R.id.proceduresListView);
        proceduresListView.setAdapter(procedureslistViewAdapter);*/

        TextView recOwner = (TextView) findViewById(R.id.recipePublisher);
        recOwner.setText("Published by: " + recipe.getRecipePublisher());


        StringBuilder ingredientBuilder = new StringBuilder();
        for(String value : recipe.getRecipeIngredients()){

            ingredientBuilder.append(" - " + value + "\n");

        }
        TextView recIngredients = (TextView) findViewById(R.id.ingredientsList);
        recIngredients.setText(ingredientBuilder.toString());

        int count = 0;
        StringBuilder procedureBuilder = new StringBuilder();
        for(String value : recipe.getRecipeInstruction()){
            count += 1;
            procedureBuilder.append(count + ".) " + value + "\n");
        }
        TextView recProcedure = (TextView) findViewById(R.id.proceduresList);
        recProcedure.setText(procedureBuilder.toString());
    }
}
