package com.mobcom.gakedaiorderapp.model;

import com.google.gson.annotations.SerializedName;

public class GetAuthModel {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    String result;
    @SerializedName("user")
    AuthModel authModel;
    @SerializedName("message")
    String message;

    public GetAuthModel(String status, String result, AuthModel authModel, String message) {
        this.status = status;
        this.result = result;
        this.authModel = authModel;
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

    public AuthModel getAuthModel() {
        return authModel;
    }

    public void setAuthModel(AuthModel authModel) {
        this.authModel = authModel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
