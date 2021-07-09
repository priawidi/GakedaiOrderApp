package com.mobcom.gakedaiorderapp.model;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

public class GoogleUserModel {
    @SerializedName("email")
    String email;
    @SerializedName("email_verified")
    String email_verified;
    @SerializedName("name")
    String name;
    @SerializedName("picture")
    Uri picture;
    @SerializedName("given_name")
    String given_name;
    @SerializedName("family_name")
    String family_name;
    @SerializedName("locale")
    String locale;

    public GoogleUserModel(String email, String email_verified, String name, Uri picture, String given_name, String family_name, String locale) {
        this.email = email;
        this.email_verified = email_verified;
        this.name = name;
        this.picture = picture;
        this.given_name = given_name;
        this.family_name = family_name;
        this.locale = locale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(String email_verified) {
        this.email_verified = email_verified;
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

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
