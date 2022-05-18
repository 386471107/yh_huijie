package com.example.yh_huijie.action;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.ColumnInfo;

import com.example.yh_huijie.R;
import com.example.yh_huijie.adapter.InventoryLeftMainAdapter;
import com.example.yh_huijie.adapter.InventoryRightMainAdapter;
import com.example.yh_huijie.data.model.ProductClassViewModel;
import com.example.yh_huijie.data.model.ProductDetailViewModel;
import com.example.yh_huijie.data.model.ProductListViewModel;
import com.example.yh_huijie.sqldata.ProductClass;
import com.example.yh_huijie.sqldata.ProductClassDao;
import com.example.yh_huijie.sqldata.ProductClassDatabase;
import com.example.yh_huijie.sqldata.ProductDetail;
import com.example.yh_huijie.sqldata.ProductDetailDao;
import com.example.yh_huijie.sqldata.ProductDetailDatabase;
import com.example.yh_huijie.sqldata.ProductList;
import com.example.yh_huijie.sqldata.ProductListDao;
import com.example.yh_huijie.sqldata.ProductListDatabase;
import com.example.yh_huijie.utils.OkManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InventoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InventoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final List<Integer> curClassIdList = new ArrayList<>();
    private final List<Integer> curClassIdListChecked = new ArrayList<>();
    private final List<Integer> curClassIdListUnChecked = new ArrayList<>();

    private static final String TAG ="TAG" ;

    View rootView;
    ProductClassDatabase productClassDatabase;
    ProductClassDao productClassDao;
    LiveData<List<ProductClass>> productClassLiveData;
    ProductClassViewModel productClassViewModel;
    ProductDetailDatabase productDetailDatabase;
    ProductDetailDao productDetailDao;
    LiveData<List<ProductDetail>> productDetailLiveData;
    ProductDetailViewModel productDetailViewModel;
    MutableLiveData<List<Integer>> classList;
    TextView bartv1, bartv2;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mRvLeftMain, mRvRightMain;
    private ArrayList<NetData> NetDatas;
    private ProductListDatabase productListDatabase;
    private ProductListDao productListDao;
    private LiveData<List<ProductList>> productListLiveData;
    private LiveData<List<ProductList>> productListLiveDataByIds;
    private ProductListViewModel productListViewModel;
    private Integer curPosition = 0;
    private ArrayList<ProductListBean> productListArray;
    private boolean allDataObserve;
    private Integer allCheckedNumber = 0;
    private Integer allUncheckedNumber = 0;


    private boolean isFirstLoad =true;
    private InventoryRightMainAdapter inventoryRightMainAdapter;

    public InventoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InventoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InventoryFragment newInstance(String param1, String param2) {
        InventoryFragment fragment = new InventoryFragment();
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
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_inventory, container, false);
        }
        initView();
        return rootView;
    }

    private void initView() {


        ImageView imageback = rootView.findViewById(R.id.backicon);
        ImageView btntest = rootView.findViewById(R.id.btntest);
        ImageView btntest1 = rootView.findViewById(R.id.btntest1);
        ImageView btntest2 = rootView.findViewById(R.id.btntest2);
        ImageView btntest3 = rootView.findViewById(R.id.btntest3);

        bartv1 = rootView.findViewById(R.id.bartv1);
        bartv2 = rootView.findViewById(R.id.bartv2);


        productClassViewModel = new ViewModelProvider(getActivity()).get(ProductClassViewModel.class);

        productListViewModel = new ViewModelProvider(getActivity()).get(ProductListViewModel.class);


        OkManager manager = OkManager.getInstance();


        // 左边RECYLEVIEW 加载数据
        InventoryLeftMainAdapter inventoryLeftMainAdapter = new InventoryLeftMainAdapter();
        mRvLeftMain = rootView.findViewById(R.id.recyr_left);
        mRvLeftMain.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvLeftMain.setAdapter(inventoryLeftMainAdapter);


        mRvRightMain = rootView.findViewById(R.id.recy_right);
        mRvRightMain.setLayoutManager(new LinearLayoutManager(getContext()));
        inventoryRightMainAdapter = new InventoryRightMainAdapter();
        mRvRightMain.setAdapter(inventoryRightMainAdapter);


//所有商品哪果显示时，需要获取数量
//        productClassViewModel.getAllProductChecked().observe(getViewLifecycleOwner(), new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                allCheckedNumber = integer;
//                if (curPosition == 0 && allCheckedNumber !=null) {
//                    Fun_disNavBar(allCheckedNumber, -1);
//                }
//            }
//        });
//        productClassViewModel.getAllProductUnChecked().observe(getViewLifecycleOwner(), new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                allUncheckedNumber = integer;
//                Log.d(TAG, "onChanged: "+allUncheckedNumber);
//                if (curPosition == 0 && allUncheckedNumber !=null) {
//                    Fun_disNavBar(-1, allUncheckedNumber);
//                }
//            }
//        });

//        productListViewModel.getAllProductListLive().observe(getViewLifecycleOwner(), new Observer<List<ProductList>>() {
//            @Override
//            public void onChanged(List<ProductList> productLists) {
//                inventoryRightMainAdapter.setData(productLists);
//                inventoryRightMainAdapter.notifyDataSetChanged();
//            }
//        });


        productClassViewModel.getAllProductClassLive().observe(getViewLifecycleOwner(), new Observer<List<ProductClass>>() {
            @Override
            public void onChanged(List<ProductClass> productClasses) {

                curClassIdList.clear();
                curClassIdListChecked.clear();
                curClassIdListUnChecked.clear();

//
//                    curClassIdList.add(0);
//                    curClassIdListChecked.add(allCheckedNumber);
//                    curClassIdListUnChecked.add(allUncheckedNumber);


                for (int i = 0; i < productClasses.size(); i++) {
                    curClassIdList.add(productClasses.get(i).getRemote_id());
                    curClassIdListChecked.add(productClasses.get(i).getClass_product_checked());
                    curClassIdListUnChecked.add(productClasses.get(i).getClass_product_unchecked());
                }
                inventoryLeftMainAdapter.setData(productClasses);
                inventoryLeftMainAdapter.notifyDataSetChanged();

                productListLoad(true,0,0);


            }


        });





        inventoryLeftMainAdapter.setRecyclerViewItemClickListener(new InventoryLeftMainAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onRecyclerViewItemClick(int position) {
                curPosition = position;
                productListLoad(false,curPosition,0 );
                barSelect(0);
                inventoryLeftMainAdapter.setCurPosition(position);
                inventoryLeftMainAdapter.notifyDataSetChanged();
            }
        });


        inventoryRightMainAdapter.setRecyclerViewItemClickListener(new InventoryRightMainAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onRecyclerViewItemClick(int position) {
                NavController controller = Navigation.findNavController(rootView);
                controller.navigate(R.id.action_inventoryFragment_to_productDetailFragment);
            }
        });


        ImageView rfid = rootView.findViewById(R.id.rfid);

        ImageView qrcode = rootView.findViewById(R.id.qrcode);


        rfid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jsonpath = "http://wx.nbyiheng.net/app/index.php?i=3&c=entry&do=aaa&m=yiheng_huamei";
                manager.asyncJsonStringByURL(jsonpath, result -> {
//                    Log.i("Tag", result);   //获取JSON字符串
                    if (result != null) {
                        ArrayList<NetData> Nds = new ArrayList<NetData>();
                        Nds = parseEasyJsonA(result);
                        for (int i = 0; i < Nds.size(); i++) {
//                            Log.d("TAG", "onClick: " + Nds.get(i).token);

//                            ProductClass productClass1 = new ProductClass(Nds.get(i).getRemote_id(), Nds.get(i).getClass_name(), 1, 1, true,50,150);
                            ProductClass productClass1 = new ProductClass(Nds.get(i).getRemote_id(), Nds.get(i).getClass_name(), 1, 1, true, Nds.get(i).class_product_checked, Nds.get(i).class_product_unchecked);

                            productClassViewModel.insertProductClass(productClass1);
                        }
                    }
//

                });
            }
        });


        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jsonpath = "http://wx.nbyiheng.net/app/index.php?i=3&c=entry&do=bbb&m=yiheng_huamei";
                manager.asyncJsonStringByURL(jsonpath, result -> {
//                    Log.i("Tag", result);   //获取JSON字符串
                    if (result != null) {
                        ArrayList<ProductListBean> Nds = new ArrayList<ProductListBean>();
                        Nds = parseEasyJson(result);
                        for (int i = 0; i < Nds.size(); i++) {
                            ProductList productList = new ProductList(Integer.parseInt(Nds.get(i).getRemote_id()), Nds.get(i).getP_class_name(), Integer.parseInt(Nds.get(i).getP_class_id()), Nds.get(i).getP_name(), Nds.get(i).getP_barcode(), Nds.get(i).getP_rfid(), Nds.get(i).getP_type(), Nds.get(i).getP_stock_no(), Float.parseFloat(Nds.get(i).getP_price()), Float.parseFloat(Nds.get(i).getP_weight()), Nds.get(i).getP_detail(), Integer.parseInt(Nds.get(i).getP_count()), Integer.parseInt(Nds.get(i).getP_display()), Integer.parseInt(Nds.get(i).getP_checkout()), Nds.get(i).getP_checktime(),Nds.get(i).getP_checktime_str());
                            productListViewModel.insertProductList(productList);
                        }
                    }

                });
            }
        });



        bartv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barSelect(0);
                productListViewModel.productListByIdLive(curClassIdList.get(curPosition), 0).observe(getViewLifecycleOwner(), new Observer<List<ProductList>>() {
                    @Override
                    public void onChanged(List<ProductList> productLists) {
                        inventoryRightMainAdapter.setData(productLists);
                        inventoryRightMainAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        bartv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barSelect(1);
                productListViewModel.productListByIdLive(curClassIdList.get(curPosition), 1).observe(getViewLifecycleOwner(), new Observer<List<ProductList>>() {
                    @Override
                    public void onChanged(List<ProductList> productLists) {

                        inventoryRightMainAdapter.setData(productLists);
                        inventoryRightMainAdapter.notifyDataSetChanged();
                    }
                });

            }
        });


        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_inventoryFragment_to_homeFragment);
                //  Toast.makeText(getActivity(), "1要实打实棒棒 ", Toast.LENGTH_SHORT).show();
            }
        });
        btntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductList productList = new ProductList(1, "手提包", 13, "Prada Symbole 刺绣织物大号托特包",
                        "971921962547", "rfid_552428953582", "1", "A0B13", Float.parseFloat("11111"), Float.parseFloat("1254"), "getP_detail", 1254, 1, 0, "1652749539","2022-05-18 12:05:39");
                productList.setId(1);
                productListViewModel.updateProductList(productList);

            }
        });
        btntest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                LiveData<List<ProductList>> a =  productListViewModel.productListByIdLive(1);
                productListViewModel.productListByIdLive(13).observe(getViewLifecycleOwner(), new Observer<List<ProductList>>() {
                    @Override
                    public void onChanged(List<ProductList> productLists) {
                        inventoryRightMainAdapter.setData(productLists);
                        inventoryRightMainAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        btntest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btntest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextNumber = rootView.findViewById(R.id.editTextNumber);
                int a = Integer.parseInt(editTextNumber.getText().toString());


                ProductClass productClass1 = new ProductClass(13, "手提包", 1, 1, true, 10, a);
                productClass1.setId(1);
                productClassViewModel.updateProductClass(productClass1);

                bartv1.setText("未盘存(" + allCheckedNumber + ")");
                bartv2.setText("已盘存(" + allUncheckedNumber + ")");

            }
        });

    }

    private void productListLoad(boolean isFirstLoad,int position,int checkFlag ) {
        position = isFirstLoad==true?0:position;
        Fun_disNavBar(curClassIdListChecked.get(position), curClassIdListUnChecked.get(position));
        productListViewModel.productListByIdLive(curClassIdList.get(position), checkFlag).observe(getViewLifecycleOwner(), new Observer<List<ProductList>>() {
            @Override
            public void onChanged(List<ProductList> productLists) {
                inventoryRightMainAdapter.setData(productLists);
                inventoryRightMainAdapter.notifyDataSetChanged();
            }
        });
    }

    private void barSelect(int f)
    {
        if(f==0)
        {
            bartv1.setBackgroundResource(R.drawable.box_01_left);
            bartv2.setBackgroundResource(R.drawable.box_01_right);
            bartv1.setTextColor(Color.WHITE);
            bartv2.setTextColor(Color.BLACK);
        }
        else
        {
            bartv1.setBackgroundResource(R.drawable.box_02_left);
            bartv2.setBackgroundResource(R.drawable.box_02_right);
            bartv1.setTextColor(Color.BLACK);
            bartv2.setTextColor(Color.WHITE);
        }
    }
    private void Fun_disNavBar(int barACount, int barBCount) {
        if (barACount == -1 || barBCount == -1) {
            if (barACount == -1) {
                bartv2.setText("已盘存(" + barBCount + ")");
            } else {
                bartv1.setText("未盘存(" + barACount + ")");
            }
        } else {
            bartv1.setText("未盘存(" + barACount + ")");
            bartv2.setText("已盘存(" + barBCount + ")");
        }
    }

    private ArrayList<NetData> parseEasyJsonA(String json) {
        NetDatas = new ArrayList<NetData>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                NetData netData = new NetData();
                netData.setRemote_id(jsonObject.getInt("remote_id"));
                netData.setClass_name(jsonObject.getString("class_name"));
                netData.setClass_order(jsonObject.getString("class_order"));
                netData.setClass_product_count(jsonObject.getString("class_product_count"));
                netData.setClass_display(jsonObject.getString("class_display"));
                netData.setClass_product_checked(jsonObject.getInt("class_product_checked"));
                netData.setClass_product_unchecked(jsonObject.getInt("class_product_unchecked"));
                NetDatas.add(netData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NetDatas;
    }


    private ArrayList<ProductListBean> parseEasyJson(String json) {
        productListArray = new ArrayList<ProductListBean>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                ProductListBean productListBean = new ProductListBean();
                productListBean.setRemote_id(jsonObject.getString("p_remote_id"));
                productListBean.setP_class_name(jsonObject.getString("p_class_name"));
                productListBean.setP_class_id(jsonObject.getString("p_class_id"));
                productListBean.setP_name(jsonObject.getString("p_name"));
                productListBean.setP_barcode(jsonObject.getString("p_barcode"));
                productListBean.setP_rfid(jsonObject.getString("p_rfid"));
                productListBean.setP_type(jsonObject.getString("p_type"));
                productListBean.setP_stock_no(jsonObject.getString("p_stock_no"));
                productListBean.setP_price(jsonObject.getString("p_price"));
                productListBean.setP_weight(jsonObject.getString("p_weight"));
                productListBean.setP_detail(jsonObject.getString("p_detail"));
                productListBean.setP_count(jsonObject.getString("p_count"));
                productListBean.setP_display(jsonObject.getString("p_display"));
                productListBean.setP_checkout(jsonObject.getString("p_checkout"));
                productListBean.setP_checktime(jsonObject.getString("p_checktime"));
                productListBean.setP_checktime_str(jsonObject.getString("p_checktime_str"));
                productListArray.add(productListBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productListArray;
    }


    private class NetData {


        private Integer remote_id;
        private String class_name;
        private String class_order;
        private String class_product_count;
        private String class_display;
        private Integer class_product_checked;
        private Integer class_product_unchecked;


        public Integer getRemote_id() {
            return remote_id;
        }

        public void setRemote_id(Integer remote_id) {
            this.remote_id = remote_id;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public String getClass_order() {
            return class_order;
        }

        public void setClass_order(String class_order) {
            this.class_order = class_order;
        }

        public String getClass_product_count() {
            return class_product_count;
        }

        public void setClass_product_count(String class_product_count) {
            this.class_product_count = class_product_count;
        }

        public String getClass_display() {
            return class_display;
        }

        public void setClass_display(String class_display) {
            this.class_display = class_display;
        }

        public Integer getClass_product_checked() {
            return class_product_checked;
        }

        public void setClass_product_checked(Integer class_product_checked) {
            this.class_product_checked = class_product_checked;
        }

        public Integer getClass_product_unchecked() {
            return class_product_unchecked;
        }

        public void setClass_product_unchecked(Integer class_product_unchecked) {
            this.class_product_unchecked = class_product_unchecked;
        }
    }


    /**
     * Copyright 2022 bejson.com
     */

    /**
     * Auto-generated: 2022-05-07 20:37:51
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    private class ProductListBean {

        private String remote_id;
        private String p_class_name;
        private String p_class_id;
        private String p_name;
        private String p_barcode;
        private String p_rfid;
        private String p_type;
        private String p_stock_no;
        private String p_price;
        private String p_weight;
        private String p_detail;
        private String class_order;
        private String p_count;
        private String p_display;
        private String p_checkout;
        private String p_checktime;
        private String p_checktime_str;

        public String getP_checktime() {
            return p_checktime;
        }

        public void setP_checktime(String p_checktime) {
            this.p_checktime = p_checktime;
        }

        public String getP_checktime_str() {
            return p_checktime_str;
        }

        public void setP_checktime_str(String p_checktime_str) {
            this.p_checktime_str = p_checktime_str;
        }

        public String getP_checkout() {
            return p_checkout;
        }

        public void setP_checkout(String p_checkout) {
            this.p_checkout = p_checkout;
        }

        public String getRemote_id() {
            return remote_id;
        }

        public void setRemote_id(String remote_id) {
            this.remote_id = remote_id;
        }

        public String getP_class_name() {
            return p_class_name;
        }

        public void setP_class_name(String p_class_name) {
            this.p_class_name = p_class_name;
        }

        public String getP_class_id() {
            return p_class_id;
        }

        public void setP_class_id(String p_class_id) {
            this.p_class_id = p_class_id;
        }

        public String getP_name() {
            return p_name;
        }

        public void setP_name(String p_name) {
            this.p_name = p_name;
        }

        public String getP_barcode() {
            return p_barcode;
        }

        public void setP_barcode(String p_barcode) {
            this.p_barcode = p_barcode;
        }

        public String getP_rfid() {
            return p_rfid;
        }

        public void setP_rfid(String p_rfid) {
            this.p_rfid = p_rfid;
        }

        public String getP_type() {
            return p_type;
        }

        public void setP_type(String p_type) {
            this.p_type = p_type;
        }

        public String getP_stock_no() {
            return p_stock_no;
        }

        public void setP_stock_no(String p_stock_no) {
            this.p_stock_no = p_stock_no;
        }

        public String getP_price() {
            return p_price;
        }

        public void setP_price(String p_price) {
            this.p_price = p_price;
        }

        public String getP_weight() {
            return p_weight;
        }

        public void setP_weight(String p_weight) {
            this.p_weight = p_weight;
        }

        public String getP_detail() {
            return p_detail;
        }

        public void setP_detail(String p_detail) {
            this.p_detail = p_detail;
        }

        public String getClass_order() {
            return class_order;
        }

        public void setClass_order(String class_order) {
            this.class_order = class_order;
        }

        public String getP_count() {
            return p_count;
        }

        public void setP_count(String p_count) {
            this.p_count = p_count;
        }

        public String getP_display() {
            return p_display;
        }

        public void setP_display(String p_display) {
            this.p_display = p_display;
        }

    }

}

//
//    RecyclerView 关于Item选中取消选中的完美实现
//   https://blog.csdn.net/qq_40642784/article/details/117292219
