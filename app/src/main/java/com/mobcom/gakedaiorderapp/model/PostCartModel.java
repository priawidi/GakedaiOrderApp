package com.mobcom.gakedaiorderapp.model;

import com.google.gson.annotations.SerializedName;

public class PostCartModel {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    String result;
    CartModel cartModel;
    @SerializedName("message")
    String message;

    public PostCartModel(String status, String result, CartModel cartModel, String message) {
        this.status = status;
        this.result = result;
        this.cartModel = cartModel;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public CartModel getCartModel() {
        return cartModel;
    }

    public void setCartModel(CartModel cartModel) {
        this.cartModel = cartModel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
