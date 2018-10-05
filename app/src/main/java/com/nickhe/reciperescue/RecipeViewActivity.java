package com.nickhe.reciperescue;

import android.content.Intent;
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

import org.w3c.dom.Text;

import java.util.List;


public class RecipeViewActivity extends AppCompatActivity {

    Recipe recipe;
    String[] ingredients;
    String[] instructions;

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

        int id = getIntent().getIntExtra("id", -1);

        recipe = null;

        if (id != -1) {
            recipe = HomeFragment.fakeRecipeRepository.getFakeRepo().get(id);
            ingredients = recipe.getRecipeIngredients();
            instructions = recipe.getRecipeInstruction();
        }

        recipeImage = findViewById(R.id.recipeImage_viewRecipeActivity);
        recipeTitle = findViewById(R.id.recipeTitleView);
        publisherTextView = findViewById(R.id.publisherTextView);
        ingredientsTextView = findViewById(R.id.ingredients_recipeViewActivity);
        caloriesTextView = findViewById(R.id.calorieTextView);
        timeTextView = findViewById(R.id.timeTextView);
        ingredientsListView = findViewById(R.id.ingredientListView);
        instructionTextView = findViewById(R.id.instructionsTextView);

        recipeImage.setImageBitmap(recipe.getRecipeImage());
        recipeTitle.setText(recipe.getRecipeTitle());
        publisherTextView.setText(recipe.getRecipePublisher());
        ingredientsTextView.setText(String.valueOf(ingredients.length));
        caloriesTextView.setText(recipe.getCalories());
        timeTextView.setText(recipe.getTime());

        final ArrayAdapter<String> ingredientsArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, ingredients) {
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
        setListViewHeightBasedOnChildren(ingredientsListView);

        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<instructions.length;i++)
        {
            stringBuilder.append((i+1)+". "+instructions[i]+"\n\n");
        }

        instructionTextView.setText(stringBuilder.toString());

    }

    /**
     * Make sure the listView will be set by the correct height based on
     * the number of the items it has.
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


}
