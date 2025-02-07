package com.example.moviesapp.data.mapper

import com.example.moviesapp.data.model.MovieDetailResponse
import com.example.moviesapp.data.model.MoviesResponse
import com.example.moviesapp.data.model.RatingDto
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.model.MovieDetails
import com.example.moviesapp.domain.model.Rating

fun MoviesResponse.toDomain(): List<Movie> {
    return this.Search.map { movie ->
        Movie(
            poster = movie.Poster,
            title = movie.Title,
            type = movie.Type,
            year = movie.Year,
            imdbID = movie.imdbID
        )
    }
}

fun RatingDto.toDomain(): Rating {
    return Rating(
        source = Source,
        value = Value
    )
}

fun MovieDetailResponse.toDomain(): MovieDetails {
    return MovieDetails(
        actors = Actors,
        awards = Awards,
        country = Country,
        director = Director,
        genre = Genre,
        language = Language,
        metaScore = Metascore,
        plot = Plot,
        poster = Poster,
        rated = Rated,
        ratings = Ratings.map { it.toDomain() },
        released = Released,
        runtime = Runtime,
        title = Title,
        type = Type,
        writer = Writer,
        year = Year,
        imdbID = imdbID,
        imdbRating = imdbRating,
        imdbVotes = imdbVotes
    )
}