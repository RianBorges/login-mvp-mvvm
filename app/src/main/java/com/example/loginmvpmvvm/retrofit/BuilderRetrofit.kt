package com.example.loginmvpmvvm.retrofit

import com.example.loginmvpmvvm.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object BuilderRetrofit {

    private const val TIME = 30L

    fun getInstance(): Api {
        val logger = HttpLoggingInterceptor()
        logger.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder()
            .connectTimeout(TIME, TimeUnit.SECONDS)
            .readTimeout(TIME, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .addInterceptor(ServiceInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(Api::class.java)
    }
}

class ServiceInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        return response.newBuilder().addHeader("Content-Type", "application/json; charset=utf-8").build()
    }
}