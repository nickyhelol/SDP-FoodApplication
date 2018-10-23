package com.nickhe.reciperescue;

import android.net.Uri;
import android.os.Parcel;
import android.support.annotation.Nullable;
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
import com.google.android.gms.internal.firebase_auth.zzap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;

import java.util.List;
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
    private final static int RC_SIGN_IN = 2;
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

        firebaseAuth = FirebaseAuth.getInstance();

        screenInfo.setText("No. of attempts remaining: 5");
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);

        mAuthLis = new FirebaseAuth.AuthStateListener() {
          @Override
          public void onAuthStateChanged(FirebaseAuth firebaseAuth)
          {
              if(firebaseAuth.getCurrentUser() != null)
              {
                  startActivity(new Intent(MainLoginActivity.this, MainMenuActivity.class));
              }
          }
        };


        FirebaseUser user = firebaseAuth.getCurrentUser();

        /*
        if(user!=null){
            finish();//meaning if there is no user then it will stay at the main activity and have to enter sign in details again.
            startActivity(new Intent(MainLoginActivity.this,MainMenuActivity.class));
        }
    */

        //providing onclick function for login button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                validate(userEmail.getText().toString(), userPassword.getText().toString());

                int i = view.getId();
                if(i == R.id.googleBtn)
                {
                    signIn();
                }

            }

        });
        userRegView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
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
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        firebaseAuth = FirebaseAuth.getInstance();

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


/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                Toast.makeText(MainLoginActivity.this, "Working?", Toast.LENGTH_LONG).show();
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(String.valueOf(MainLoginActivity.this), "Google sign in failed", e);
                Toast.makeText(MainLoginActivity.this, "Google sign in failed", Toast.LENGTH_LONG).show();
            }
        }
    }
*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                Toast.makeText(MainLoginActivity.this, "Working?", Toast.LENGTH_SHORT).show();
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
                Toast.makeText(MainLoginActivity.this, "Not Working?", Toast.LENGTH_SHORT).show();
                // [START_EXCLUDE]
                //updateUI(null);
                // [END_EXCLUDE]
            }
        }
    }

    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    */


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Toast.makeText(MainLoginActivity.this, "Working 2?", Toast.LENGTH_LONG).show();
            signIn();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(MainLoginActivity.this, "Not Working 2?", Toast.LENGTH_LONG).show();
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());

        }
    }


    /*
    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        ///Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                        } else {
                            Toast.makeText(MainLoginActivity.this, "Not working?", Toast.LENGTH_LONG).show();
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });


    }
    */

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        //showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAH", "signInWithCredential:success");
                            Toast.makeText(MainLoginActivity.this, "Working 3?", Toast.LENGTH_LONG).show();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            if(user != null)
                            {
                                startActivity(new Intent(MainLoginActivity.this, MainMenuActivity.class));
                            }
                            //updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Snackbar.make(findViewById(R.id.userLoginView), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            Toast.makeText(MainLoginActivity.this, "Not Working 3?", Toast.LENGTH_LONG).show();
                            //updateUI(null);
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
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
