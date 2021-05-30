package com.topibatu.sibarong.fragments.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.topibatu.sibarong.database.entity.HistoryEntity
import com.topibatu.sibarong.repository.RepositoryInterface

class HistoryViewModel(private val repositoryInterface: RepositoryInterface): ViewModel() {
    fun deleteHistory(historyEntity: HistoryEntity) = repositoryInterface.deleteHistory(historyEntity)

    fun getAllHistory() : LiveData<List<HistoryEntity>> = repositoryInterface.getAllHistory()

    fun deleteFromTable() = repositoryInterface.deleteFromTable()
}