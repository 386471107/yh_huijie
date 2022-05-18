package com.example.yh_huijie.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yh_huijie.R;
import com.example.yh_huijie.sqldata.ProductDetail;
import com.example.yh_huijie.sqldata.ProductList;

import java.util.ArrayList;
import java.util.List;

public class InventoryRightMainAdapter extends RecyclerView.Adapter<InventoryRightMainAdapter.rightMianHolder> {

    List<ProductList> data = new ArrayList<>();

    Boolean checkFlag = false;

    public void setData(List<ProductList> data) {
        this.data = data;
    }

    public void setCheckFlag(Boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    @NonNull
    @Override
    public rightMianHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new rightMianHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull rightMianHolder holder, @SuppressLint("RecyclerView") int position) {

        ProductList productList = data.get(position);

        holder.tv_title.setText(String.valueOf(productList.getP_name()));
        holder.tv_barcode.setText(String.valueOf(productList.getP_barcode()));
        holder.tv_price.setText("￥：" + String.valueOf(productList.getP_price()));
        if (productList.getP_checkout() == 0) {
            holder.check_area.setVisibility(View.GONE);
        } else {
            holder.check_area.setVisibility(View.VISIBLE);
            holder.tv_checktime.setText(productList.getP_checktime_str());
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "025我被点击了" + String.valueOf(position), Toast.LENGTH_SHORT).show();
                if (mOnRecyclerViewItemClickListener != null) {
                    mOnRecyclerViewItemClickListener.onRecyclerViewItemClick(holder.getAdapterPosition());
//                    Toast.makeText(view.getContext(), "商品入详情,InventoryRightMainAdapter被点击" + String.valueOf(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;

    public void setRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnRecyclerViewItemClickListener = listener;
    }

    public interface OnRecyclerViewItemClickListener {
        void onRecyclerViewItemClick(int position);
    }


    public class rightMianHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_barcode, tv_price,tv_checktime;
        private LinearLayout check_area;

        public rightMianHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.title);
            tv_barcode = itemView.findViewById(R.id.barcode);
            tv_price = itemView.findViewById(R.id.price);
            tv_checktime= itemView.findViewById(R.id.checktime);
            check_area = itemView.findViewById(R.id.check_area);


        }

    }
}
