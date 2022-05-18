package com.example.yh_huijie.action;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.yh_huijie.R;
import com.example.yh_huijie.adapter.BLEListAdapter;
import com.example.yh_huijie.adapter.InventoryLeftMainAdapter;
import com.example.yh_huijie.adapter.InventoryRightMainAdapter;
import com.example.yh_huijie.data.model.ProductClassViewModel;
import com.example.yh_huijie.data.model.ProductDetailViewModel;
import com.example.yh_huijie.sqldata.ProductClass;
import com.example.yh_huijie.sqldata.ProductClassDao;
import com.example.yh_huijie.sqldata.ProductClassDatabase;
import com.example.yh_huijie.sqldata.ProductDetail;
import com.example.yh_huijie.sqldata.ProductDetailDao;
import com.example.yh_huijie.sqldata.ProductDetailDatabase;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BLESearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BLESearchFragment extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mRvLeftMain,mRvRightMain;



    ProductClassDatabase productClassDatabase;
    ProductClassDao productClassDao;
    LiveData<List<ProductClass>> productClassLiveData;
    ProductClassViewModel productClassViewModel;



    ProductDetailDatabase productDetailDatabase;
    ProductDetailDao productDetailDao;
    LiveData<List<ProductDetail>> productDetailLiveData;
    ProductDetailViewModel productDetailViewModel;
    View rootView;


    public BLESearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BLESearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BLESearchFragment newInstance(String param1, String param2) {
        BLESearchFragment fragment = new BLESearchFragment();
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
            rootView  = inflater.inflate(R.layout.fragment_ble_search, container, false);
        }
        initView();
        return rootView;


    }

    private void initView() {



        mRvLeftMain = rootView.findViewById(R.id.recyr_left);
        mRvRightMain= rootView.findViewById(R.id.recy_right);
        mRvLeftMain.setLayoutManager(new LinearLayoutManager(getContext()));
        BLEListAdapter blelistadapter = new BLEListAdapter();
        mRvLeftMain.setAdapter(blelistadapter);

        mRvRightMain.setLayoutManager(new LinearLayoutManager(getContext()));

        mRvRightMain.setAdapter(blelistadapter);


        productClassDatabase= Room.databaseBuilder(getContext(), ProductClassDatabase.class,"product_class").build();
        productClassDao = productClassDatabase.getProductClassDao();
        productClassLiveData = productClassDao.getAllLiveDataProductClass();
        productClassViewModel = new ViewModelProvider(getActivity()).get(ProductClassViewModel.class);
        productClassViewModel.getAllProductClassLive().observe(getViewLifecycleOwner(), new Observer<List<ProductClass>>() {
            @Override
            public void onChanged(List<ProductClass> productClasses) {
                blelistadapter.setData(productClasses);
                blelistadapter.notifyDataSetChanged();
            }
        });



        productDetailDatabase = Room.databaseBuilder(getContext(), ProductDetailDatabase.class,"product_detail").build();

        productDetailDao =productDetailDatabase.getProductDetaiDao();
        productDetailLiveData =productDetailDao.getAllLiveDataProducts();
        productDetailViewModel =new ViewModelProvider(getActivity()).get(ProductDetailViewModel.class);






    }
}
