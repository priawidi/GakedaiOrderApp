package com.mobcom.gakedaiorderapp.model.order_item;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetOrderItemModel {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    String result;
    @SerializedName("menu")
    List<OrderItemModel> ListOrderItem;
    @SerializedName("message")
    String message;

    public GetOrderItemModel(String status, String result, List<OrderItemModel> listOrderItem, String message) {
        this.status = status;
        this.result = result;
        ListOrderItem = listOrderItem;
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

    public List<OrderItemModel> getListOrderItem() {
        return ListOrderItem;
    }

    public void setListOrderItem(List<OrderItemModel> listOrderItem) {
        ListOrderItem = listOrderItem;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
