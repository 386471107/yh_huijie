package com.example.yh_huijie.sqldata;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDetailDao {
    @Insert
    void insertProductDetail(ProductDetail...productDetails);

    @Update
    void updateProductDetail(ProductDetail...productDetails);

    @Delete
    void deleteProductDetail(ProductDetail...productDetails);

    @Query("DELETE FROM ProductDetail")
    void deleteAllProductDetail();

    @Query("SELECT * FROM ProductDetail ORDER BY ID DESC")
    LiveData<List<ProductDetail>> getAllLiveDataProducts();

}
