package com.mobcom.gakedaiorderapp.model;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

public class GoogleUserModel {
    @SerializedName("user_id")
    String user_id;
    @SerializedName("email")
    String email;
    @SerializedName("name")
    String name;
    @SerializedName("picture")
    Uri picture;
    @SerializedName("given_name")
    String given_name;
    @SerializedName("family_name")
    String family_name;



    public GoogleUserModel(String user_id, String email, String name, Uri picture, String given_name, String family_name) {
        this.user_id = user_id;
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.given_name = given_name;
        this.family_name = family_name;
    }
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getPicture() {
        return picture;
    }

    public void setPicture(Uri picture) {
        this.picture = picture;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }
}
