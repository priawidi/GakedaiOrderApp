package com.mobcom.gakedaiorderapp.model.history_user;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUserHistoryModel {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    String result;
    @SerializedName("menu")
    List<UserHistoryModel> ListUserHistory;
    @SerializedName("message")
    String message;

    public GetUserHistoryModel(String status, String result, List<UserHistoryModel> listUserHistory, String message) {
        this.status = status;
        this.result = result;
        ListUserHistory = listUserHistory;
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

    public List<UserHistoryModel> getListUserHistory() {
        return ListUserHistory;
    }

    public void setListUserHistory(List<UserHistoryModel> listUserHistory) {
        ListUserHistory = listUserHistory;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
