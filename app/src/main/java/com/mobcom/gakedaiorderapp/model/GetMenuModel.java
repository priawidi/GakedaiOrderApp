package com.mobcom.gakedaiorderapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMenuModel {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    String result;
    @SerializedName("menu")
    List<MenuModel> ListDataMenu;
    @SerializedName("message")
    String message;

    public GetMenuModel(String status, String result, List<MenuModel> listDataMenu, String message) {
        this.status = status;
        this.result = result;
        ListDataMenu = listDataMenu;
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

    public List<MenuModel> getListDataMenu() {
        return ListDataMenu;
    }

    public void setListDataMenu(List<MenuModel> listDataMenu) {
        ListDataMenu = listDataMenu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
