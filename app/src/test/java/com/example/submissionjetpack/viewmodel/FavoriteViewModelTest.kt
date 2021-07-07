package com.example.submissionjetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.submissionjetpack.data.local.MovieEntity
import com.example.submissionjetpack.data.local.TvShowEntity
import com.example.submissionjetpack.data.repository.DataRepository
import com.example.submissionjetpack.utils.DataDummy
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepo: DataRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<PagedList<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(dataRepo)
    }

    @Test
    fun `getMovies should be success`() {
        val expected = MutableLiveData<PagedList<MovieEntity>>()
        expected.value = MovieViewModelTest.PagedTestDataSources.snapshot(DataDummy.dataMovies())

        `when`(dataRepo.getFavoredMovie()).thenReturn(expected)

        viewModel.getMovies().observeForever(movieObserver)
        verify(movieObserver).onChanged(expected.value)

        val expectedResult = expected.value
        val actualResult = viewModel.getMovies().value
        assertEquals(expectedResult, actualResult)
        assertEquals(expectedResult?.snapshot(), actualResult?.snapshot())
        assertEquals(expectedResult?.size, actualResult?.size)
    }

    @Test
    fun `getTvShows should be success`() {
        val expected = MutableLiveData<PagedList<TvShowEntity>>()
        expected.value = TvShowViewModelTest.PagedTestDataSources.snapshot(DataDummy.dataTvShows())

        `when`(dataRepo.getFavoredTvShow()).thenReturn(expected)

        viewModel.getTvShows().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(expected.value)

        val expectedResult = expected.value
        val actualResult = viewModel.getTvShows().value
        assertEquals(expectedResult, actualResult)
        assertEquals(expectedResult?.snapshot(), actualResult?.snapshot())
        assertEquals(expectedResult?.size, actualResult?.size)
    }

    @Test
    fun `getMovies should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<MovieEntity>>()
        expected.value = MovieViewModelTest.PagedTestDataSources.snapshot()

        `when`(dataRepo.getFavoredMovie()).thenReturn(expected)

        viewModel.getMovies().observeForever(movieObserver)
        verify(movieObserver).onChanged(expected.value)

        val actualResultSize = viewModel.getMovies().value?.size
        assertTrue("data size = 0, actual size = $actualResultSize", actualResultSize == 0)
    }

    @Test
    fun `getTvShows should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<TvShowEntity>>()
        expected.value = TvShowViewModelTest.PagedTestDataSources.snapshot()

        `when`(dataRepo.getFavoredTvShow()).thenReturn(expected)

        viewModel.getTvShows().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(expected.value)

        val actualResultSize = viewModel.getTvShows().value?.size
        assertTrue("data size = 0, actual size = $actualResultSize", actualResultSize == 0)
    }
}