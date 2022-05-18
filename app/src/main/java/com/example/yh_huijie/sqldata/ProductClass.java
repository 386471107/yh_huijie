package com.example.yh_huijie.sqldata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProductClass {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="remote_id")
    private int remote_id;
    @ColumnInfo(name="class_name")
    private String class_name;
    @ColumnInfo(name="class_order")
    private int class_order;
    @ColumnInfo(name="class_product_count")
    private int class_product_count;



    @ColumnInfo(name="class_display" ,defaultValue = "1")
    private Boolean class_display ;

    @ColumnInfo(name="class_product_checked",defaultValue = "0")
    private int class_product_checked;

    @ColumnInfo(name="class_product_unchecked",defaultValue = "0")
    private int class_product_unchecked;

    public ProductClass(int remote_id, String class_name, int class_order, int class_product_count, Boolean class_display, int class_product_checked, int class_product_unchecked) {
        this.remote_id = remote_id;
        this.class_name = class_name;
        this.class_order = class_order;
        this.class_product_count = class_product_count;
        this.class_display = class_display;
        this.class_product_checked = class_product_checked;
        this.class_product_unchecked = class_product_unchecked;
    }

    public int getClass_product_checked() {
        return class_product_checked;
    }

    public void setClass_product_checked(int class_product_checked) {
        this.class_product_checked = class_product_checked;
    }

    public int getClass_product_unchecked() {
        return class_product_unchecked;
    }

    public void setClass_product_unchecked(int class_product_unchecked) {
        this.class_product_unchecked = class_product_unchecked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRemote_id() {
        return remote_id;
    }

    public void setRemote_id(int remote_id) {
        this.remote_id = remote_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getClass_order() {
        return class_order;
    }

    public void setClass_order(int class_order) {
        this.class_order = class_order;
    }

    public int getClass_product_count() {
        return class_product_count;
    }

    public void setClass_product_count(int class_product_count) {
        this.class_product_count = class_product_count;
    }

    public Boolean getClass_display() {
        return class_display;
    }

    public void setClass_display(Boolean class_display) {
        this.class_display = class_display;
    }
}
