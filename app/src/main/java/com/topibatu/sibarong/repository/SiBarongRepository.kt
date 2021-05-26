package com.topibatu.sibarong.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.topibatu.sibarong.api.DataParcel
import com.topibatu.sibarong.repository.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SiBarongRepository(
    private val remoteDataSource: RemoteDataSource): RepositoryInterface {

    override fun analyzeNews(newsText: String): LiveData<DataParcel> {
        val messageString = MutableLiveData<DataParcel>()
        CoroutineScope(Dispatchers.IO).launch {
            messageString.postValue(
                DataParcel(
                    remoteDataSource.analyzeNews(newsText))
            )
        }
        return messageString
    }
}