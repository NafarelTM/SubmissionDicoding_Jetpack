package com.example.submissionjetpack.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.submissionjetpack.data.local.room.DatabaseDao

class LocalDataSource private constructor(private val databaseDao: DatabaseDao){

    fun getMovies(): DataSource.Factory<Int, MovieEntity> = databaseDao.getMovies()

    fun getTvShows(): DataSource.Factory<Int, TvShowEntity> = databaseDao.getTvShows()

    fun getFavoredMovie(): DataSource.Factory<Int, MovieEntity> = databaseDao.getFavoredMovie()

    fun getFavoredTvShow(): DataSource.Factory<Int, TvShowEntity> = databaseDao.getFavoredTvShow()

    fun getMovieById(movieId: Int): LiveData<MovieEntity> = databaseDao.getMovieById(movieId)

    fun getTvShowById(tvShowId: Int): LiveData<TvShowEntity> = databaseDao.getTvShowById(tvShowId)

    fun insertMovies(movies: List<MovieEntity>) = databaseDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvShowEntity>) = databaseDao.insertTvShows(tvShows)

    fun setMovieFavorite(movie: MovieEntity, favored: Boolean){
        movie.favored = favored
        databaseDao.updateMovie(movie)
    }

    fun setTvShowFavorite(tvShow: TvShowEntity, favored: Boolean){
        tvShow.favored = favored
        databaseDao.updateTvShow(tvShow)
    }

    companion object{
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(dao: DatabaseDao): LocalDataSource{
            return INSTANCE ?: LocalDataSource(dao).apply { INSTANCE = this }
        }
    }
}