package com.example.moviesapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RatingDto(
    val Source: String,
    val Value: String
)