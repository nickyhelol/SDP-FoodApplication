package com.nickhe.reciperescue;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
=======
import android.widget.ListAdapter;
import android.widget.ListView;
>>>>>>> master



/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ListView listView;
    FakeRecipeRepository fakeRecipeRepository;
    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

<<<<<<< HEAD
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
=======
        listView = view.findViewById(R.id.home_recipeList);
        fakeRecipeRepository = FakeRecipeRepository.getFakeRecipeRepository(getActivity());
        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(getActivity(), fakeRecipeRepository.getFakeRepo());
        listView.setAdapter(recipeListAdapter);
        setListViewHeightBasedOnChildren(listView);
>>>>>>> master
    }

    /**
     * Make sure the listView will be set by the correct height based on
     * the number of the items it has.
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
