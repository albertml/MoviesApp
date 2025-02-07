package com.example.moviesapp.data.remote

import com.example.moviesapp.data.model.MovieDetailResponse
import com.example.moviesapp.data.model.MoviesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val APP_ID = "c92eac26"

class ApiService(val client: HttpClient) {

    // https://www.omdbapi.com/?apikey=c92eac26&s=Naruto

    suspend fun getMovies(search: String): MoviesResponse {
        return client.get {
            url {
                host = "www.omdbapi.com"
                parameters.append("apikey", APP_ID)
                parameters.append("s", search)
            }
        }.body<MoviesResponse>()
    }

    // http://www.omdbapi.com/?apikey=c92eac26&i=tt0988824
    suspend fun getMovieDetails(movieId: String): MovieDetailResponse {
        return client.get {
            url {
                host = "www.omdbapi.com"
                parameters.append("apikey", APP_ID)
                parameters.append("i", movieId)
            }
        }.body<MovieDetailResponse>()
    }
}