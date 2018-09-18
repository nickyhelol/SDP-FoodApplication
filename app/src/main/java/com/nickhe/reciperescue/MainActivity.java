package com.nickhe.reciperescue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
//Test comment
public class MainActivity extends AppCompatActivity {

    /*public static String accountPref = "accountPref";
    public static String emailPref = "emailPref";
    public static String passPref = "passPref";
    public static SharedPreferences sharedPreferences;
    private DrawerLayout mDrawerLayout;*/

    public void login(View view)
    {
        startActivity(new Intent(getApplicationContext(), MainLoginActivity.class));
    }

    public void signup(View view)
    {
        startActivity(new Intent(getApplicationContext(), RegisterToFirebaseActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
