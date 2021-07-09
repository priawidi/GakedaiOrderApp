package com.mobcom.gakedaiorderapp.model;

import com.google.gson.annotations.SerializedName;

public class MenuModel {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("code")
    private String code;
    @SerializedName("photo")
    private String photo;
    @SerializedName("price")
    private String price;
    @SerializedName("type")
    private String type;
    @SerializedName("detail")
    private String detail;
    @SerializedName("status")
    private String status;

    public MenuModel(String id, String name, String code, String photo, String price, String type, String detail, String status) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.photo = photo;
        this.price = price;
        this.type = type;
        this.detail = detail;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
