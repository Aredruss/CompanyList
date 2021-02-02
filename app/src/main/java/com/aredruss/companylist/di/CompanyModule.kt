package com.aredruss.companylist.di

import com.aredruss.companylist.domain.CompanyInteractor
import com.aredruss.companylist.domain.api.CompanyService
import com.aredruss.companylist.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val companyModule = module {
    single { get<Retrofit>().create(CompanyService::class.java) }
    single { CompanyInteractor(companyService = get()) }
    viewModel { MainViewModel(get()) }
}
