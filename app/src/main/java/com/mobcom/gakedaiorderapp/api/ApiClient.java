package com.mobcom.gakedaiorderapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static String BASE_URL = "https://admin.gakedai.com/api/";
    private static Retrofit retrofit;
    public static ApiEndpoint endpoint () {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiEndpoint.class);
    }
}
