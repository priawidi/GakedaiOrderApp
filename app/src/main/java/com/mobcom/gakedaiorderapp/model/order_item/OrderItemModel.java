package com.mobcom.gakedaiorderapp.model.order_item;

import com.google.gson.annotations.SerializedName;

public class OrderItemModel {
    @SerializedName("id")
    String id;
    @SerializedName("user_id")
    String user_id;
    @SerializedName("user_name")
    String user_name;
    @SerializedName("item_qty")
    String item_qty;
    @SerializedName("item_name")
    String item_name;
    @SerializedName("item_price")
    String item_price;
    @SerializedName("item_photo")
    String item_photo;

    public OrderItemModel(String id, String user_id, String user_name, String item_qty, String item_name, String item_price, String item_photo) {
        this.id = id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.item_qty = item_qty;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_photo = item_photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getItem_qty() {
        return item_qty;
    }

    public void setItem_qty(String item_qty) {
        this.item_qty = item_qty;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_photo() {
        return item_photo;
    }

    public void setItem_photo(String item_photo) {
        this.item_photo = item_photo;
    }
}
