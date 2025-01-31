package com.example.swipebhanu.direct

import com.example.swipebhanu.data.repository.ProductRepository
import com.example.swipebhanu.utils.RetrofitBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.example.swipebhanu.ui.viewmodels.ProductViewModel

val appModule = module {

    // RetrofitBuilder
    single { RetrofitBuilder }

    // ProductApiService
    single { get<RetrofitBuilder>().createService() }

    // Repository
    single { ProductRepository(get()) }

    // ViewModel
    viewModel { ProductViewModel(get()) }
}