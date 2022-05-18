package com.example.yh_huijie.sqldata;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductClassDao {
    @Insert
    void insertProductClass(ProductClass ... productClasses);

    @Update
    void updateProductClass(ProductClass ... productClasses);

    @Delete
    void deleteProductClass(ProductClass ... productClasses);

    @Query("DELETE FROM ProductClass")
    void deleteAllProductClass();

    @Query("SELECT * FROM ProductClass ORDER BY class_order ASC")
    LiveData<List<ProductClass>> getAllLiveDataProductClass();


    @Query("SELECT SUM(class_product_checked) FROM ProductClass")
    LiveData<Integer>getAllproductChecked();

    @Query("SELECT SUM(class_product_unchecked) FROM ProductClass")
    LiveData<Integer>getAllproductUnChecked();

}
