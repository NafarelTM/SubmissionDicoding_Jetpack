package com.example.submissionjetpack.ui.movie

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import com.example.submissionjetpack.model.DataEntity
import com.example.submissionjetpack.utils.DataDummy

class MovieViewModel: ViewModel() {

    private lateinit var movie: DataEntity

    fun getMovies(): List<DataEntity> = DataDummy.dataMovies()

    fun setMovieDetail(parcelable: Parcelable){
        for (dataMovie in DataDummy.dataMovies()){
            if (dataMovie == parcelable) movie = dataMovie
        }
    }

    fun getMovieDetail(): DataEntity = movie
}