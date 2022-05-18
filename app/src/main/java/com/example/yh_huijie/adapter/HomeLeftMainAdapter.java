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
import com.example.yh_huijie.sqldata.ProductClass;

import java.util.ArrayList;
import java.util.List;

public class HomeLeftMainAdapter extends RecyclerView.Adapter<HomeLeftMainAdapter.leftMianHolder> {

    List<ProductClass>data = new ArrayList<>();

    public void setData(List<ProductClass> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public leftMianHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new leftMianHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homeleft, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull leftMianHolder holder, @SuppressLint("RecyclerView") int position) {

        ProductClass productClass = data.get(position);
        holder.tv_content.setText(String.valueOf(productClass.getClass_name()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "我被点击了"+String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class leftMianHolder extends RecyclerView.ViewHolder {
        private TextView tv_content;
        public leftMianHolder(@NonNull View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.textView);
        }


    }
}
