package com.topibatu.sibarong.injection

import com.topibatu.sibarong.fragments.analyze.AnalyzeViewModel
import com.topibatu.sibarong.repository.RepositoryInterface
import com.topibatu.sibarong.repository.SiBarongRepository
import com.topibatu.sibarong.repository.remote.RemoteDataSource
import com.topibatu.sibarong.utils.AppExecutors
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single { RemoteDataSource() }
    factory { AppExecutors() }
    single<RepositoryInterface> {
        SiBarongRepository(
            get()
        )
    }
}

val viewModelModule = module {
    viewModel { AnalyzeViewModel(get()) }
}