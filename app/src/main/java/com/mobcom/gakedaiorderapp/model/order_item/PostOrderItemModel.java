package com.mobcom.gakedaiorderapp.model.order_item;

import com.google.gson.annotations.SerializedName;

public class PostOrderItemModel {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    String result;
    OrderItemModel orderItemModel;
    @SerializedName("message")
    String message;

    public PostOrderItemModel(String status, String result, OrderItemModel orderItemModel, String message) {
        this.status = status;
        this.result = result;
        this.orderItemModel = orderItemModel;
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

    public OrderItemModel getOrderItemModel() {
        return orderItemModel;
    }

    public void setOrderItemModel(OrderItemModel orderItemModel) {
        this.orderItemModel = orderItemModel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
