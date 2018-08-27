package com.nickhe.reciperescue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void login(View view)
    {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

        startActivity(intent);
    }

    public void signup(View view)
    {
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);

        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
