package com.topibatu.sibarong.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.topibatu.sibarong.database.entity.HistoryEntity

@Dao
interface DatabaseDao {
    @Query("SELECT * from tb_history")
    fun getAllHistory(): LiveData<List<HistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(historyEntity: HistoryEntity)

    @Delete
    fun deleteFromDb(historyEntity: HistoryEntity)

    @Query("DELETE FROM tb_history")
    fun deleteFromTable()
}