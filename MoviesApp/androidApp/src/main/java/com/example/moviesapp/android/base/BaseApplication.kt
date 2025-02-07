package com.example.moviesapp.android.base

import android.app.Application
import com.example.moviesapp.data.di.dataModule
import com.example.moviesapp.domain.di.domainModule
import com.example.moviesapp.ui.di.sharedViewModelModule
import org.koin.core.context.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                dataModule + domainModule + sharedViewModelModule()
            )
        }
    }
}