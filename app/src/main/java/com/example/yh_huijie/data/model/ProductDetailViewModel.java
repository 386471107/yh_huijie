package com.example.yh_huijie.data.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.yh_huijie.data.ProductDetailRepostitory;
import com.example.yh_huijie.sqldata.ProductDetail;
import com.example.yh_huijie.sqldata.ProductDetailDao;

import java.util.List;

public class ProductDetailViewModel extends AndroidViewModel {

    private ProductDetailDao productDetailDao;
    ProductDetailRepostitory productDetailRepostitory;
    private LiveData<List<ProductDetail>>allProductDetailLive;
    public ProductDetailViewModel(@NonNull Application application) {
        super(application);
        productDetailRepostitory = new ProductDetailRepostitory(application.getApplicationContext());
        allProductDetailLive=productDetailRepostitory.getAllProductDetailLive();
        productDetailDao  =productDetailRepostitory.getProductDetailDao();

    }

    public LiveData<List<ProductDetail>> getAllProductDetailLive() {
        return allProductDetailLive;
    }


    public void insertProductDetail(ProductDetail... productDetails)
    {
        productDetailRepostitory.insertProductDetail(productDetails);
    }

    public void deleteProductDetail()
    {
        productDetailRepostitory.deleteProductDetail();
    }



}
