package com.mobcom.gakedaiorderapp.model.menu;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MenuModel {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("code")
    private String code;
    @SerializedName("photo")
    private String photo;
    @SerializedName("price")
    private String price;
    @SerializedName("type")
    private String type;
    @SerializedName("detail")
    private String detail;
    @SerializedName("status")
    private boolean status;

    public MenuModel(String id, String name, String code, String photo, String price, String type, String detail, boolean status) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.photo = photo;
        this.price = price;
        this.type = type;
        this.detail = detail;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MenuModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", photo='" + photo + '\'' +
                ", price='" + price + '\'' +
                ", type='" + type + '\'' +
                ", detail='" + detail + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuModel menuModel = (MenuModel) o;
        return isStatus() == menuModel.isStatus() &&
                getId().equals(menuModel.getId()) &&
                getName().equals(menuModel.getName()) &&
                getCode().equals(menuModel.getCode()) &&
                getPhoto().equals(menuModel.getPhoto()) &&
                getPrice().equals(menuModel.getPrice()) &&
                getType().equals(menuModel.getType()) &&
                getDetail().equals(menuModel.getDetail());
    }

    public static DiffUtil.ItemCallback<MenuModel> itemCallback = new DiffUtil.ItemCallback<MenuModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull MenuModel oldItem, @NonNull @NotNull MenuModel newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull MenuModel oldItem, @NonNull @NotNull MenuModel newItem) {
            return oldItem.equals(newItem);
        }
    };

    @BindingAdapter("android:menuImage")
    public static void loadImage(ImageView imageView, String imageUrl){
        Picasso.get().load("https://admin.gakedai.com/api/menu/" + imageUrl).into(imageView);
    }

}
