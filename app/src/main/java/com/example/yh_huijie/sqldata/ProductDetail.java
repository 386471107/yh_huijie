package com.example.yh_huijie.sqldata;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity
public class ProductDetail {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="p_remote_id")
    private int p_remote_id;

    @ColumnInfo(name="p_class_name")
    private String p_class_name;
    @ColumnInfo(name="p_class_id")
    private int p_class_id;

    @ColumnInfo(name="p_name")
    private String p_name;
    @ColumnInfo(name="p_barcode")
    private String p_barcode;
    @ColumnInfo(name="p_rfid")
    private String p_rfid;

    @ColumnInfo(name="p_type")
    private String p_type;

    @ColumnInfo(name="p_stock_no")
    private String p_stock_no;

    @ColumnInfo(name="p_price")
    private double p_price;

    @ColumnInfo(name="p_weight")
    private double p_weight;

    @ColumnInfo(name="p_detail")
    private String p_detail;

    @ColumnInfo(name="p_order")
    private int class_order;

    @ColumnInfo(name="p_count")
    private int p_count;

    @ColumnInfo(name="p_display" ,defaultValue = "1")
    private Boolean p_display ;

    public ProductDetail(int p_remote_id, String p_class_name, int p_class_id, String p_name, String p_barcode, String p_rfid, String p_type, String p_stock_no, double p_price, double p_weight, String p_detail, int class_order, int p_count, Boolean p_display) {
        this.p_remote_id = p_remote_id;
        this.p_class_name = p_class_name;
        this.p_class_id = p_class_id;
        this.p_name = p_name;
        this.p_barcode = p_barcode;
        this.p_rfid = p_rfid;
        this.p_type = p_type;
        this.p_stock_no = p_stock_no;
        this.p_price = p_price;
        this.p_weight = p_weight;
        this.p_detail = p_detail;
        this.class_order = class_order;
        this.p_count = p_count;
        this.p_display = p_display;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getP_remote_id() {
        return p_remote_id;
    }

    public void setP_remote_id(int p_remote_id) {
        this.p_remote_id = p_remote_id;
    }

    public String getP_class_name() {
        return p_class_name;
    }

    public void setP_class_name(String p_class_name) {
        this.p_class_name = p_class_name;
    }

    public int getP_class_id() {
        return p_class_id;
    }

    public void setP_class_id(int p_class_id) {
        this.p_class_id = p_class_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_barcode() {
        return p_barcode;
    }

    public void setP_barcode(String p_barcode) {
        this.p_barcode = p_barcode;
    }

    public String getP_rfid() {
        return p_rfid;
    }

    public void setP_rfid(String p_rfid) {
        this.p_rfid = p_rfid;
    }

    public String getP_type() {
        return p_type;
    }

    public void setP_type(String p_type) {
        this.p_type = p_type;
    }

    public String getP_stock_no() {
        return p_stock_no;
    }

    public void setP_stock_no(String p_stock_no) {
        this.p_stock_no = p_stock_no;
    }

    public double getP_price() {
        return p_price;
    }

    public void setP_price(double p_price) {
        this.p_price = p_price;
    }

    public double getP_weight() {
        return p_weight;
    }

    public void setP_weight(double p_weight) {
        this.p_weight = p_weight;
    }

    public String getP_detail() {
        return p_detail;
    }

    public void setP_detail(String p_detail) {
        this.p_detail = p_detail;
    }

    public int getClass_order() {
        return class_order;
    }

    public void setClass_order(int class_order) {
        this.class_order = class_order;
    }

    public int getP_count() {
        return p_count;
    }

    public void setP_count(int p_count) {
        this.p_count = p_count;
    }

    public Boolean getP_display() {
        return p_display;
    }

    public void setP_display(Boolean p_display) {
        this.p_display = p_display;
    }
}


