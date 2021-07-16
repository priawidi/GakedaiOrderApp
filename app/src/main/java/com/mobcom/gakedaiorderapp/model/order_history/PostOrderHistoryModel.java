package com.mobcom.gakedaiorderapp.model.order_history;

import com.google.gson.annotations.SerializedName;

public class PostOrderHistoryModel {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    String result;
    OrderHistoryModel orderHistoryModel;
    @SerializedName("message")
    String message;

    public PostOrderHistoryModel(String status, String result, OrderHistoryModel orderHistoryModel, String message) {
        this.status = status;
        this.result = result;
        this.orderHistoryModel = orderHistoryModel;
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

    public OrderHistoryModel getOrderHistoryModel() {
        return orderHistoryModel;
    }

    public void setOrderHistoryModel(OrderHistoryModel orderHistoryModel) {
        this.orderHistoryModel = orderHistoryModel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
