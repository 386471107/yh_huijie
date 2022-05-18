package com.example.yh_huijie.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yh_huijie.R;

import java.util.ArrayList;
import java.util.List;

public class HomeRightMainAdapter extends RecyclerView.Adapter<HomeRightMainAdapter.rightMianHolder> {

    private  final  List<String> data;
    public HomeRightMainAdapter()
    {
        data = new ArrayList<>();
        for (int i = 0;i<50;i++)
        {
            data.add(String.valueOf(i));
        }
    }

    @NonNull
    @Override
    public rightMianHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new rightMianHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull rightMianHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.bindData(data,position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "HomeRightMainAdapter"+String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class rightMianHolder extends RecyclerView.ViewHolder {
        private TextView tv_content;
        public rightMianHolder(@NonNull View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.textView);
        }

        public void bindData(List<String>data, int postion) {

            String s=data.get(postion);
            tv_content.setText(s);

        }
    }
}
