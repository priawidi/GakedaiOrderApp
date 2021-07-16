package com.mobcom.gakedaiorderapp.model.order_history;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetOrderHistoryModel {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    String result;
    @SerializedName("menu")
    List<OrderHistoryModel> ListOrderHistory;
    @SerializedName("message")
    String message;

    public GetOrderHistoryModel(String status, String result, List<OrderHistoryModel> listOrderHistory, String message) {
        this.status = status;
        this.result = result;
        ListOrderHistory = listOrderHistory;
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

    public List<OrderHistoryModel> getListOrderHistory() {
        return ListOrderHistory;
    }

    public void setListOrderHistory(List<OrderHistoryModel> listOrderHistory) {
        ListOrderHistory = listOrderHistory;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
