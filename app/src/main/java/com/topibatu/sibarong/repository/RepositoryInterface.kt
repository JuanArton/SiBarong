package com.topibatu.sibarong.repository

import androidx.lifecycle.LiveData
import com.topibatu.sibarong.api.DataParcel
import com.topibatu.sibarong.database.entity.HistoryEntity

interface RepositoryInterface {
    fun analyzeNews(newsText: String): LiveData<DataParcel>

    fun getAllHistory(): LiveData<List<HistoryEntity>>

    fun deleteHistory(historyEntity: HistoryEntity)

    fun insertText(historyEntity: HistoryEntity)

    fun deleteFromTable()
}