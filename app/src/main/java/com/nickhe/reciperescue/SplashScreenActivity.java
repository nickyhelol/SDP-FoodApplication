package com.nickhe.reciperescue;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreenActivity extends Activity {

    private FirebaseAuth firebaseAuth;
    private Button logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        firebaseAuth= FirebaseAuth.getInstance();
        logoutBtn = (Button) findViewById(R.id.btnLogout);


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
    }

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SplashScreenActivity.this,MainLoginActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logoutMenu:
                Logout();
                break;
            //This will direct to the userProfile activity where we can see the attributes(name, age, and email of the user
            //retrieved from the firebase database. This means when user clicks to profile menu item then it will directs to another screen.
            case R.id.ProfileMenu:
                startActivity(new Intent(SplashScreenActivity.this, UserProfileActivity.class));
                break;

            case android.R.id.home:
                onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
