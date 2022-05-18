package com.example.yh_huijie.sqldata;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ProductDetail.class}, version = 1,exportSchema = false)
public abstract  class ProductsDatabase extends RoomDatabase{
    private static ProductsDatabase INSTANCE;
     public static synchronized ProductsDatabase getDatabase(Context context)
    {
        if (INSTANCE==null)
        {
            INSTANCE=Room.databaseBuilder(context.getApplicationContext(), ProductsDatabase.class,"ProductDetail").build();
        }
        return INSTANCE;
    }


    public abstract ProductDetailDao getProductDetailDao();
}
