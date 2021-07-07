package com.example.submissionjetpack

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.submissionjetpack.data.local.MovieEntity
import com.example.submissionjetpack.data.local.TvShowEntity
import com.example.submissionjetpack.vo.Resource

interface DataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getFavoredMovie(): LiveData<PagedList<MovieEntity>>

    fun getFavoredTvShow(): LiveData<PagedList<TvShowEntity>>

    fun getMovieById(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getTvShowById(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun setMovieFavorite(movie: MovieEntity, favored: Boolean)

    fun setTvShowFavorite(tvShow: TvShowEntity, favored: Boolean)

}