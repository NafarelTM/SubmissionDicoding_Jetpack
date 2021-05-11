package com.example.submissionjetpack.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submissionjetpack.DataSource
import com.example.submissionjetpack.model.entity.MovieEntity
import com.example.submissionjetpack.model.entity.TvShowEntity
import com.example.submissionjetpack.remote.RemoteDataSource
import com.example.submissionjetpack.utils.LoadMovieCallback
import com.example.submissionjetpack.utils.LoadTvShowCallback

class FakeDataRepository(private val remoteDataSource: RemoteDataSource): DataSource {

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val result = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMovies(object: LoadMovieCallback {
            override fun onAllMoviesReceived(movieEntity: List<MovieEntity>) {
                val listMovie = ArrayList<MovieEntity>()
                for (response in movieEntity){
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.year,
                        response.date,
                        response.genre,
                        response.voteAvg,
                        response.duration,
                        response.description,
                        response.posterImg,
                        response.backdropImg
                    )
                    listMovie.add(movie)
                }
                result.postValue(listMovie)
            }
        })

        return result
    }

    override fun getAllTvShows(): LiveData<List<TvShowEntity>> {
        val result = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getAllTvShows(object: LoadTvShowCallback {
            override fun onAllTvShowsReceived(tvShowEntity: List<TvShowEntity>) {
                val listTvShow = ArrayList<TvShowEntity>()
                for (response in tvShowEntity){
                    val tvShow = TvShowEntity(
                        response.id,
                        response.title,
                        response.year,
                        response.date,
                        response.genre,
                        response.voteAvg,
                        response.duration,
                        response.season,
                        response.description,
                        response.posterImg,
                        response.backdropImg
                    )
                    listTvShow.add(tvShow)
                }
                result.postValue(listTvShow)
            }
        })

        return result
    }
}