package com.nickhe.reciperescue;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.algolia.search.saas.AlgoliaException;
import com.algolia.search.saas.CompletionHandler;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * CreateRecipeActivity class
 * An activity that users of the application can use to upload a recipe to the database.
 * This has its own view and takes the recipe fields and uploads it, including the recipe image.
 * <p>
 * Uploads to both Firebase and Algolia
 */
public class CreateRecipeActivity extends AppCompatActivity {

    public final int PICK_IMAGE_RESULT = 1;
    private FirebaseFirestore recipeDB;
    private FirebaseAuth firebaseAuth;
    private HashMap<String, Object> recipeSubmitFireStore = new HashMap();
    private JSONObject recipeSubmit;
    private Algolia algolia;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private StorageReference imageStorageReference;
    private ImageView recipeAddImageView;
    private Uri imagePath;
    private String recipeID;

    public void submitRecipe(View view) throws JSONException {

        getRecipeFields(view);

        recipeDB.collection("recipes")
                .add(recipeSubmitFireStore)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        algolia.index.addObjectAsync(recipeSubmit, new CompletionHandler() {
                            @Override
                            public void requestCompleted(JSONObject jsonObject, AlgoliaException e) {
                                recipeID = jsonObject.optString("objectID");
                                imageStorageReference = storageReference.child("recipes").child(recipeID);

                                // Check if recipe image is null
                                if (imagePath == null) {
                                    imagePath = Uri.parse("android.resource://com.nickhe.reciperescue/drawable/no_picture.png"); // Sets to default no_picture image
                                }

                                UploadTask uploadTask = imageStorageReference.putFile(imagePath); //Uploads image to Firebase storage
                                uploadTask.addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getBaseContext(), "Recipe pic uploading failed", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Toast.makeText(getBaseContext(), "Recipe pic uploading completed", Toast.LENGTH_SHORT).show();

                                        // Takes the download link and stores it in the database
                                        Task<Uri> downloadLinkTask = taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                Uri downloadLink = uri;
                                                try {
                                                    recipeSubmit.put("recipeImage", downloadLink);
                                                    algolia.index.addObjectAsync(recipeSubmit, recipeID, new CompletionHandler() {
                                                        @Override
                                                        public void requestCompleted(JSONObject jsonObject, AlgoliaException e) {
                                                            Toast.makeText(getBaseContext(), "Recipe image link updated", Toast.LENGTH_LONG);
                                                        }
                                                    });
                                                } catch (JSONException e1) {
                                                    e1.printStackTrace();
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        });
                        Toast.makeText(getApplicationContext(), "DocumentSnapshot written with ID: " + documentReference.getId(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error adding document" + e, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * getRecipeFields method retrieves the fields needed for the recipe object creation, this does
     * not include the image used for the recipe.
     *
     * @param view
     * @throws JSONException
     */
    private void getRecipeFields(View view) throws JSONException {
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

        recipeSubmitFireStore.put("recipeTitle", recipeTitle);
        recipeSubmitFireStore.put("recipeIngredients", ingredientsList);
        recipeSubmitFireStore.put("recipeInstruction", instructionsList);
        recipeSubmitFireStore.put("calories", calories);
        recipeSubmitFireStore.put("time", time);
        recipeSubmitFireStore.put("recipePublisher", firebaseAuth.getCurrentUser().getUid());

        recipeSubmit.put("recipeTitle", recipeTitle);
        recipeSubmit.put("recipeIngredients", ingredientsList);
        recipeSubmit.put("recipeInstruction", instructionsList);
        recipeSubmit.put("calories", calories);
        recipeSubmit.put("time", time);
        recipeSubmit.put("recipePublisher", firebaseAuth.getCurrentUser().getUid());
    }

    /**
     * Adds an extra ingredient field to the view
     *
     * @param view
     */
    public void addIngredient(View view) {
        LinearLayout layout = findViewById(R.id.recipeIngredientsInputLayout);
        EditText text = new EditText(getApplicationContext());
        text.setHint("Recipe Ingredient");
        layout.addView(text);
    }

    /**
     * Adds an extra instruction field to the view
     *
     * @param view
     */
    public void addInstruction(View view) {
        LinearLayout layout = findViewById(R.id.recipeInstructionsInputLayout);
        EditText text = new EditText(getApplicationContext());
        text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
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
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        recipeAddImageView = findViewById(R.id.recipeAddImageView);
        recipeAddImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICK_IMAGE_RESULT:
                if (resultCode == Activity.RESULT_OK) {
                    imagePath = data.getData();

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver()
                            .query(imagePath, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    recipeAddImageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                }

        }
    }
}
