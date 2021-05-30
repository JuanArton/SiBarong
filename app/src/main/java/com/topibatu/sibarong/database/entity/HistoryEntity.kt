package com.topibatu.sibarong.database.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_history")
data class HistoryEntity(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "text")
    var text: String
)