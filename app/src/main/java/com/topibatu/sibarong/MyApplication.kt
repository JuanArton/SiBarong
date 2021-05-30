package com.topibatu.sibarong

import android.app.Application
import com.topibatu.sibarong.injection.databaseModule
import com.topibatu.sibarong.injection.repositoryModule
import com.topibatu.sibarong.injection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    repositoryModule,
                    viewModelModule,
                    databaseModule
                )
            )
        }
    }
}