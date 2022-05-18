package com.example.yh_huijie.action;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yh_huijie.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View rootView;
    private TextView curTime;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
            rootView  = inflater.inflate(R.layout.fragment_home, container, false);
        }
        initView();
        return rootView;

    }



    private void initView() {
        ImageView  imageInventory =rootView.findViewById(R.id.imageInventory);

        ImageView  imageView7 =rootView.findViewById(R.id.imageView7);

        ImageView  imageView8 =rootView.findViewById(R.id.imageView8);

        ImageView  imageView9 =rootView.findViewById(R.id.imageView9);
        ImageView  imageView10 =rootView.findViewById(R.id.imageView10);

        ImageView  imagesetting =rootView.findViewById(R.id.imagesetting);

        ImageView  padd =rootView.findViewById(R.id.padd);


        Button btn1 = rootView.findViewById(R.id.button1);
        Button btn2 = rootView.findViewById(R.id.button2);
        Button btn3 = rootView.findViewById(R.id.button3);
        Button btn4 = rootView.findViewById(R.id.button4);
        Button btn5 = rootView.findViewById(R.id.button5);
        Button btn6 = rootView.findViewById(R.id.button6);


        long lCurTime = System.currentTimeMillis();
        CharSequence curTimeFormat = DateFormat.format("yyyy-MM-dd hh:mm:ss ", lCurTime);
        curTime = rootView.findViewById(R.id.curtime);
        curTime.setText("登录时间 : "+String.valueOf(curTimeFormat));

        imageInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller =Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment2_to_inventoryFragment);
              //  Toast.makeText(getActivity(), "1要实打实棒棒 ", Toast.LENGTH_SHORT).show();
            }
        });

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller =Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment_to_productSearchFragment);
                //  Toast.makeText(getActivity(), "1要实打实棒棒 ", Toast.LENGTH_SHORT).show();
            }
        });


        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller =Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment_to_productCalcFragment);
                //  Toast.makeText(getActivity(), "1要实打实棒棒 ", Toast.LENGTH_SHORT).show();
            }
        });

        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller =Navigation.findNavController(v);
                controller.navigate(R.id.inventoryByWeightFragment);
                //  Toast.makeText(getActivity(), "1要实打实棒棒 ", Toast.LENGTH_SHORT).show();
            }
        });
        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller =Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment_to_productSynchronizationFragment);
//                controller.navigate(R.id.action_homeFragment_to_searchFragment2);
                //  Toast.makeText(getActivity(), "1要实打实棒棒 ", Toast.LENGTH_SHORT).show();
            }
        });


        imagesetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller =Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment_to_testFragment);
                //  Toast.makeText(getActivity(), "1要实打实棒棒 ", Toast.LENGTH_SHORT).show();
            }
        });

        padd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller =Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment_to_productAddPageaFragment);
//                Uri uri = Uri.parse("http://www.sina.com");
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                intent.setData(uri);
//                startActivity(intent);
            }
        });



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller =Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment_to_BLESearchFragment);
                //  Toast.makeText(getActivity(), "1要实打实棒棒 ", Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller =Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment_to_testFragment);
                //  Toast.makeText(getActivity(), "1要实打实棒棒 ", Toast.LENGTH_SHORT).show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller =Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment_to_productSynchronizationFragment);
                //  Toast.makeText(getActivity(), "1要实打实棒棒 ", Toast.LENGTH_SHORT).show();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller =Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment_to_BLESearchFragment);
                //  Toast.makeText(getActivity(), "1要实打实棒棒 ", Toast.LENGTH_SHORT).show();
            }
        });


        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller =Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment_to_searchFragment2);
                //  Toast.makeText(getActivity(), "1要实打实棒棒 ", Toast.LENGTH_SHORT).show();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller =Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment_to_testFragment);
                //  Toast.makeText(getActivity(), "1要实打实棒棒 ", Toast.LENGTH_SHORT).show();
            }
        });







    }


    @Override
    public void onPrimaryNavigationFragmentChanged(boolean isPrimaryNavigationFragment) {
        super.onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment);
        Log.e("TAG", "onClick: aaaaaa" );
    }




    }
