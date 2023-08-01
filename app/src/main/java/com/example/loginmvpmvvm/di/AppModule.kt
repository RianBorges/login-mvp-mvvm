package com.example.loginmvpmvvm.di

import com.example.loginmvpmvvm.repositories.AccessRepository
import com.example.loginmvpmvvm.repositories.AccessRepositoryImpl
import com.example.loginmvpmvvm.repositories.HomeRepository
import com.example.loginmvpmvvm.repositories.HomeRepositoryImpl
import com.example.loginmvpmvvm.retrofit.BuilderRetrofit
import com.example.loginmvpmvvm.ui.access.login.LoginViewModel
import com.example.loginmvpmvvm.ui.access.signup.SignUpViewModel
import com.example.loginmvpmvvm.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module(override = true) {

    viewModel { LoginViewModel(repository = get()) }
    viewModel { HomeViewModel(repository = get()) }
    viewModel { SignUpViewModel(repository = get()) }

    factory<AccessRepository> { AccessRepositoryImpl(api = get()) }
    factory<HomeRepository> { HomeRepositoryImpl(api = get()) }

    single { BuilderRetrofit.getInstance() }
}