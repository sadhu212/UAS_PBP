package com.app.servicekendaraan.api;

import com.app.servicekendaraan.models.User;
import com.app.servicekendaraan.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @Headers({"Accept: application/json"})
    @GET("user")
    Call<UserResponse> getAllUser();
    @Headers({"Accept: application/json"})
    @GET("user/{id}")
    Call<UserResponse> getUserById(@Path("id") long id);
    @Headers({"Accept: application/json"})
    @POST("user")
    Call<UserResponse> createUser(@Body User user);
    @Headers({"Accept: application/json"})
    @PUT("user/{id}")
    Call<UserResponse> updateUser(@Path("id") long id,
                                            @Body User user);
    @Headers({"Accept: application/json"})
    @DELETE("user/{id}")
    Call<UserResponse> deleteUser(@Path("id") long id);

}
