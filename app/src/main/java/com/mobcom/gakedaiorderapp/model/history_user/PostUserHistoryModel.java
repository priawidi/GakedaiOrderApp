package com.mobcom.gakedaiorderapp.model.history_user;

import com.google.gson.annotations.SerializedName;

public class PostUserHistoryModel {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    String result;
    UserHistoryModel userHistoryModel;
    @SerializedName("message")
    String message;

    public PostUserHistoryModel(String status, String result, UserHistoryModel userHistoryModel, String message) {
        this.status = status;
        this.result = result;
        this.userHistoryModel = userHistoryModel;
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

    public UserHistoryModel getUserHistoryModel() {
        return userHistoryModel;
    }

    public void setUserHistoryModel(UserHistoryModel userHistoryModel) {
        this.userHistoryModel = userHistoryModel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
