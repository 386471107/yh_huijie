package com.example.yh_huijie.pages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yh_huijie.R;
import com.example.yh_huijie.adapter.InventoryRightMainAdapter;
import com.example.yh_huijie.data.model.ProductClassViewModel;
import com.example.yh_huijie.data.model.ProductDetailViewModel;
import com.example.yh_huijie.sqldata.ProductClass;
import com.example.yh_huijie.sqldata.ProductClassDao;
import com.example.yh_huijie.sqldata.ProductClassDatabase;
import com.example.yh_huijie.sqldata.ProductDetail;
import com.example.yh_huijie.sqldata.ProductDetailDao;
import com.example.yh_huijie.sqldata.ProductDetailDatabase;
import com.example.yh_huijie.sqldata.ProductList;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PageProductLocationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageProductLocationFragment extends Fragment {



    private RecyclerView mRvLeftMain,mRvRightMain;



    ProductClassDatabase productClassDatabase;
    ProductClassDao productClassDao;
    LiveData<List<ProductClass>> productClassLiveData;
    ProductClassViewModel productClassViewModel;



    ProductDetailDatabase productDetailDatabase;
    ProductDetailDao productDetailDao;
    LiveData<List<ProductDetail>> productDetailLiveData;
    ProductDetailViewModel productDetailViewModel;





    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private View rootView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment TestaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PageProductLocationFragment newInstance(String param1) {
        PageProductLocationFragment fragment = new PageProductLocationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView ==null)
        {
            rootView  = inflater.inflate(R.layout.fragment_page_product_location, container, false);
        }
        initView();
        return rootView;

    }

    private void initView() {
        TextView tv = rootView.findViewById(R.id.text1);
        tv.setText(mParam1);



        mRvRightMain= rootView.findViewById(R.id.recy_right);
        mRvRightMain.setLayoutManager(new LinearLayoutManager(getContext()));
        InventoryRightMainAdapter inventoryRightMainAdapter = new InventoryRightMainAdapter();
        mRvRightMain.setAdapter(inventoryRightMainAdapter);
        productDetailDatabase = Room.databaseBuilder(getContext(), ProductDetailDatabase.class,"product_detail").build();
        productDetailDao =productDetailDatabase.getProductDetaiDao();
        productDetailLiveData =productDetailDao.getAllLiveDataProducts();
        productDetailViewModel =new ViewModelProvider(getActivity()).get(ProductDetailViewModel.class);
//        productDetailViewModel.getAllProductDetailLive().observe(getViewLifecycleOwner(), new Observer<List<ProductList>>() {
//            public void onChanged(List<ProductList> productLists) {
//                inventoryRightMainAdapter.setData(productLists);
//                inventoryRightMainAdapter.notifyDataSetChanged();
//            }
//        });




    }
}