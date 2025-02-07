package com.example.moviesapp.ui.di

import com.example.moviesapp.ui.viewmodels.MovieDetailsViewModel
import com.example.moviesapp.ui.viewmodels.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
}

actual fun sharedViewModelModule(): Module = viewModelModule