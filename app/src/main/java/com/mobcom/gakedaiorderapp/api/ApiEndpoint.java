package com.mobcom.gakedaiorderapp.api;

import android.net.Uri;

import com.mobcom.gakedaiorderapp.model.AuthModel;
import com.mobcom.gakedaiorderapp.model.GetGoogleUserModel;
import com.mobcom.gakedaiorderapp.model.GetMenuModel;
import com.mobcom.gakedaiorderapp.model.PostGoogleUserModel;

import retrofit2.Call;
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

    @GET("google")
    Call<GetGoogleUserModel> getUser();

    @FormUrlEncoded
    @POST("google")
    Call<PostGoogleUserModel> sendUser(@Field("email") String email,
                                       @Field("name") String name,
                                       @Field("picture") Uri picture,
                                       @Field("given_name") String given_name,
                                       @Field("family_name") String family_name);

    @FormUrlEncoded
    @PUT("google/{id}")
    Call<PostGoogleUserModel> updateUser(@Path("id") String id,
                                         @Field("email") String email,
                                         @Field("name") String name,
                                         @Field("picture") Uri picture,
                                         @Field("given_name") String given_name

    );

    @FormUrlEncoded
    @PUT("google")
    @HTTP(method = "DELETE", path = "user", hasBody = true)
    Call<PostGoogleUserModel> deleteMenu(@Field("id") String id);

    @GET
    Call<AuthModel> getAuth(@Url String url );

}
