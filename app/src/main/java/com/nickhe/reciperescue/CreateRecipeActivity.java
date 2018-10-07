package com.nickhe.reciperescue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class CreateRecipeActivity extends AppCompatActivity {

    FirebaseFirestore recipeDB;
    Map<String, Object> recipeSubmit = new HashMap<>();
    Recipe recipe;

    public void submitRecipe(View view) {
        recipeSubmit.put("id", recipe.getId());
        recipeSubmit.put("recipeTitle", recipe.getRecipeTitle());
        recipeSubmit.put("recipeIngredients", recipe.getRecipeIngredients());
        recipeSubmit.put("recipeInstruction", recipe.getRecipeInstruction());
        recipeSubmit.put("recipePublisher", recipe.getRecipePublisher());
        recipeSubmit.put("recipeRating", recipe.getRecipeRating());
        recipeSubmit.put("calories", recipe.getCalories());
        recipeSubmit.put("time", recipe.getTime());
        //TODO IMAGE
    }

    public void addIngredient(View view) {
        LinearLayout layout = findViewById(R.id.recipeIngredientsInputLayout);
        EditText text = new EditText(getApplicationContext());
        text.setHint("Recipe Ingredient");
        layout.addView(text);
    }

    public void addInstruction(View view) {
        LinearLayout layout = findViewById(R.id.recipeInstructionsInputLayout);
        EditText text = new EditText(getApplicationContext());
        text.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        text.setHint("Recipe Instruction");
        layout.addView(text);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

//        recipeDB = FirebaseFirestore.getInstance();
    }
}
