package com.example.yh_huijie.action;

import static com.alibaba.fastjson.JSON.parseObject;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.asm.Type;
import com.example.yh_huijie.R;
import com.example.yh_huijie.utils.FastJson;
import com.example.yh_huijie.utils.OkManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductAddPageaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductAddPageaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View rootView;



    //图片下载的请求地址
    private String img_path = "http://192.168.191.1:8080/OkHttp3Server/UploadDownloadServlet?method=download";
    //请求返回值为Json数组
    private String jsonpath = "http://web.nbyiheng.net/public/1.php";
    //登录验证请求
//    private String login_path="http://192.168.191.1:8080/OkHttp3Server/OkHttpLoginServlet";

    private String login_path="http://web.nbyiheng.net/public/1.php";

    private ArrayList<NetData> NetDatas;


    public ProductAddPageaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductAddPageaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductAddPageaFragment newInstance(String param1, String param2) {
        ProductAddPageaFragment fragment = new ProductAddPageaFragment();
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
        // Inflate the layout for this fragment
       
        if (rootView ==null)
        {
            rootView  = inflater.inflate(R.layout.fragment_product_add_pagea, container, false);
        }
        initView();
        return rootView;
        
    }

    private void initView() {


        Button testButton = rootView.findViewById(R.id.test);
        Button getJsonButton = rootView.findViewById(R.id.getjson);
        ImageView testImageView = rootView.findViewById(R.id.testImageView);
        Button button3 = rootView.findViewById(R.id.button3);

        //-----------------------------------------------------------------------------------------
        OkManager manager = OkManager.getInstance();
        getJsonButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                manager.asyncJsonStringByURL(jsonpath, result -> {
                   Log.i("Tag", result);   //获取JSON字符串


                    ArrayList<NetData> Nds = new ArrayList<NetData>();
                    Nds =  parseEasyJson(result);
                    for (int i=0;i<Nds.size();i++) {
                        Log.d("TAG", "onClick: "+Nds.get(i).token);
                    }
//                    Log.d("TAG", "onClick: "+Nds.get(0).token);

                });
            }
        });
        //-------------------------------------------------------------------------
        //用于登录请求测试，登录用户名和登录密码应该Sewrver上的对应
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("username", "123");
                map.put("password", "123");
                manager.sendComplexForm(login_path, map, new OkManager.Fun4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.i("Tag", jsonObject.toString());
                    }


                });
            }
        });
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.asyncDownLoadImgtByUrl(img_path, new OkManager.Fun3() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        testImageView.setImageBitmap(bitmap);
                        Log.i("Tag", "231541645");
                    }


                });
            }
        });



        }



    private ArrayList<NetData> parseEasyJson(String json){
        NetDatas = new ArrayList<NetData>();
        try{
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                NetData netData = new NetData();
                netData.setToken(jsonObject.getString("token"));
                netData.setMessage(jsonObject.getString("message"));
//                Log.d("TAG", "parseEasyJson: "+jsonObject.getString("token"));
                NetDatas.add(netData);
            }
        }catch (Exception e){e.printStackTrace();}
        return NetDatas;
    }


    private class NetData {

        private String message;
        private String  token;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}


