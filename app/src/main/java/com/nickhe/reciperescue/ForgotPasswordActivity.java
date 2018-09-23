package com.nickhe.reciperescue;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
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


public class ForgotPasswordActivity extends Activity {

    private EditText emailView;
    private Button pwResetBtn;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setUpView();

        firebaseAuth= FirebaseAuth.getInstance();//intantiating the firebase object
        //setting on click listener to the password reset button.
        pwResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= emailView.getText().toString().trim();

                if(email.isEmpty()){
                    Toast.makeText(ForgotPasswordActivity.this,"Please enter valid and registered Email ID", Toast.LENGTH_LONG).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ForgotPasswordActivity.this, "Reset password email sent", Toast.LENGTH_LONG).show();
                                //Finishing this activity upon successful task
                                finish();
                                //starting main activity for login after reset link has been sent to user email.
                                startActivity(new Intent(ForgotPasswordActivity.this,MainLoginActivity.class));
                            }else{//if the user email is not registered in firebase, then it will print this error message.
                                Toast.makeText(ForgotPasswordActivity.this, "Error sending password reset email", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });
    }

    private void setUpView(){
        emailView=(EditText) findViewById(R.id.emailTextView);
        pwResetBtn= (Button) findViewById(R.id.passwordResetBtn);
    }

}
