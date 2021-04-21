package com.example.submissionjetpack.ui.tvshow

import com.example.submissionjetpack.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel
    private val dummyTvShow = DataDummy.dataTvShows()

    @Before
    fun setUp(){
        viewModel = TvShowViewModel()
        viewModel.setTvShowDetail(dummyTvShow[0])
    }

    @Test
    fun getTvShows() {
        val tvShowEntity = viewModel.getTvShows()
        assertNotNull(tvShowEntity)
        assertEquals(19, tvShowEntity.size)
    }

    @Test
    fun getTvSHowDetail() {
        viewModel.setTvShowDetail(dummyTvShow[0])
        val tvShow = viewModel.getTvSHowDetail()
        assertNotNull(tvShow)
        assertEquals(dummyTvShow[0].title, tvShow.title)
        assertEquals(dummyTvShow[0].genre, tvShow.genre)
        assertEquals(dummyTvShow[0].duration, tvShow.duration)
        assertEquals(dummyTvShow[0].description, tvShow.description)
        assertEquals(dummyTvShow[0].date, tvShow.date)
        assertEquals(dummyTvShow[0].cover, tvShow.cover)
        assertEquals(dummyTvShow[0].image, tvShow.image)
    }
}