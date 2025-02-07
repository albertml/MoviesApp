package com.example.moviesapp.data.di

import com.example.moviesapp.data.remote.ApiService
import com.example.moviesapp.data.remote.KtorClient
import org.koin.dsl.module

val dataModule = module {
    factory { KtorClient.client }
    factory<ApiService> { ApiService(get()) }
}