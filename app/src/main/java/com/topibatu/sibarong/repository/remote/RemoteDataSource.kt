package com.topibatu.sibarong.repository.remote

import android.util.Log
import com.topibatu.sibarong.api.API
import com.topibatu.sibarong.api.DataParcel
import retrofit2.await
import java.lang.Exception

class RemoteDataSource {
    suspend fun analyzeNews(newsText: String): String{
        return try{
            API.services.analyzeNews(DataParcel(newsText)).await()
        } catch(e: Exception){
            return ""
        }
    }
}