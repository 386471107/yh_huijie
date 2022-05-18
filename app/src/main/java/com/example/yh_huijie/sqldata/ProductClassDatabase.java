package com.example.yh_huijie.sqldata;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ProductClass.class}, version = 1,exportSchema = false)
public abstract  class ProductClassDatabase extends RoomDatabase{
    private static ProductClassDatabase INSTANCE;
     public static synchronized ProductClassDatabase getDatabase(Context context)
    {
        if (INSTANCE==null)
        {
            INSTANCE=Room.databaseBuilder(context.getApplicationContext(),ProductClassDatabase.class,"product_class").build();
        }
        return INSTANCE;
    }


    public abstract ProductClassDao getProductClassDao();
}
