package com.example.yh_huijie.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yh_huijie.R;
import com.example.yh_huijie.sqldata.ProductClass;

import java.util.ArrayList;
import java.util.List;

public class InventoryLeftMainAdapter extends RecyclerView.Adapter<InventoryLeftMainAdapter.leftMianHolder> {

    private final int lastPosition = -1;
    List<ProductClass> data = new ArrayList<>();
    private int curPosition = 0;
    private boolean addTagFlag = false;
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;

    public List<ProductClass> getData() {
        return data;
    }

    public void setData(List<ProductClass> data) {

//        if (addTagFlag==false)
//        {
//            ProductClass productClass1 = new ProductClass(0, "全部商品", 0, 0, true, 0, 0);
//            data.add(0, productClass1);
//            addTagFlag=true;
//        }


        this.data = data;
    }

    @NonNull
    @Override
    public leftMianHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new leftMianHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull leftMianHolder holder, @SuppressLint("RecyclerView") int position) {


        ProductClass productClass = data.get(position);

        holder.tv_content.setText(String.valueOf(productClass.getClass_name()));
        if (getCurPosition() == position) {
            holder.tv_content.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.tv_content.setTextColor(Color.parseColor("#099BF4"));
        } else {
            //未选中
            holder.tv_content.setBackgroundColor(Color.parseColor("#F1F2F7"));
            holder.tv_content.setTextColor(Color.parseColor("#46536D"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 这边是点击事件，测试一个数据插入
                if (mOnRecyclerViewItemClickListener != null) {
                    mOnRecyclerViewItemClickListener.onRecyclerViewItemClick(position);
                    ProductClass productClass = data.get(position);
//                    Toast.makeText(view.getContext(), productClass.getClass_name() + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //再定义一个int类型的返回值方法
    public int getCurPosition() {
        return curPosition;
    }

    //   传入点击的位置
    public void setCurPosition(int curPosition) {
        this.curPosition = curPosition;
    }

    public void setRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnRecyclerViewItemClickListener = listener;
    }

    public interface OnRecyclerViewItemClickListener {
        void onRecyclerViewItemClick(int position);
    }

    public static class leftMianHolder extends RecyclerView.ViewHolder {
        private final TextView tv_content;

        public leftMianHolder(@NonNull View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.textView);
        }
    }


}
