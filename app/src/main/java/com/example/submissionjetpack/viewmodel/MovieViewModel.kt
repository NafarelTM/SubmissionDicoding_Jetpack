package com.example.submissionjetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.submissionjetpack.data.local.MovieEntity
import com.example.submissionjetpack.data.repository.DataRepository
import com.example.submissionjetpack.vo.Resource

class MovieViewModel(private val movieRepository: DataRepository): ViewModel() {
    val movieId = MutableLiveData<Int>()

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = movieRepository.getAllMovies()

    fun setSelectedMovie(movieId: Int){
        this.movieId.value = movieId
    }

    var detailMovie: LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId){
        movieRepository.getMovieById(it)
    }

    fun setFavorite(){
        val movie = detailMovie.value?.data
        movie?.let{
            val favored = !movie.favored
            movieRepository.setMovieFavorite(movie, favored)
        }
    }
}