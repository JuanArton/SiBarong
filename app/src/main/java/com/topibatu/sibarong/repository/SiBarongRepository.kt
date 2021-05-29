package com.topibatu.sibarong.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.topibatu.sibarong.api.DataParcel
import com.topibatu.sibarong.database.entity.HistoryEntity
import com.topibatu.sibarong.repository.local.LocalDataSource
import com.topibatu.sibarong.repository.remote.RemoteDataSource
import com.topibatu.sibarong.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SiBarongRepository(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource): RepositoryInterface {

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

    override fun getAllHistory(): LiveData<List<HistoryEntity>> {
        return localDataSource.getAllHistory()
    }

    override fun deleteHistory(historyEntity: HistoryEntity) {
        return localDataSource.deleteFromDb(historyEntity)
    }

    override fun insertText(historyEntity: HistoryEntity) {
        return localDataSource.insertHistory(historyEntity)
    }

    override fun deleteFromTable() {
        return localDataSource.deleteFromTable()
    }
}