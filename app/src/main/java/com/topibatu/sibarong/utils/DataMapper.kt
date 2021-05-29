package com.topibatu.sibarong.utils

import com.topibatu.sibarong.api.DataParcel
import com.topibatu.sibarong.database.entity.HistoryEntity

object DataMapper {
    fun DataMapper.dataParcelToEntity(dataParcel: DataParcel) = HistoryEntity(
        text = dataParcel.news
    )
}