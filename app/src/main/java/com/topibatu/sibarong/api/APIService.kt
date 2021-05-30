package com.topibatu.sibarong.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("getdata_siBarong/")
    fun analyzeNews(
        @Body body: DataParcel
    ): Call<String>
}