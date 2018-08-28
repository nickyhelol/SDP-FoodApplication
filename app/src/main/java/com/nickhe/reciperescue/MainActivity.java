package com.nickhe.reciperescue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static String accountPref = "accountPref";
    public static String emailPref = "emailPref";
    public static String passPref = "passPref";
    public static SharedPreferences sharedPreferences;

    public void login(View view)
    {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    public void signup(View view)
    {
        startActivity(new Intent(getApplicationContext(), SignupActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
