package com.topibatu.sibarong.injection

import androidx.room.Room
import com.topibatu.sibarong.database.Database
import com.topibatu.sibarong.fragments.analyze.AnalyzeViewModel
import com.topibatu.sibarong.fragments.fragment.HistoryViewModel
import com.topibatu.sibarong.repository.RepositoryInterface
import com.topibatu.sibarong.repository.SiBarongRepository
import com.topibatu.sibarong.repository.local.LocalDataSource
import com.topibatu.sibarong.repository.remote.RemoteDataSource
import com.topibatu.sibarong.utils.AppExecutors
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val repositoryModule = module {
    single { RemoteDataSource() }
    single { LocalDataSource(get()) }
    factory { AppExecutors() }
    single<RepositoryInterface> {
        SiBarongRepository(
            get(),
            get()
        )
    }
}

val viewModelModule = module {
    viewModel { AnalyzeViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
}

val databaseModule = module {
    factory { get<Database>().databaseDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java, "db_history"
        ).fallbackToDestructiveMigration().build()
    }
}