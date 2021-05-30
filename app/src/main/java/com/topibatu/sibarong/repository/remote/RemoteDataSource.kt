package com.topibatu.sibarong.repository.remote

class RemoteDataSource {
    suspend fun analyzeNews(newsText: String): String{
        return try{
            API.services.analyzeNews(DataParcel(newsText)).await()
        } catch(e: Exception){
            return ""
        }
    }
}