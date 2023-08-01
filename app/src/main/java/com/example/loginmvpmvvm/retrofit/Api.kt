package com.example.loginmvpmvvm.retrofit

import com.example.loginmvpmvvm.model.SignInRequest
import com.example.loginmvpmvvm.model.SignInResponse
import com.example.loginmvpmvvm.model.SignUpRequest
import com.example.loginmvpmvvm.model.SignUpResponse
import com.example.loginmvpmvvm.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @POST("signin/")
    suspend fun signIn(@Body signIn: SignInRequest): Response<SignInResponse>

    @GET("users/{iduser}")
    suspend fun getUsersData(@Path("iduser") idUser: String): Response<UserResponse>

    @POST("signup/")
    suspend fun signUp(@Body signUp: SignUpRequest): Response<SignUpResponse>
}