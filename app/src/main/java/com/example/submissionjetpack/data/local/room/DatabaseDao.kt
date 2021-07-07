package com.example.submissionjetpack.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.submissionjetpack.data.local.MovieEntity
import com.example.submissionjetpack.data.local.TvShowEntity

@Dao
interface DatabaseDao {

    @Query("SELECT * FROM movie_favorite")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvshow_favorite")
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM movie_favorite WHERE movieFavored = 1")
    fun getFavoredMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvshow_favorite WHERE tvShowFavored = 1")
    fun getFavoredTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Transaction
    @Query("SELECT * FROM movie_favorite WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tvshow_favorite WHERE tvShowId = :tvShowId")
    fun getTvShowById(tvShowId: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)
}