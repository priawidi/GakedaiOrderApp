package com.mobcom.gakedaiorderapp.model.cart;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCartModel {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    String result;
    @SerializedName("menu")
    List<CartModel> ListCartMenu;
    @SerializedName("message")
    String message;

    public GetCartModel(String status, String result, List<CartModel> listCartMenu, String message) {
        this.status = status;
        this.result = result;
        ListCartMenu = listCartMenu;
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

    public List<CartModel> getListCartMenu() {
        return ListCartMenu;
    }

    public void setListCartMenu(List<CartModel> listCartMenu) {
        ListCartMenu = listCartMenu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
