package com.nickhe.reciperescue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ViewRecipe extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        Recipe aRecipe = (Recipe) getIntent().getSerializableExtra("Carbonara_Object");

        TextView recipeName = (TextView) findViewById(R.id.recipeName);
        recipeName.setText(aRecipe.getRecipeTitle());

        //TextView recDesription = (TextView) findViewById(R.id.recipeDescription);
        //recDesription.setText(aRecipe.getRecDescription());

        TextView recOwner = (TextView) findViewById(R.id.recipePublisher);
        recOwner.setText("Published by: " + aRecipe.getRecipePublisher());

        StringBuilder ingredientBuilder = new StringBuilder();
        for(String value : aRecipe.getRecipeIngredients()){
            ingredientBuilder.append(value);
        }
        TextView recIngredients = (TextView) findViewById(R.id.ingredientsList);
        recIngredients.setText(ingredientBuilder.toString());

        StringBuilder procedureBuilder = new StringBuilder();
        for(String value : aRecipe.getRecipeInstruction()){
            procedureBuilder.append(value);
        }
        TextView recProcedure = (TextView) findViewById(R.id.proceduresList);
        recProcedure.setText(procedureBuilder.toString());





    }
}
