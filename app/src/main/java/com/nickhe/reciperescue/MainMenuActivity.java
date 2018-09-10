package com.nickhe.reciperescue;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainMenuActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private ProfileFragment profileFragment;
    private RankListFragment rankListFragment;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        firebaseAuth= FirebaseAuth.getInstance();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        profileFragment = new ProfileFragment();
        rankListFragment = new RankListFragment();

        setFragment(homeFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_navigation: {
                        setFragment(homeFragment);
                        return true;
                    }
                    case R.id.search_navigation: {
                        setFragment(searchFragment);
                        return true;
                    }
                    case R.id.profile_navigation: {
                        setFragment(profileFragment);
                        return true;
                    }
                    case R.id.rankList_navigation: {
                        setFragment(rankListFragment);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public void setFragment(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout, fragment).commit();
    }

    /**
     * This method will log out the user from the firebase and finish the current activity which is main menu activity and
     * goes back to the main login activity once log out menu is clicked.
     */
    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(MainMenuActivity.this,MainLoginActivity.class));
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
                startActivity(new Intent(MainMenuActivity.this, UserProfileActivity.class));
                break;



        }
        return super.onOptionsItemSelected(item);
    }

}
