package com.example.moviesapp.domain.use_cases

import com.example.moviesapp.domain.model.MovieDetails
import com.example.moviesapp.domain.repository.MoviesRepository

class GetMovieDetailsUseCase(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(movieId: String): Result<MovieDetails> {
        return try {
            val response = moviesRepository.getMovieDetails(movieId)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}