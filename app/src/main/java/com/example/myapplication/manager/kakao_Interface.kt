package com.example.myapplication.data

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.Response

interface kakao_interface {

    @GET("https://dapi.kakao.com/v2/search/web")
    fun kakao_image_search(
        @Header("Authorization") apiKey: String? = "KakaoAK 031dcfb15b0e58f859ab7a807508be70",
        @Query("query") query: String?,
        @Query("sort") sort: String?,
        @Query("page") page: Int,
        @Query("size") size: Int
    ):  Call<KakaoModel?>?
}