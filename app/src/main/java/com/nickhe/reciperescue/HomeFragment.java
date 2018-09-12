package com.nickhe.reciperescue;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
private FirebaseAuth firebaseAuth;
private Button logOutBtn;


    public HomeFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mview= inflater.inflate(R.layout.fragment_home,container,false);
        logOutBtn= mview.findViewById(R.id.buttonLog);
        this.firebaseAuth= FirebaseAuth.getInstance();



        // Inflate the layout for this fragment
        return mview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                firebaseAuth.signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(),MainLoginActivity.class));

                Toast.makeText(getActivity(),"log out clicked", Toast.LENGTH_LONG).show();
            }
        });
    }

}
