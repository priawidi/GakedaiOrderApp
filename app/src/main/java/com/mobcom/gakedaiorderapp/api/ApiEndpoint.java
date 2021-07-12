package com.mobcom.gakedaiorderapp.api;

import android.net.Uri;

import com.mobcom.gakedaiorderapp.model.AuthModel;
import com.mobcom.gakedaiorderapp.model.CartModel;
import com.mobcom.gakedaiorderapp.model.GetAuthModel;
import com.mobcom.gakedaiorderapp.model.GetCartModel;
import com.mobcom.gakedaiorderapp.model.GetGoogleUserModel;
import com.mobcom.gakedaiorderapp.model.GetMenuModel;
import com.mobcom.gakedaiorderapp.model.PostCartModel;
import com.mobcom.gakedaiorderapp.model.PostGoogleUserModel;
import com.mobcom.gakedaiorderapp.model.PostToken;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ApiEndpoint {
    @GET("menu")
    Call<GetMenuModel> getMenu();

    @GET("filter/Minuman")
    Call<GetMenuModel> getMinum();

    @GET("filter/Makanan")
    Call<GetMenuModel> getMakan();

    @GET("google")
    Call<GetGoogleUserModel> getUser();

    @FormUrlEncoded
    @POST("google")
    Call<GetGoogleUserModel> sendUser(@Field("user_id") String id,
                                      @Field("email") String email,
                                      @Field("name") String name,
                                      @Field("picture") Uri picture,
                                      @Field("given_name") String given_name,
                                      @Field("family_name") String family_name);

    @FormUrlEncoded
    @PUT("google/{id}")
    Call<GetGoogleUserModel> updateUser(@Path("id") String id,
                                        @Field("email") String email,
                                        @Field("name") String name,
                                        @Field("picture") Uri picture,
                                        @Field("given_name") String given_name

    );

    @FormUrlEncoded
    @PUT("google")
    @HTTP(method = "DELETE", path = "user", hasBody = true)
    Call<GetGoogleUserModel> deleteMenu(@Field("id") String id);

    @GET
    Call<GetAuthModel> getAuth(@Url String url);

    @FormUrlEncoded
    @POST("token")
    Call<PostToken> sendToken(@Field("idToken") String token);

    @GET
    Call<GetCartModel> getCartUser(@Url String url);
    
    @FormUrlEncoded
    @POST("cart")
    Call<PostCartModel> sendCart(@Field("user_id") String user_id,
                                 @Field("user_name") String user_name,
                                 @Field("item_qty") String item_qty,
                                 @Field("item_name") String item_name,
                                 @Field("item_price") String item_price,
                                 @Field("item_photo") String item_photo
                                 );

    @FormUrlEncoded
    @HTTP(method = "DELETE", path ="cart", hasBody = true)
    Call<PostCartModel> deleteCart(@Field("user_id") String user_id);

}
