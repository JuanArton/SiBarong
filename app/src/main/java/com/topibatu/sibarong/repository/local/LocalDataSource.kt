package com.topibatu.sibarong.repository.local

import androidx.lifecycle.LiveData
import com.topibatu.sibarong.database.DatabaseDao
import com.topibatu.sibarong.database.entity.HistoryEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LocalDataSource(private val databaseDao: DatabaseDao) : DatabaseDao {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    override fun getAllHistory(): LiveData<List<HistoryEntity>> {
        return databaseDao.getAllHistory()
    }

    override fun insertHistory(historyEntity: HistoryEntity) {
        executorService.execute {
            databaseDao.insertHistory(historyEntity)
        }
    }

    override fun deleteFromDb(historyEntity: HistoryEntity) {
        executorService.execute{
            databaseDao.deleteFromDb(historyEntity)
        }
    }

    override fun deleteFromTable() {
        executorService.execute {
            databaseDao.deleteFromTable()
        }
    }
}