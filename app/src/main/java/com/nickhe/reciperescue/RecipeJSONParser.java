package com.nickhe.reciperescue;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONObject;

public class RecipeJSONParser {
    private StorageReference firebaseStorage;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private StorageReference imageReference;

    public RecipeJSONParser() {
        firebaseStorage = FirebaseStorage.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public Recipe parse(JSONObject jsonObject) {
        String recipeTitle = jsonObject.optString("recipeTitle");
        String[] recipeIngredients = jsonObject.optString("recipeIngredients").split(",");
        String time = jsonObject.optString("time");
        String calories = jsonObject.optString("calories");
        String[] recipeInstruction = jsonObject.optString("recipeInstruction").split(",");
        String objectID = jsonObject.optString("objectID");
        String recipePublisherID = jsonObject.optString("recipePublisher");
        final String[] recipePublisherName = new String[1];
        Uri recipeImage = Uri.parse(jsonObject.optString("recipeImage"));

        databaseReference = firebaseDatabase.getReference(recipePublisherID);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                recipePublisherName[0] = user.getName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Recipe recipe = new Recipe(recipeTitle, recipeIngredients, recipePublisherName[0], time, calories, recipeInstruction, recipeImage);

        return recipe;
    }
}
