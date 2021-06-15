package com.topibatu.sibarong.adapter

import com.topibatu.sibarong.database.entity.HistoryEntity

interface RecyclerViewCallback {
    fun onItemClicked(historyEntity: HistoryEntity)
}