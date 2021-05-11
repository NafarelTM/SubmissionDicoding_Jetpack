package com.example.submissionjetpack.model.entity

data class MovieEntity(
    val id: Int,
    val title: String,
    val year: String,
    val date: String,
    val genre: String,
    val voteAvg: Double,
    val duration: String,
    val description: String,
    val posterImg: String,
    val backdropImg: String
)
