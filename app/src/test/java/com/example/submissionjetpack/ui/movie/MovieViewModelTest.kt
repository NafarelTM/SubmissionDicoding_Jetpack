package com.example.submissionjetpack.ui.movie

import com.example.submissionjetpack.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    private val dummyMovie = DataDummy.dataMovies()

    @Before
    fun setUp(){
        viewModel = MovieViewModel()
        viewModel.setMovieDetail(dummyMovie[0])
    }

    @Test
    fun getMovies() {
        val movieEntity = viewModel.getMovies()
        assertNotNull(movieEntity)
        assertEquals(19, movieEntity.size)
    }

    @Test
    fun getMovieDetail() {
        viewModel.setMovieDetail(dummyMovie[0])
        val movie = viewModel.getMovieDetail()
        assertNotNull(movie)
        assertEquals(dummyMovie[0].image, movie.image)
        assertEquals(dummyMovie[0].cover, movie.cover)
        assertEquals(dummyMovie[0].date, movie.date)
        assertEquals(dummyMovie[0].description, movie.description)
        assertEquals(dummyMovie[0].duration, movie.duration)
        assertEquals(dummyMovie[0].genre, movie.genre)
        assertEquals(dummyMovie[0].title, movie.title)
    }
}