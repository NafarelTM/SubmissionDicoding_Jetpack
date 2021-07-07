package com.example.submissionjetpack.data.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submissionjetpack.data.local.MovieEntity
import com.example.submissionjetpack.data.local.TvShowEntity
import com.example.submissionjetpack.utils.IdlingResources
import com.example.submissionjetpack.utils.JsonConverter

class RemoteDataSource private constructor(private val jsonConverter: JsonConverter){

    fun getAllMovies(): LiveData<ApiResponse<List<MovieEntity>>>{
        IdlingResources.increment()
        val movieResult = MutableLiveData<ApiResponse<List<MovieEntity>>>()
        Handler(Looper.getMainLooper()).postDelayed({
            movieResult.value = ApiResponse.success(jsonConverter.loadMovies())
            IdlingResources.decrement()
        }, DELAY_MILLIS)

        return movieResult
    }

    fun getAllTvShows(): LiveData<ApiResponse<List<TvShowEntity>>>{
        IdlingResources.increment()
        val tvShowResult = MutableLiveData<ApiResponse<List<TvShowEntity>>>()
        Handler(Looper.getMainLooper()).postDelayed({
            tvShowResult.value = ApiResponse.success(jsonConverter.loadTvShows())
            IdlingResources.decrement()
        }, DELAY_MILLIS)

        return tvShowResult
    }

    companion object{
        private const val DELAY_MILLIS = 2000L

        @Volatile
        private var INSTANCE: RemoteDataSource? = null
        fun getInstance(helper: JsonConverter): RemoteDataSource {
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: RemoteDataSource(helper).apply { INSTANCE = this }
            }
        }
    }
}