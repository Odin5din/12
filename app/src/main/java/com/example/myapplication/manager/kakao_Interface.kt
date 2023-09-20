package com.example.myapplication.data

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.Response

interface kakao_interface {

    @GET("v2/search/image")
    fun kakao_image_search(
        @Header("Authorization") apiKey: String? = "KakaoAK 4047252062067a053aa65bc9c7789c945",
        @Query("query") query: String?,
        @Query("sort") sort: String?,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ImageSearchResponse>
}