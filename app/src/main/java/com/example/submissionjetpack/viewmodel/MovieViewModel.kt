package com.example.submissionjetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submissionjetpack.model.entity.MovieEntity
import com.example.submissionjetpack.repository.DataRepository

class MovieViewModel(private val movieRepository: DataRepository): ViewModel() {
    fun getMovies(): LiveData<List<MovieEntity>> = movieRepository.getAllMovies()
}