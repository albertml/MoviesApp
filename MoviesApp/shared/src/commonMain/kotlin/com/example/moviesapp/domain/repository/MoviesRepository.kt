package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.mapper.toDomain
import com.example.moviesapp.data.remote.ApiService
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.model.MovieDetails

interface MoviesRepository {
    suspend fun getMovies(search: String): List<Movie>
    suspend fun getMovieDetails(movieId: String): MovieDetails
}

class MoviesRepositoryImpl(
    private val apiService: ApiService
): MoviesRepository {
    override suspend fun getMovies(search: String): List<Movie> {
        return apiService.getMovies(search).toDomain()
    }

    override suspend fun getMovieDetails(movieId: String): MovieDetails {
        return apiService.getMovieDetails(movieId).toDomain()
    }
}