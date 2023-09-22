package com.example.myapplication.manager

import com.example.myapplication.model.KakaoModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.Call

interface kakao_interface {

    @GET("v2/search/image")
    fun kakao_image_search(
        @Header("Authorization") apiKey: String?,
        @Query("query") query: String?,
        @Query("sort") sort: String?,
        @Query("page") page: Int,
        @Query("size") size: Int
    ):  Call<KakaoModel?>?
}