package com.nickhe.reciperescue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;


public class RecipeViewActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);

        //Once the intent is called, this activity picks up the recipe by its index
        int id = getIntent().getIntExtra("id", -1);

        Recipe recipe = null;

        if(id != -1)
        {
            recipe = HomeFragment.fakeRecipeRepository.getFakeRepo().get(id);
        }

        //We build the ingredients and procedure from String array back into a single string
        StringBuilder ingredientBuilder = new StringBuilder();
        for(String value : recipe.getRecipeIngredients()){

            ingredientBuilder.append(" - " + value + "\n");

        }

        int count = 0;
        StringBuilder procedureBuilder = new StringBuilder();
        for(String value : recipe.getRecipeInstruction()){
            count += 1;
            procedureBuilder.append(count + ".) " + value + "\n");
        }

        //WE initialize the elements from the XML file into this activity
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView recipeName = (TextView) findViewById(R.id.recipeName);
        TextView calorieValue = (TextView) findViewById(R.id.calorieText);
        TextView recOwner = (TextView) findViewById(R.id.recipePublisher);
        TextView recIngredients = (TextView) findViewById(R.id.ingredientsList);
        TextView recProcedure = (TextView) findViewById(R.id.proceduresList);

        //WE set the text/image values of the XML file that we're going to display on the layout
        imageView.setImageBitmap(recipe.getRecipeImage());
        recipeName.setText(recipe.getRecipeTitle());
        calorieValue.setText(recipe.getCalories());
        recOwner.setText("Published by: " + recipe.getRecipePublisher());
        recIngredients.setText(ingredientBuilder.toString());
        recProcedure.setText(procedureBuilder.toString());

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
    }
}
