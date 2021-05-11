package com.example.submissionjetpack.utils

import com.example.submissionjetpack.model.entity.MovieEntity

interface LoadMovieCallback {
    fun onAllMoviesReceived(movieEntity: List<MovieEntity>)
}