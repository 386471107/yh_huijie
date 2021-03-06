package com.example.yh_huijie.action;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yh_huijie.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View rootView;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (rootView ==null)
        {
            rootView  = inflater.inflate(R.layout.activity_search, container, false);
        }
        initView();
        return rootView;
    }

    private void initView() {
        ViewPager2 viewpager = rootView.findViewById(R.id.viewpager2);
        ArrayList<Fragment> fragments =new ArrayList<>();
        List<String> titles = new ArrayList<>();
        fragments.add(TestaFragment.newInstance("??????1"));
        fragments.add(TestbFragment.newInstance("??????2"));
        fragments.add(TestaFragment.newInstance("??????3"));
        fragments.add(TestaFragment.newInstance("??????4"));
        titles.add("??????1");
        titles.add("??????2");
        titles.add("??????3");
        titles.add("??????4");
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getParentFragmentManager(),getLifecycle(),fragments);
        viewpager.setAdapter(viewPageAdapter);


        TabLayout tabLayout = rootView.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewpager,
                (tab, position) -> tab.setText(titles.get(position))
        ).attach();





    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}