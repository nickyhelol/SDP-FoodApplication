package com.nickhe.reciperescue;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;


public class RecipeViewActivity extends AppCompatActivity {

    Recipe recipe;
    ImageView recipeImage;
    TextView recipeTitle;
    TextView publisherTextView;
    TextView ingredientsTextView;
    TextView caloriesTextView;
    TextView timeTextView;
    ListView ingredientsListView;
    TextView instructionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);

        receiveData();
        initializeViews();
        updateView();
        initializeIngredientsListView();
        initializeInstructionView();
    }

    private void initializeInstructionView(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<recipe.getRecipeInstruction().length;i++)
        {
            stringBuilder.append((i+1)+". "+recipe.getRecipeInstruction()[i]+"\n\n");
        }

        instructionTextView.setText(stringBuilder.toString());
    }

    private void initializeIngredientsListView(){
        final ArrayAdapter<String> ingredientsArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, recipe.getRecipeIngredients()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Get the current item from ListView
                View view = super.getView(position, convertView, parent);
                if (position % 2 == 1) {
                    // Set a background color for ListView regular row/item
                    view.setBackgroundColor(Color.parseColor("#d9e0de"));
                }

                return view;
            }
        };

        ingredientsListView.setAdapter(ingredientsArrayAdapter);
        ListViewProcessor.setListViewHeightBasedOnChildren(ingredientsListView);
    }

    private void initializeViews(){
        recipeImage = findViewById(R.id.recipeImage_viewRecipeActivity);
        recipeTitle = findViewById(R.id.recipeTitleView);
        publisherTextView = findViewById(R.id.publisherTextView);
        ingredientsTextView = findViewById(R.id.ingredients_recipeViewActivity);
        caloriesTextView = findViewById(R.id.calorieTextView);
        timeTextView = findViewById(R.id.timeTextView);
        ingredientsListView = findViewById(R.id.ingredientListView);
        instructionTextView = findViewById(R.id.instructionsTextView);
    }

    /**
     * Update view based on the info of the recipe selected
     */
    private void updateView()
    {
        if (recipe.getRecipeImage() != null) {
            Picasso.get().load(recipe.getRecipeImage()).into(recipeImage);
        } else {
            BitmapFactory.decodeResource(getResources(), R.drawable.no_picture);
        }
        recipeTitle.setText(recipe.getRecipeTitle());
        publisherTextView.setText(recipe.getRecipePublisher());
        ingredientsTextView.setText(String.valueOf(recipe.getRecipeIngredients().length));
        caloriesTextView.setText(recipe.getCalories());
        timeTextView.setText(recipe.getTime());
    }

    /**
     * RECEIVE DATA FROM FRAGMENT
     */
    private void receiveData() {
        Intent i = getIntent();
        recipe = i.getParcelableExtra("recipe");
    }


}
