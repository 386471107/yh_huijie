package com.example.yh_huijie.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.yh_huijie.sqldata.ProductClass;
import com.example.yh_huijie.sqldata.ProductClassDao;
import com.example.yh_huijie.sqldata.ProductClassDatabase;

import java.util.List;

public class ProductClassRepostitory {
    private LiveData<List<ProductClass>> getallProductClassLive;
    private ProductClassDao productClassDao;

    private LiveData<Integer> allProductChecked;
    private LiveData<Integer> allProductUnChecked;

    public ProductClassRepostitory(Context context) {
        ProductClassDatabase productClassDatabase = ProductClassDatabase.getDatabase(context);
        productClassDao = productClassDatabase.getProductClassDao();
        getallProductClassLive = productClassDao.getAllLiveDataProductClass();

        allProductChecked = productClassDao.getAllproductChecked();

        allProductUnChecked = productClassDao.getAllproductUnChecked();

    }

    public LiveData<Integer> getAllProductUnChecked() {
        return allProductUnChecked;
    }

    public LiveData<Integer> getAllProductChecked() {
        return allProductChecked;
    }

    public ProductClassDao getProductClassDao() {
        return productClassDao;
    }

    public ProductClassRepostitory(LiveData<List<ProductClass>> getallProductClassLive) {
        this.getallProductClassLive = getallProductClassLive;
    }

    public LiveData<List<ProductClass>> getGetallProductClassLive() {
        return getallProductClassLive;
    }





    public void insertProductClass(ProductClass... productClasses)
    {

        new InsertAsynTask(productClassDao).execute(productClasses);
    }


    public void updateProductClass(ProductClass... productClasses)
    {

        new UpdateAsynTask(productClassDao).execute(productClasses);
    }

    public void deleteProductClass()
    {
        new DeleteAsynTask(productClassDao).execute();
    }

    static class InsertAsynTask extends AsyncTask<ProductClass,Void,Void>
    {
        private ProductClassDao productClassDao;
        public InsertAsynTask(ProductClassDao productClassDao) {
            this.productClassDao = productClassDao;
        }
        @Override
        protected Void doInBackground(ProductClass... productClasses) {
            productClassDao.insertProductClass(productClasses);
            return null;
        }
    }

    static class UpdateAsynTask extends AsyncTask<ProductClass,Void,Void>
    {
        private ProductClassDao productClassDao;
        public UpdateAsynTask(ProductClassDao productClassDao) {
            this.productClassDao = productClassDao;
        }
        @Override
        protected Void doInBackground(ProductClass... productClasses) {
            productClassDao.updateProductClass(productClasses);
            return null;
        }
    }


    static class DeleteAsynTask extends AsyncTask<Void,Void,Void>
    {
        private ProductClassDao productClassDao;
        public DeleteAsynTask(ProductClassDao productClassDao) {
            this.productClassDao = productClassDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productClassDao.deleteAllProductClass();
            return null;
        }
    }
}
