package com.example.submissionjetpack.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.submissionjetpack.DataSource
import com.example.submissionjetpack.data.local.LocalDataSource
import com.example.submissionjetpack.data.local.MovieEntity
import com.example.submissionjetpack.data.local.TvShowEntity
import com.example.submissionjetpack.data.remote.ApiResponse
import com.example.submissionjetpack.data.remote.NetworkBoundResource
import com.example.submissionjetpack.data.remote.RemoteDataSource
import com.example.submissionjetpack.utils.AppExecutors
import com.example.submissionjetpack.vo.Resource

class FakeDataRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors): DataSource {

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieEntity>>(appExecutors){
            override fun saveCallResult(data: List<MovieEntity>) {
                val listMovie = ArrayList<MovieEntity>()
                for (response in data){
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.year,
                        response.date,
                        response.genre,
                        response.voteAvg,
                        response.duration,
                        response.description,
                        response.posterImg,
                        response.backdropImg
                    )
                    listMovie.add(movie)
                }

                localDataSource.insertMovies(listMovie)
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieEntity>>> {
                return remoteDataSource.getAllMovies()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadDatabase(): LiveData<PagedList<MovieEntity>> {
                val builder = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getMovies(), builder).build()
            }
        }.asLiveData()
    }

    override fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowEntity>>(appExecutors) {
            override fun saveCallResult(data: List<TvShowEntity>) {
                val listTvShow = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.title,
                        response.year,
                        response.date,
                        response.genre,
                        response.voteAvg,
                        response.duration,
                        response.season,
                        response.description,
                        response.posterImg,
                        response.backdropImg
                    )
                    listTvShow.add(tvShow)
                }

                localDataSource.insertTvShows(listTvShow)
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowEntity>>> {
                return remoteDataSource.getAllTvShows()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadDatabase(): LiveData<PagedList<TvShowEntity>> {
                val builder = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getTvShows(), builder).build()
            }
        }.asLiveData()
    }

    override fun getFavoredMovie(): LiveData<PagedList<MovieEntity>> {
        val builder = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoredMovie(), builder).build()
    }

    override fun getFavoredTvShow(): LiveData<PagedList<TvShowEntity>> {
        val builder = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoredTvShow(), builder).build()
    }

    override fun getMovieById(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, List<MovieEntity>>(appExecutors){
            override fun saveCallResult(data: List<MovieEntity>) {
                val listMovie = ArrayList<MovieEntity>()
                for (response in data){
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.year,
                        response.date,
                        response.genre,
                        response.voteAvg,
                        response.duration,
                        response.description,
                        response.posterImg,
                        response.backdropImg
                    )
                    listMovie.add(movie)
                }

                localDataSource.insertMovies(listMovie)
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieEntity>>> {
                return remoteDataSource.getAllMovies()
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data == null || data.equals(null)
            }

            override fun loadDatabase(): LiveData<MovieEntity> {
                return localDataSource.getMovieById(movieId)
            }
        }.asLiveData()
    }

    override fun getTvShowById(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, List<TvShowEntity>>(appExecutors){
            override fun saveCallResult(data: List<TvShowEntity>) {
                val listTvShow = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.title,
                        response.year,
                        response.date,
                        response.genre,
                        response.voteAvg,
                        response.duration,
                        response.season,
                        response.description,
                        response.posterImg,
                        response.backdropImg
                    )
                    listTvShow.add(tvShow)
                }

                localDataSource.insertTvShows(listTvShow)
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowEntity>>> {
                return remoteDataSource.getAllTvShows()
            }

            override fun shouldFetch(data: TvShowEntity?): Boolean {
                return data == null || data.equals(null)
            }

            override fun loadDatabase(): LiveData<TvShowEntity> {
                return localDataSource.getTvShowById(tvShowId)
            }
        }.asLiveData()
    }

    override fun setMovieFavorite(movie: MovieEntity, favored: Boolean) {
        return appExecutors.diskIO().execute{ localDataSource.setMovieFavorite(movie, favored) }
    }

    override fun setTvShowFavorite(tvShow: TvShowEntity, favored: Boolean) {
        return appExecutors.diskIO().execute{ localDataSource.setTvShowFavorite(tvShow, favored) }
    }
}