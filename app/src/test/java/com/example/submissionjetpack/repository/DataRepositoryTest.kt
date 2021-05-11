package com.example.submissionjetpack.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.submissionjetpack.remote.RemoteDataSource
import com.example.submissionjetpack.utils.DataDummy
import com.example.submissionjetpack.utils.LiveDataTestUtils
import com.example.submissionjetpack.utils.LoadMovieCallback
import com.example.submissionjetpack.utils.LoadTvShowCallback
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.mock

class DataRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val dataRepo = FakeDataRepository(remote)

    private val movieResponse = DataDummy.dataMovies()
    private val tvShowResponse = DataDummy.dataTvShows()

    @Test
    fun getAllMovies() {
        doAnswer {
            (it.arguments[0] as LoadMovieCallback)
                .onAllMoviesReceived(movieResponse)
            null
        }.`when`(remote).getAllMovies(any())
        val movieEntity = LiveDataTestUtils.getValue(dataRepo.getAllMovies())
        verify(remote).getAllMovies(any())
        assertNotNull(movieEntity)
        assertEquals(movieResponse.size.toLong(), movieEntity.size.toLong())
    }

    @Test
    fun getAllTvShows() {
        doAnswer {
            (it.arguments[0] as LoadTvShowCallback)
                .onAllTvShowsReceived(tvShowResponse)
            null
        }.`when`(remote).getAllTvShows(any())
        val tvShowEntity = LiveDataTestUtils.getValue(dataRepo.getAllTvShows())
        verify(remote).getAllTvShows(any())
        assertNotNull(tvShowEntity)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntity.size.toLong())
    }
}