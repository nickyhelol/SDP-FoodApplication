package com.nickhe.reciperescue;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private ProfileFragment profileFragment;
    private RankListFragment rankListFragment;
    private RecipeListFragment recipeListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        profileFragment = new ProfileFragment();
        rankListFragment = new RankListFragment();
        recipeListFragment = new RecipeListFragment();


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

    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout, fragment).commit();
    }

}
