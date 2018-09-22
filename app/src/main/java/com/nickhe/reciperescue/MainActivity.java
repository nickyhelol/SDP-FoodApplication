package com.nickhe.reciperescue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * MainActivity is the menu the user would see after they started the application. It should have two
 * options:
 * a. Login button for logging in
 * b. Sign Up button for new users
 * <p>
 * Users are required to log in before accessing other features of the application.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The login method starts the MainLoginActivity activity and switches to it.
     *
     * @param view
     */
    public void login(View view) {
        startActivity(new Intent(getApplicationContext(), MainLoginActivity.class));
    }

    /**
     * The signUp method starts the RegisterToFirebaseActivity and switches to it.
     *
     * @param view
     */
    public void signUp(View view) {
        startActivity(new Intent(getApplicationContext(), RegisterToFirebaseActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
