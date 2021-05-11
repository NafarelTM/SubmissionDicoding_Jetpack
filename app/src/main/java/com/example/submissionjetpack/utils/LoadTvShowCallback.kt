package com.example.submissionjetpack.utils

import com.example.submissionjetpack.model.entity.TvShowEntity

interface LoadTvShowCallback {
    fun onAllTvShowsReceived(tvShowEntity: List<TvShowEntity>)
}