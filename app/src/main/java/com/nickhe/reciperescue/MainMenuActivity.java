package com.nickhe.reciperescue;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

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

        firebaseAuth = FirebaseAuth.getInstance();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        profileFragment = new ProfileFragment();
        rankListFragment = new RankListFragment();

        setFragment(homeFragment);

        //Set ActionListener for the BottomNavigationView
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

    /**
     * Return the bottomNavigationView
     *
     * @return
     */
    public BottomNavigationView getBottomNavigationView() {
        return bottomNavigationView;
    }

    /**
     * Replace the specific fragment with the contentLayout
     *
     * @param fragment
     */
    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout, fragment).commit();
    }

    /**
     * This method will log out the user from the firebase and finish the current activity which is main menu activity and
     * goes back to the main login activity once log out menu is clicked.
     */
    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(MainMenuActivity.this, MainLoginActivity.class));
    }

    /**
     * Enable the option menu in MainMenuActivity
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * The action when option selected in the optionMenu
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutMenu:
                Logout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
