package com.nickhe.reciperescue;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * This activity is intended to provide facility to user so as to update their profile, name, age, email
 *
 * TODO adding user image to database
 * */
public class UpdateUserProfileActivity extends AppCompatActivity {

    private EditText updateName, updateEmail, updateAge;
    private Button updateButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private ImageView updateImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_profile);

        initializeViews();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        //getting the Uid from database
        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        //add the event listener (Look at the firebase documentation for details)
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //we need to provide a class which is object, we will use user object.
                //data snapshot will retreive the value from the database and store it into the class
                //which can again be assigned to the textView to show in the screen
                User user = dataSnapshot.getValue(User.class);
                updateName.setText(user.getName());
                updateAge.setText(user.getAge());
                updateEmail.setText(user.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Provide toast message in case there is an error in retrieving the data from the database.
                Toast.makeText(UpdateUserProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });

        //adding listener to save button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //retrieving the data from the screen to update in the database
                String userName = updateName.getText().toString();
                String userAge = updateAge.getText().toString();
                String userEmail = updateEmail.getText().toString();

                User user = new User(userName, userAge, userEmail);
                databaseReference.setValue(user);

                //finish this activity
                finish();
            }
        });
    }

    /**
     * This method initialize the variables.
     */
    private void initializeViews() {

        updateName = findViewById(R.id.updateNameET);
        updateAge = findViewById(R.id.updateAgeET);
        updateEmail = findViewById(R.id.updateEmailET);
        updateButton = findViewById(R.id.updateInfoBtn);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }


        return super.onOptionsItemSelected(item);
    }
}