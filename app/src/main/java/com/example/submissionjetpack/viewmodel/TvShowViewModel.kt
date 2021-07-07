package com.example.submissionjetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.submissionjetpack.data.local.TvShowEntity
import com.example.submissionjetpack.data.repository.DataRepository
import com.example.submissionjetpack.vo.Resource

class TvShowViewModel(private val tvShowRepository: DataRepository): ViewModel() {
    val tvShowId = MutableLiveData<Int>()

    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = tvShowRepository.getAllTvShows()

    fun setSelectedTvShow(tvShowId: Int){
        this.tvShowId.value = tvShowId
    }

    var detailTvShow: LiveData<Resource<TvShowEntity>> = Transformations.switchMap(tvShowId){
        tvShowRepository.getTvShowById(it)
    }

    fun setFavorite(){
        val tvShow = detailTvShow.value?.data
        tvShow?.let{
            val favored = !tvShow.favored
            tvShowRepository.setTvShowFavorite(tvShow, favored)
        }
    }
}