package com.example.loginduytan.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Currency;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.99:2024/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("data/data.json")
    Call<Currency> convertUsdToVnd();

    //if use key
    Call<Currency> convertUsdToVnd(@Query("access_key") String access_key,
                                   @Query("currencies") String currencies,
                                   @Query("source") String source,
                                   @Query("format") String format
    );

    @POST("Login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("Register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

    // API để lấy danh sách vai trò
    @GET("Role")
    Call<RoleResponse> getRoles(@Query("key") String key);
}
