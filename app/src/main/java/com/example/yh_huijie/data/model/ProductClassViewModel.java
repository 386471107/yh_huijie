package com.example.yh_huijie.data.model;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.yh_huijie.data.ProductClassRepostitory;
import com.example.yh_huijie.sqldata.ProductClass;
import com.example.yh_huijie.sqldata.ProductClassDao;
import com.example.yh_huijie.sqldata.ProductClassDatabase;

import java.util.List;

public class ProductClassViewModel extends AndroidViewModel {

    private ProductClassDao productClassDao;
    ProductClassRepostitory productClassRepostitory;
    private LiveData<List<ProductClass>>allProductClassLive;

    private LiveData<Integer> allProductChecked,allProductUnChecked;


    public ProductClassViewModel(@NonNull Application application) {
        super(application);

        productClassRepostitory = new ProductClassRepostitory(application.getApplicationContext());
        allProductClassLive=productClassRepostitory.getGetallProductClassLive();
        productClassDao  =productClassRepostitory.getProductClassDao();

        allProductChecked = productClassRepostitory.getAllProductChecked();
        allProductUnChecked= productClassRepostitory.getAllProductUnChecked();
    }

    public LiveData<Integer> getAllProductChecked() {
        return allProductChecked;
    }

    public LiveData<Integer> getAllProductUnChecked() {
        return allProductUnChecked;
    }

    public LiveData<List<ProductClass>> getAllProductClassLive() {
        return allProductClassLive;
    }



    public void insertProductClass(ProductClass... productClasses)
    {
        productClassRepostitory.insertProductClass(productClasses);
    }
    public void updateProductClass(ProductClass... productClasses)
    {
        productClassRepostitory.updateProductClass(productClasses);
    }


    public void deleteProductClass()
    {
        productClassRepostitory.deleteProductClass();
    }



}
