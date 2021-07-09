package com.mobcom.gakedaiorderapp.model;

import com.google.gson.annotations.SerializedName;

public class PostGoogleUserModel {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    String result;
    GoogleUserModel googleUserModel;
    @SerializedName("message")
    String message;

    public PostGoogleUserModel(String status, String result, GoogleUserModel googleUserModel, String message) {
        this.status = status;
        this.result = result;
        this.googleUserModel = googleUserModel;
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

    public GoogleUserModel getGoogleUserModel() {
        return googleUserModel;
    }

    public void setGoogleUserModel(GoogleUserModel googleUserModel) {
        this.googleUserModel = googleUserModel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
