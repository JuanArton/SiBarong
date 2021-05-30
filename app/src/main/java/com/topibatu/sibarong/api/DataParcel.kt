package com.topibatu.sibarong.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataParcel (
    val news: String
) : Parcelable