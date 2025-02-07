package com.example.moviesapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchDto(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
)