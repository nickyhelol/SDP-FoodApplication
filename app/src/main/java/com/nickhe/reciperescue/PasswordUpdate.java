package com.nickhe.reciperescue;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * This activity is intended for the password update functionality
 * If user wants to update (change) password, they can do it from this activity, it will update the password in the firebase.
 */
public class PasswordUpdate extends Activity {

    private Button updatePasswordButton;
    private EditText updatedPassword;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_update);

        updatePasswordButton= findViewById(R.id.updatePasswordBtn);
        updatedPassword = findViewById(R.id.newPasswordText);

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        getActionBar().setDisplayHomeAsUpEnabled(true);
        //adding onclick listener for update password Button
        updatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating new variable for password String
                String newPw= updatedPassword.getText().toString();
                firebaseUser.updatePassword(newPw).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            //display a toast message that password is updated and password is changed
                            Toast.makeText(PasswordUpdate.this,"Password Updated", Toast.LENGTH_SHORT).show();
                            //finish this activity
                            finish();
                        }else{
                            Toast.makeText(PasswordUpdate.this,"Password Updating failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //adding override method
    //when clicked on the back arrow on top of this page, it will lead to the previous activity.

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }


        return super.onOptionsItemSelected(item);
    }
}
