package com.example.yh_huijie.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.yh_huijie.sqldata.ProductDetail;
import com.example.yh_huijie.sqldata.ProductDetailDao;
import com.example.yh_huijie.sqldata.ProductDetailDatabase;

import java.util.List;

public class ProductDetailRepostitory {
    private LiveData<List<ProductDetail>> getallProductDetailLive;
    private ProductDetailDao productDetailDao;

    public ProductDetailRepostitory(Context context) {
        ProductDetailDatabase productDetailDatabase = ProductDetailDatabase.getDatabase(context);
        productDetailDao = productDetailDatabase.getProductDetaiDao();
        getallProductDetailLive = productDetailDao.getAllLiveDataProducts();
    }

    public ProductDetailDao getProductDetailDao() {
        return productDetailDao;
    }

    public ProductDetailRepostitory(LiveData<List<ProductDetail>> getallProductDetailLive, ProductDetailDao productDetailDao) {
        this.getallProductDetailLive = getallProductDetailLive;
        this.productDetailDao = productDetailDao;
    }



    public LiveData<List<ProductDetail>> getAllProductDetailLive() {
        return getallProductDetailLive;
    }


    public void insertProductDetail(ProductDetail... productDetails)
    {
        new InsertAsynTask(productDetailDao).execute(productDetails);
    }

    public void deleteProductDetail()
    {
        new DeleteAsynTask(productDetailDao).execute();
    }

    static class InsertAsynTask extends AsyncTask<ProductDetail,Void,Void>
    {
        private ProductDetailDao productDetailDao;

        public InsertAsynTask(ProductDetailDao productDetailDao) {
            this.productDetailDao = productDetailDao;
        }
        @Override
        protected Void doInBackground(ProductDetail... productDetails) {
            productDetailDao.insertProductDetail(productDetails);
            return null;
        }
    }

    static class DeleteAsynTask extends AsyncTask<Void,Void,Void>
    {
        private ProductDetailDao productDetailDao;

        public DeleteAsynTask(ProductDetailDao productDetailDao) {
            this.productDetailDao = productDetailDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDetailDao.deleteAllProductDetail();
            return null;
        }
    }
}
