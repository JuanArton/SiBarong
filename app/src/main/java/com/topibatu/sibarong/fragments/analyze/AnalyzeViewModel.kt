package com.topibatu.sibarong.fragments.analyze

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.topibatu.sibarong.api.DataParcel
import com.topibatu.sibarong.database.entity.HistoryEntity
import com.topibatu.sibarong.repository.RepositoryInterface

class AnalyzeViewModel (private val repositoryInterface: RepositoryInterface): ViewModel(){
    fun analyzeNews(newsText: String): LiveData<DataParcel> = repositoryInterface.analyzeNews(newsText)

    fun insertText(historyEntity: HistoryEntity) = repositoryInterface.insertText(historyEntity)

    fun deleteHistory(historyEntity: HistoryEntity) = repositoryInterface.deleteHistory(historyEntity)

    fun getAllHistory() : LiveData<List<HistoryEntity>> = repositoryInterface.getAllHistory()

    fun deleteFromTable() = repositoryInterface.deleteFromTable()
}