package com.example.moviesapp.ui.di

import com.example.moviesapp.data.di.dataModule
import com.example.moviesapp.domain.di.domainModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            dataModule + domainModule + sharedViewModelModule()
        )
    }
}