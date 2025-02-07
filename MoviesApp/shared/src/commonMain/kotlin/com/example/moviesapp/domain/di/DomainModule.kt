package com.example.moviesapp.domain.di

import com.example.moviesapp.domain.repository.MoviesRepository
import com.example.moviesapp.domain.repository.MoviesRepositoryImpl
import com.example.moviesapp.domain.use_cases.GetMovieDetailsUseCase
import com.example.moviesapp.domain.use_cases.GetMoviesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<MoviesRepository> { MoviesRepositoryImpl(get()) }
    factory<GetMoviesUseCase> { GetMoviesUseCase(get()) }
    factory<GetMovieDetailsUseCase> { GetMovieDetailsUseCase(get()) }
}