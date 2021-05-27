package com.topibatu.sibarong.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.topibatu.sibarong.database.entity.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase(){
    abstract fun databaseDao(): DatabaseDao
}