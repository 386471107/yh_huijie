package com.example.yh_huijie.action;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.yh_huijie.R;
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
 * Use the {@link TestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View rootView;

    ProductClassDatabase productClassDatabase;
    ProductClassDao productClassDao;

    ProductDetailDatabase productDetailDatabase;
    ProductDetailDao productDetailDao;
    Button button,button6,button7,button8;
    TextView tv2;
    LiveData<List<ProductClass>> productClassLiveData;


    LiveData<List<ProductDetail>> productDetailLiveData;
    ProductClassViewModel productClassViewModel;


    ProductDetailViewModel productDetailViewModel;
    public TestFragment() {
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
    public static TestFragment newInstance(String param1, String param2) {
        TestFragment fragment = new TestFragment();
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
            rootView  = inflater.inflate(R.layout.fragment_test, container, false);
        }
        initView();
        return rootView;
    }
    private void initView() {
        productClassDatabase= Room.databaseBuilder(getContext(),ProductClassDatabase.class,"product_class").build();
        productClassDao = productClassDatabase.getProductClassDao();


        productDetailDatabase =Room.databaseBuilder(getContext(), ProductDetailDatabase.class,"product_detail").build();
        productDetailDao =productDetailDatabase.getProductDetaiDao();


        button = rootView.findViewById(R.id.button);
        button6 = rootView.findViewById(R.id.button6);
        button7 = rootView.findViewById(R.id.button7);
        button8 = rootView.findViewById(R.id.button8);

        productClassLiveData = productClassDao.getAllLiveDataProductClass();
        productClassViewModel = new ViewModelProvider(getActivity()).get(ProductClassViewModel.class);

        productDetailLiveData = productDetailDao.getAllLiveDataProducts();

        productDetailViewModel = new ViewModelProvider(getActivity()).get(ProductDetailViewModel.class);


        productClassViewModel.getAllProductClassLive().observe(getViewLifecycleOwner(), new Observer<List<ProductClass>>() {
            @Override
            public void onChanged(List<ProductClass> productClasses) {
                TextView tv2;
                tv2 = rootView.findViewById(R.id.taxtView2);
                String texts="";
                for (int i = 0;i<productClasses.size();i++)
                {
                    ProductClass productClass = productClasses.get(i);
                    texts +=productClass.getId()+" "+productClass.getClass_name()+" "+productClass.getClass_order()+"\n";
                }
                tv2.setText(texts);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProductClass productClass1 =new ProductClass(1,"1",1,1,true,1,1);
                ProductClass productClass2 =new ProductClass(2,"2",2,2,true,1,1);
                ProductClass productClass3 =new ProductClass(3,"3",3,3,true,1,1);
                ProductClass productClass4 =new ProductClass(3,"4",3,3,true,1,1);
                ProductClass productClass5 =new ProductClass(3,"5",3,3,true,1,1);
                productClassViewModel.insertProductClass(productClass1,productClass2,productClass3,productClass4,productClass5);

//                ProductDetail productDetail1 =new ProductDetail(1,"测试类名1",1,"商品名称1","123455","1234213","1","A1B1",1.11,1.25,"11",1,1,true);
//                ProductDetail productDetail2 =new ProductDetail(1,"测试类名2",1,"商品名称2","123455","1234213","1","A1B1",1.11,1.25,"11",1,1,true);
//                ProductDetail productDetail3 =new ProductDetail(1,"测试类名3",1,"商品名称3","123455","1234213","1","A1B1",1.11,1.25,"11",1,1,true);
//
//                productDetailViewModel.insertProductDetail(productDetail1,productDetail2,productDetail3);


            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productClassViewModel.deleteProductClass();

            }
        });
    }






}