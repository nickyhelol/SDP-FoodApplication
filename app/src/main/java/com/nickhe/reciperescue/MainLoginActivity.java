package com.nickhe.reciperescue;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This activity is the main login activity where user can provide email and password to login into the application
 *
 */
public class MainLoginActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText userPassword;
    private TextView screenInfo;
    private Button loginBtn;
    private int counter = 5;
    private TextView userRegView;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPwView;
    private TextView errorInfoView;
    SignInButton googleSignInButton;
    GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 9001;
    FirebaseAuth.AuthStateListener mAuthLis;


    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(mAuthLis);

    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_login);
        initializeViews();

        screenInfo.setText("No. of attempts remaining: 5");
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);

        mAuthLis = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() !=null)
                {
                    startActivity(new Intent(MainLoginActivity.this, MainMenuActivity.class));
                }
            }
        };

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user!=null){
            finish();//meaning if there is no user then it will stay at the main activity and have to enter sign in details again.
            startActivity(new Intent(MainLoginActivity.this,MainMenuActivity.class));
        }


        //providing onclick function for login button
        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                validate(userEmail.getText().toString(), userPassword.getText().toString());

            }

        });
        userRegView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainLoginActivity.this, RegisterToFirebaseActivity.class));
            }
        });

        //providing onclick function for forgot password text view
        forgotPwView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainLoginActivity.this, ForgotPasswordActivity.class));
            }
        });

        googleSignInButton = (SignInButton) findViewById(R.id.googleBtn);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


    }



    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(String.valueOf(MainLoginActivity.this), "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {



        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());


                        }

                        // ...
                    }
                });

    }


    /**
     * This method will initialize all the variables and upon its call,
     * it will show all the views to the screen
     */
    private void initializeViews(){
        userEmail = (EditText) findViewById(R.id.userNameField);

        userPassword = (EditText) findViewById(R.id.userPasswordField);

        screenInfo = (TextView) findViewById(R.id.attemptsLoginInfo);

        loginBtn = (Button) findViewById(R.id.loginButton);
        userRegView = (TextView) findViewById(R.id.RegTextView);
        forgotPwView= (TextView) findViewById(R.id.forgotPwTV);
        errorInfoView= findViewById(R.id.infoView);
    }


    /**
     * This method will validate if the given name and the password are valid and if they are valid then it will let
     * user to enter to new window otherwise it will send error message
     * @param userEmail
     * @param userPassword
     */

    private void validate(String userEmail, String userPassword) {

        if(userEmail.isEmpty()){
            errorInfoView.setText("Email address required");
        }

       else if (!isEmailValid(userEmail)){
            errorInfoView.setText("Please enter valid email address");
        }
        else if(userPassword.isEmpty()){
            errorInfoView.setText("Password required");

        }
        else if(!isPasswordValid(userPassword)){
            errorInfoView.setText("Password must be at least 6 character");
        }
        else {

            errorInfoView.setText("");
            progressDialog.setMessage("Logging in ");
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();

                        checkEmailVerification();
                    } else {
                        progressDialog.cancel();
                        errorInfoView.setText("Wrong user name or password");
                        Toast.makeText(MainLoginActivity.this, "Wrong user name or password", Toast.LENGTH_SHORT).show();
                        counter--;
                        screenInfo.setText("No of attempts remaining: " + counter);
                        if (counter == 0) {
                            loginBtn.setEnabled(false);
                        }
                    }
                }

            });
        }
    }

    /**
     * This method will check the email provided by the user during loging in and if the email
     * is correct then it will let user enter to new window otherwise it will sign out from the firebase
     * and ask user to provide correct email address.
     */
    private void checkEmailVerification(){
        FirebaseUser firebaseUser= firebaseAuth.getInstance().getCurrentUser();
        Boolean flag= firebaseUser.isEmailVerified();


        //if email is verified then link this to the second activity
        if(flag){
            finish();//finishes this main activity and directs it to the second activity.
            startActivity(new Intent(MainLoginActivity.this, MainMenuActivity.class));
        }else{//if the email is not verified then send a toast message to user and sign out from the firebase
            Toast.makeText(this, "Please verify your email", Toast.LENGTH_SHORT).show();
            //Need to sign out until user provides the valid email address.
            firebaseAuth.signOut();
        }
    }

    /**
     * This method check if the given email address is valid or not.
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
     * @param password password
     * @return true if the password is valid and false otherwise
     */
    public static boolean isPasswordValid(String password){
        return password.length() >= 6;
    }




}
