package com.topibatu.sibarong.repository

import androidx.lifecycle.LiveData
import com.topibatu.sibarong.api.DataParcel

interface RepositoryInterface {
    fun analyzeNews(newsText: String): LiveData<DataParcel>
}