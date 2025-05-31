package com.example.yemeksiparisuygulamasi.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://kasimadalan.pe.hu/"

    val api: YemekApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YemekApi::class.java)
    }
}
