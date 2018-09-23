package com.nickhe.reciperescue;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

/**
 * This activity is used to retrieve user information from database to the application screen.
 *
 * TODO user image retrieve from database
 */
public class UserProfileActivity extends AppCompatActivity {
    private ImageView profilePicture;
    private TextView pName,pAge, pEmail;
    private Button profileBtn,updatePwBtn;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        initializeViews();

        //showing the arrow at the top of the screen
        getActionBar().setDisplayHomeAsUpEnabled(true);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        //getting the Uid from database
        DatabaseReference databaseReference= firebaseDatabase.getReference(firebaseAuth.getUid());

        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(firebaseAuth.getUid()).child("Images/Profile Picture").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(profilePicture);
            }
        });

        //add the event listener (Look at the firebase documentation for details)
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //we need to provide a class which is object, we will use user object.
                //data snapshot will retreive the value from the database and store it into the class
                //which can again be assigned to the textView to show in the screen
                User user= dataSnapshot.getValue(User.class);
                pName.setText("Name of User: "+user.getName());
                pAge.setText("Age of User: "+user.getAge());
                pEmail.setText("Email of User: "+user.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Provide toast message in case there is an error in retrieving the data from the database.
                Toast.makeText(UserProfileActivity.this, databaseError.getCode(),Toast.LENGTH_SHORT).show();

            }
        });

        //adding onclick listener for edit button
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfileActivity.this, UpdateUserProfileActivity.class));
            }
        });

        //adding onclick listener for change password button
        updatePwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfileActivity.this,PasswordUpdate.class));
            }
        });
    }

    private void initializeViews(){

        pName= findViewById(R.id.profileNameTV);
        pAge=findViewById(R.id.profileAgeTV);
        pEmail=findViewById(R.id.profileEmailTV);
        profileBtn= findViewById(R.id.profileUpdateBtn);
        updatePwBtn=findViewById(R.id.updatePwBtn);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }


        return super.onOptionsItemSelected(item);
    }
}
