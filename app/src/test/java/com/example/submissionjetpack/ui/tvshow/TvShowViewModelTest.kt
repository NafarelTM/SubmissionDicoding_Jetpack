package com.example.submissionjetpack.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.submissionjetpack.model.entity.TvShowEntity
import com.example.submissionjetpack.repository.DataRepository
import com.example.submissionjetpack.utils.DataDummy
import com.example.submissionjetpack.viewmodel.TvShowViewModel
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
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepo: DataRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setUp(){
        viewModel = TvShowViewModel(dataRepo)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = DataDummy.dataTvShows()
        val tvShows = MutableLiveData<List<TvShowEntity>>()
        tvShows.value = dummyTvShows

        `when`(dataRepo.getAllTvShows()).thenReturn(tvShows)
        val tvShowEntity = viewModel.getTvShows().value
        verify(dataRepo).getAllTvShows()
        assertNotNull(tvShowEntity)
        assertEquals(10, tvShowEntity?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}