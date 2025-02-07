package com.example.moviesapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val Response: String,
    val Search: List<SearchDto>,
    val totalResults: String
)