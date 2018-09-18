package com.nickhe.reciperescue;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{

    public HomeFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        String [] menuItems = {
                "Carbonara",
                "Spaghetti",
                "Mac and cheese",
                "Steak",
                "Bolognese",
                "Pizza",
                "Calzone",
                "Fried rice",
                "Chow mein"};

        ListView listView = (ListView) view.findViewById(R.id.homeListView);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.listview_layout,
                R.id.recipeName,
                menuItems
                //WE can change the 4th argument to be taken from the DB
        );

        listView.setAdapter(listViewAdapter);

        return view;
    }

}
