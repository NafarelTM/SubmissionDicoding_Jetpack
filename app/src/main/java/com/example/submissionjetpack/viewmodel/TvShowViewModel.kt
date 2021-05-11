package com.example.submissionjetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submissionjetpack.model.entity.TvShowEntity
import com.example.submissionjetpack.repository.DataRepository

class TvShowViewModel(private val tvShowRepository: DataRepository): ViewModel() {
    fun getTvShows(): LiveData<List<TvShowEntity>> = tvShowRepository.getAllTvShows()
}