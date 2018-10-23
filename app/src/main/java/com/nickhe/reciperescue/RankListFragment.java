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
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RankListFragment extends Fragment {

    private View view;
    private ListView listView;
    FakeUserRepository fakeUserRepository;

    public RankListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rank_list, container, false);

        return view;
    }

    private void initialize(){
        fakeUserRepository = FakeUserRepository.getFakeUserRepository(getActivity());
        listView = view.findViewById(R.id.rankList_RankList);
        UserListAdapter userListAdapter = new UserListAdapter(getActivity(), fakeUserRepository.getFakeRepo());
        listView.setAdapter(userListAdapter);
        ListViewProcessor.setListViewHeightBasedOnChildren(listView);
    }

    private void setListViewOnClickListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                User user = fakeUserRepository.getFakeRepo().get(position);

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize();
        setListViewOnClickListener();

    }

}
