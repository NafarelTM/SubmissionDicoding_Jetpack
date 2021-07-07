package com.example.submissionjetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.submissionjetpack.data.local.MovieEntity
import com.example.submissionjetpack.data.repository.DataRepository
import com.example.submissionjetpack.utils.DataDummy
import com.example.submissionjetpack.vo.Resource
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    private val dummyMovie = DataDummy.dataMovies()[0]

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepo: DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Before
    fun setUp(){
        viewModel = MovieViewModel(dataRepo)
        viewModel.setSelectedMovie(dummyMovie.id)
    }

    @Test
    fun `getMovies should be success`(){
        val movies = PagedTestDataSources.snapshot(DataDummy.dataMovies())
        val expected = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        expected.value = Resource.success(movies)

        `when`(dataRepo.getAllMovies()).thenReturn(expected)
        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedResult = expected.value
        val actualResult = viewModel.getMovies().value
        assertEquals(expectedResult, actualResult)
        assertEquals(expectedResult?.data, actualResult?.data)
        assertEquals(expectedResult?.data?.size, actualResult?.data?.size)
    }

    @Test
    fun `getMovies should be success but empty`(){
        val movies = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        expected.value = Resource.success(movies)

        `when`(dataRepo.getAllMovies()).thenReturn(expected)
        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualResultSize = viewModel.getMovies().value?.data?.size
        assertTrue("data size = 0, actual size = $actualResultSize", actualResultSize == 0)
    }

    @Test
    fun `getMovies should be error`(){
        val expectedErrorMsg = "Error detected!"
        val expected = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        expected.value = Resource.error(expectedErrorMsg, null)

        `when`(dataRepo.getAllMovies()).thenReturn(expected)
        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualErrorMsg = viewModel.getMovies().value?.message
        assertEquals(expectedErrorMsg, actualErrorMsg)
    }

    class PagedTestDataSources private constructor(private val items: List<MovieEntity>) :
        PositionalDataSource<MovieEntity>() {
        companion object {
            fun snapshot(items: List<MovieEntity> = listOf()): PagedList<MovieEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<MovieEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MovieEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}