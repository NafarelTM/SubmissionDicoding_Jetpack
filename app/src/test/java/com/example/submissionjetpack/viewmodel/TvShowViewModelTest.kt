package com.example.submissionjetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.submissionjetpack.data.local.TvShowEntity
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
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel
    private val dummyTvShow = DataDummy.dataTvShows()[0]

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepo: DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Before
    fun setUp(){
        viewModel = TvShowViewModel(dataRepo)
        viewModel.setSelectedTvShow(dummyTvShow.id)
    }

    @Test
    fun `getTvShows should be success`(){
        val tvShows = PagedTestDataSources.snapshot(DataDummy.dataTvShows())
        val expected = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        expected.value = Resource.success(tvShows)

        `when`(dataRepo.getAllTvShows()).thenReturn(expected)
        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedResult = expected.value
        val actualResult = viewModel.getTvShows().value
        assertEquals(expectedResult, actualResult)
        assertEquals(expectedResult?.data, actualResult?.data)
        assertEquals(expectedResult?.data?.size, actualResult?.data?.size)
    }

    @Test
    fun `getTvShows should be success but empty`(){
        val tvShows = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        expected.value = Resource.success(tvShows)

        `when`(dataRepo.getAllTvShows()).thenReturn(expected)
        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualResultSize = viewModel.getTvShows().value?.data?.size
        assertTrue("data size = 0, actual size = $actualResultSize", actualResultSize == 0)
    }

    @Test
    fun `getTvShows should be error`(){
        val expectedErrorMsg = "Error detected!"
        val expected = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        expected.value = Resource.error(expectedErrorMsg, null)

        `when`(dataRepo.getAllTvShows()).thenReturn(expected)
        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualErrorMsg = viewModel.getTvShows().value?.message
        assertEquals(expectedErrorMsg, actualErrorMsg)
    }

    class PagedTestDataSources private constructor(private val items: List<TvShowEntity>) :
        PositionalDataSource<TvShowEntity>() {
        companion object {
            fun snapshot(items: List<TvShowEntity> = listOf()): PagedList<TvShowEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<TvShowEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TvShowEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}