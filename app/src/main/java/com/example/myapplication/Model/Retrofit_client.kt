package com.example.myapplication.Model

import com.example.myapplication.data.kakao_interface
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit_client {

    val apiService : kakao_interface
        get() = instance.create(kakao_interface::class.java)

    private val instance: Retrofit
        private get() {
            val gson = GsonBuilder().setLenient().create()

            return Retrofit.Builder()
                .baseUrl("https://dapi.kakao.com/v2/search/web")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
}