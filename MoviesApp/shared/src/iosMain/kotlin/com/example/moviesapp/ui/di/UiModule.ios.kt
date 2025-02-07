package com.example.moviesapp.ui.di

import com.example.moviesapp.ui.viewmodels.MovieDetailsViewModel
import com.example.moviesapp.ui.viewmodels.MoviesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.module.Module
import org.koin.dsl.module

private val viewModelModule = module {
    single { MoviesViewModel(get()) }
    single { MovieDetailsViewModel(get()) }
}

actual fun sharedViewModelModule(): Module = viewModelModule

object ProvideViewModel: KoinComponent {
    fun getMoviesViewModel(): MoviesViewModel = get()
    fun getMovieDetailViewModel(): MovieDetailsViewModel = get()
}