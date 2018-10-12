package com.nickhe.reciperescue;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.algolia.search.saas.CompletionHandler;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateRecipeActivity extends AppCompatActivity {

    FirebaseFirestore recipeDB;
    Recipe recipe;
    FirebaseAuth firebaseAuth;
    HashMap<String, Object> recipeSubmitFireStore = new HashMap();
    JSONObject recipeSubmit;
    Algolia algolia;

    public void submitRecipe(View view) throws JSONException {

        getRecipeFields(view);


        //TODO IMAGE

        recipeDB.collection("recipes")
                .add(recipeSubmitFireStore)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        algolia.index.addObjectAsync(recipeSubmit,null);
                        Toast.makeText(getApplicationContext(), "DocumentSnapshot written with ID: " + documentReference.getId(),Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error adding document" +e,Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getRecipeFields (View view) throws JSONException {
        EditText recipeTitleEditText = findViewById(R.id.recipeTitleInput);
        String recipeTitle = recipeTitleEditText.getText().toString();

        EditText caloriesEditText = findViewById(R.id.caloriesInput);
        String calories = caloriesEditText.getText().toString();

        EditText timeEditText = findViewById(R.id.timeInput);
        String time = timeEditText.getText().toString();

        LinearLayout ingredientsLayout = findViewById(R.id.recipeIngredientsInputLayout);
        String[] ingredientsArray = new String[ingredientsLayout.getChildCount()];
        for (int i = 0; i < ingredientsLayout.getChildCount(); ++i) {
            EditText ingredientText = (EditText) ingredientsLayout.getChildAt(i);
            ingredientsArray[i] = ingredientText.getText().toString();
        }

        LinearLayout instructionsLayout = findViewById(R.id.recipeInstructionsInputLayout);
        String[] instructionsArray = new String[instructionsLayout.getChildCount()];
        for (int i = 0; i < instructionsLayout.getChildCount(); ++i) {
            EditText instructionText = (EditText) instructionsLayout.getChildAt(i);
            instructionsArray[i] = instructionText.getText().toString();
        }

        recipeSubmit = new JSONObject();
        List ingredientsList;
        List instructionsList;

        ingredientsList = Arrays.asList(ingredientsArray);
        instructionsList = Arrays.asList(instructionsArray);

        recipeSubmitFireStore.put("recipeTitle",recipeTitle);
        recipeSubmitFireStore.put("recipeIngredients", ingredientsList);
        recipeSubmitFireStore.put("recipeInstruction", instructionsList);
        recipeSubmitFireStore.put("calories",calories);
        recipeSubmitFireStore.put("time",time);
        recipeSubmitFireStore.put("recipePublisher", firebaseAuth.getCurrentUser().getUid());
        
        recipeSubmit.put("recipeTitle",recipeTitle);
        recipeSubmit.put("recipeIngredients", ingredientsList);
        recipeSubmit.put("recipeInstruction", instructionsList);
        recipeSubmit.put("calories",calories);
        recipeSubmit.put("time",time);
        recipeSubmit.put("recipePublisher", firebaseAuth.getCurrentUser().getUid());
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

        firebaseAuth = FirebaseAuth.getInstance();
        recipeDB = FirebaseFirestore.getInstance();
        algolia = new Algolia();
    }
}
