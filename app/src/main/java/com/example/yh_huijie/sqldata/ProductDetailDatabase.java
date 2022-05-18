package com.example.yh_huijie.sqldata;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ProductDetail.class}, version = 1,exportSchema = false)
public abstract  class ProductDetailDatabase extends RoomDatabase{
    private static ProductDetailDatabase INSTANCE;
     public static synchronized ProductDetailDatabase getDatabase(Context context)
    {
        if (INSTANCE==null)
        {
            INSTANCE=Room.databaseBuilder(context.getApplicationContext(), ProductDetailDatabase.class,"product_detail").build();
        }
        return INSTANCE;
    }


    public abstract ProductDetailDao getProductDetaiDao();
}
