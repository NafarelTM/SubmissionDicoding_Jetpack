package com.example.submissionjetpack.model.entity

data class TvShowEntity(
    val id: Int,
    val title: String,
    val year: String,
    val date: String,
    val genre: String,
    val voteAvg: Double,
    val duration: String,
    val season: String,
    val description: String,
    val posterImg: String,
    val backdropImg: String
)
