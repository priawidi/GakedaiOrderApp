package com.mobcom.gakedaiorderapp.model.order_history;

import com.google.gson.annotations.SerializedName;

public class OrderHistoryModel {
    @SerializedName("id")
    String id;
    @SerializedName("meja")
    String meja;
    @SerializedName("user_id")
    String user_id;
    @SerializedName("user_name")
    String user_name;
    @SerializedName("total_price")
    String total_price;
    @SerializedName("unique_code")
    String unique_code;
    @SerializedName("order_date")
    String order_date;
    @SerializedName("order_time")
    String order_time;

    public OrderHistoryModel(String id, String meja, String user_id, String user_name, String total_price, String unique_code, String order_date, String order_time) {
        this.id = id;
        this.meja = meja;
        this.user_id = user_id;
        this.user_name = user_name;
        this.total_price = total_price;
        this.unique_code = unique_code;
        this.order_date = order_date;
        this.order_time = order_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeja() {
        return meja;
    }

    public void setMeja(String meja) {
        this.meja = meja;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getUnique_code() {
        return unique_code;
    }

    public void setUnique_code(String unique_code) {
        this.unique_code = unique_code;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }
}
