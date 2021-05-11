package com.example.submissionjetpack

import androidx.lifecycle.LiveData
import com.example.submissionjetpack.model.entity.MovieEntity
import com.example.submissionjetpack.model.entity.TvShowEntity

interface DataSource {
    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun getAllTvShows(): LiveData<List<TvShowEntity>>
}