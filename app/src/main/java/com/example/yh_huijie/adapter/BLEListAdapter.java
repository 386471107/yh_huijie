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


public class BLEListAdapter extends RecyclerView.Adapter<BLEListAdapter.bleListHolder> {

    List<ProductClass>data = new ArrayList<>();

    public void setData(List<ProductClass> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public bleListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new bleListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ble_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull bleListHolder holder, @SuppressLint("RecyclerView") int position) {

        ProductClass ProductClass = data.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), new StringBuilder().append("我被点击了").append(String.valueOf(position)).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class bleListHolder  extends RecyclerView.ViewHolder {
        private TextView tv_content,tv_content24;
        public bleListHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
