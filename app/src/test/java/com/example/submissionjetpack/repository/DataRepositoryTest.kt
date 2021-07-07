package com.example.submissionjetpack.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.submissionjetpack.data.local.LocalDataSource
import com.example.submissionjetpack.data.local.MovieEntity
import com.example.submissionjetpack.data.local.TvShowEntity
import com.example.submissionjetpack.data.local.room.DatabaseDao
import com.example.submissionjetpack.data.remote.RemoteDataSource
import com.example.submissionjetpack.utils.*
import com.example.submissionjetpack.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.*
import java.util.concurrent.Executors

@Suppress("UNCHECKED_CAST")
class DataRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val executors = mock(AppExecutors::class.java)

    private val dataRepo = FakeDataRepository(remote, local, executors)

    private val movieResponse = DataDummy.dataMovies()
    private val movieId = movieResponse[0].id
    private val tvShowResponse = DataDummy.dataTvShows()
    private val tvShowId = tvShowResponse[0].id

    private val dataDao = mock(DatabaseDao::class.java)

    @Test
    fun getAllMovies() {
        val dataFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies()).thenReturn(dataFactory)
        dataRepo.getAllMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(movieResponse))
        verify(local).getMovies()
        assertNotNull(movieEntity.data)
        assertEquals(movieResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getAllTvShows() {
        val dataFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShows()).thenReturn(dataFactory)
        dataRepo.getAllTvShows()

        val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(tvShowResponse))
        verify(local).getTvShows()
        assertNotNull(tvShowEntity.data)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntity.data?.size?.toLong())
    }

    @Test
    fun getFavoredMovies(){
        val dataFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoredMovie()).thenReturn(dataFactory)
        dataRepo.getFavoredMovie()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(movieResponse))
        verify(local).getFavoredMovie()
        assertNotNull(movieEntity)
        assertEquals(movieResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getFavoredTvShows(){
        val dataFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoredTvShow()).thenReturn(dataFactory)
        dataRepo.getFavoredTvShow()

        val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(tvShowResponse))
        verify(local).getFavoredTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntity.data?.size?.toLong())
    }

    @Test
    fun getMovieById(){
        val dummyMovies = MutableLiveData<MovieEntity>()
        dummyMovies.value = movieResponse[0]
        `when`(local.getMovieById(movieId)).thenReturn(dummyMovies)

        val movieEntity = LiveDataTestUtils.getValue(dataRepo.getMovieById(movieId))
        verify(local, times(3)).getMovieById(movieId)
        assertNotNull(movieEntity.data)
        assertEquals(movieResponse[0].title, movieEntity.data?.title)
    }

    @Test
    fun getTvShowById(){
        val dummyTvShows = MutableLiveData<TvShowEntity>()
        dummyTvShows.value = tvShowResponse[0]
        `when`(local.getTvShowById(tvShowId)).thenReturn(dummyTvShows as LiveData<TvShowEntity>)

        val tvShowEntity = LiveDataTestUtils.getValue(dataRepo.getTvShowById(tvShowId))
        verify(local, times(3)).getTvShowById(tvShowId)
        assertNotNull(tvShowEntity.data)
        assertEquals(tvShowResponse[0].title, tvShowEntity.data?.title)
    }

    @Test
    fun setMovieFavorite(){
        val local = LocalDataSource.getInstance(dataDao)
        val dataMovie = DataDummy.dataMovies()[0]
        val expected = dataMovie.copy(favored = true)

        com.nhaarman.mockitokotlin2.doNothing().`when`(dataDao).updateMovie(expected)
        local.setMovieFavorite(dataMovie, true)
        verify(dataDao).updateMovie(expected)
    }

    @Test
    fun setTvShowFavorite(){
        val dataTvShow = DataDummy.dataTvShows()[0]
        val favored = dataTvShow.favored

        `when`(executors.diskIO()).thenReturn(Executors.newSingleThreadExecutor())
        local.setTvShowFavorite(dataTvShow, favored)

        dataRepo.setTvShowFavorite(dataTvShow, favored)
        verify(local).setTvShowFavorite(dataTvShow, favored)
        verifyNoMoreInteractions(local)
    }

}