package com.nickhe.reciperescue;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This activity is intended to register user to the database, They need to provide email, age, and password to register to the database
 * Once they enter their details, they need to verify their email address by clicking on the email sent by the database of recipe rescue
 * application. They can only login after verifying their email.
 */
public class RegisterToFirebaseActivity extends AppCompatActivity {


    private EditText name, password, email, ageOfUser;
    private Button registerButton;
    private TextView userLoginView;
    private FirebaseAuth firebaseAuth;
    private String userName, userPassword, userEmail, userAge;
    //adding firebase storage object
    private FirebaseStorage firebaseStorage;
    private TextView errorTextView;

    /**
     * This method check if the given email address is valid or not.
     *
     * @param email email address
     * @return true if the email is valid and false otherwise
     */
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * This method checks if the given password is valid or not.
     *
     * @param password password
     * @return true if the password is valid and false otherwise
     */
    public static boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_to_firebase);
        initializeViews();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //first we need to validate if user has entered all fields required to register.
                if (validate()) {
                    //upload data to the database.
                    String user_email = email.getText().toString().trim();
                    String user_password = password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //calling method
                                verifyEmail();
                                sendUserDataToDatabase();
                                //when user registers to the database, it signs in as well in firebase so we need to sign out user
                                //to prevent from going to other activity than main activity. And the user will be null.
                                firebaseAuth.signOut();
                                Toast.makeText(RegisterToFirebaseActivity.this, "Successfully Registered, Upload completed", Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(RegisterToFirebaseActivity.this, MainLoginActivity.class));
                            } else {
                                Toast.makeText(RegisterToFirebaseActivity.this, "Registration failed, User name already exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        userLoginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterToFirebaseActivity.this, MainLoginActivity.class));
            }
        });

        /**
         * adding on click listener for the image button so that it can direct user to their gallery to choose pic
         * so that they can upload it to their profile.
         */


    }

    /**
     * This method initialize the variables.
     */
    private void initializeViews() {
        name = (EditText) findViewById(R.id.updateNameET);
        password = (EditText) findViewById(R.id.userPasswordField);
        email = (EditText) findViewById(R.id.updateEmailET);
        registerButton = (Button) findViewById(R.id.registerButton);
        userLoginView = (TextView) findViewById(R.id.userLoginView);
        ageOfUser = (EditText) findViewById(R.id.updateAgeET);
        errorTextView = findViewById(R.id.errorDetailsView);


    }

    /**
     * this method will validate if user has entered all fielsd required to register in the database.
     */
    private Boolean validate() {
        Boolean result = false;
        userName = this.name.getText().toString();
        userPassword = this.password.getText().toString();
        userEmail = this.email.getText().toString();
        userAge = ageOfUser.getText().toString();

        if (userName.isEmpty() || userPassword.isEmpty() || userEmail.isEmpty() || userAge.isEmpty()) {
            errorTextView.setText("Please enter all the details");
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else if (!(isEmailValid(userEmail))) {
            errorTextView.setText("Please provide valid email");

        } else if (!isPasswordValid(userPassword)) {
            errorTextView.setText("Password must be at least 6 character");
        } else {
            errorTextView.setText("");
            result = true;
        }

        return result;
    }

    /**
     * Verifies the email provided while registering to the database
     * Sends the verification email to the user to verify the email.
     */
    private void verifyEmail() {
        FirebaseUser user = firebaseAuth.getCurrentUser();//gets current user

        if (user != null) {//
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {//if the task is successfull, show the display message
                        //calling method

                        // sendUserDataToDatabase();
                        Toast.makeText(RegisterToFirebaseActivity.this, "Successfully Registered, Verification mail sent", Toast.LENGTH_LONG).show();
                        firebaseAuth.signOut();//once the user has created firebase username and password, user is actually
                        //signed in into the firebase so we need to sign them out. we don't want user to enter the app without logging
                        //in into the firebase.
                        finish();
                        startActivity(new Intent(RegisterToFirebaseActivity.this, MainLoginActivity.class));
                    } else {
                        Toast.makeText(RegisterToFirebaseActivity.this, "Verification not sent", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * This method creates the database reference per user and sends it to the firebase database.
     */
    private void sendUserDataToDatabase() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());//getting the UID of the user from the firebase console.

<<<<<<< HEAD

        User user= new User(userName, userAge, userEmail);



=======
        //Creating user object
        User user = new User(userName, userAge, userEmail);
>>>>>>> master

        //since the database reference need an object of class, we created the object of user class, and assigned the value
        //as per the constructor to pass into the database reference.
        databaseReference.setValue(user);
    }


}
