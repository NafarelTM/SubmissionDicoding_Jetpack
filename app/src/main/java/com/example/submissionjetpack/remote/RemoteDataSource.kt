package com.example.submissionjetpack.remote

import android.os.Handler
import android.os.Looper
import com.example.submissionjetpack.utils.IdlingResources
import com.example.submissionjetpack.utils.JsonConverter
import com.example.submissionjetpack.utils.LoadMovieCallback
import com.example.submissionjetpack.utils.LoadTvShowCallback

class RemoteDataSource private constructor(private val jsonConverter: JsonConverter){

    fun getAllMovies(callback: LoadMovieCallback){
        IdlingResources.increment()
        Handler(Looper.getMainLooper()).postDelayed({
            callback.onAllMoviesReceived(jsonConverter.loadMovies())
            IdlingResources.decrement()
        }, DELAY_MILLIS)
    }

    fun getAllTvShows(callback: LoadTvShowCallback){
        IdlingResources.increment()
        Handler(Looper.getMainLooper()).postDelayed({
            callback.onAllTvShowsReceived(jsonConverter.loadTvShows())
            IdlingResources.decrement()
        }, DELAY_MILLIS)
    }

    companion object{
        private const val DELAY_MILLIS = 2000L

        @Volatile
        private var INSTANCE: RemoteDataSource? = null
        fun getInstance(helper: JsonConverter):RemoteDataSource{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?:RemoteDataSource(helper).apply { INSTANCE = this }
            }
        }
    }
}