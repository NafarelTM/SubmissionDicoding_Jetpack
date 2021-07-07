package com.example.submissionjetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.submissionjetpack.data.local.MovieEntity
import com.example.submissionjetpack.data.local.TvShowEntity
import com.example.submissionjetpack.data.repository.DataRepository

class FavoriteViewModel(private val dataRepository: DataRepository): ViewModel() {
    fun getMovies(): LiveData<PagedList<MovieEntity>> = dataRepository.getFavoredMovie()

    fun getTvShows(): LiveData<PagedList<TvShowEntity>> = dataRepository.getFavoredTvShow()

    fun setFavMovie(movieEntity: MovieEntity){
        val favored = !movieEntity.favored
        dataRepository.setMovieFavorite(movieEntity, favored)
    }

    fun setFavTvShow(tvShowEntity: TvShowEntity){
        val favored = !tvShowEntity.favored
        dataRepository.setTvShowFavorite(tvShowEntity, favored)
    }
}