package com.example.submissionjetpack.ui.tvshow

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import com.example.submissionjetpack.model.DataEntity
import com.example.submissionjetpack.utils.DataDummy

class TvShowViewModel: ViewModel() {

    private lateinit var tvShow: DataEntity

    fun getTvShows(): List<DataEntity> = DataDummy.dataTvShows()

    fun setTvShowDetail(parcelable: Parcelable){
        for (dataTvShow in DataDummy.dataTvShows()){
            if (dataTvShow == parcelable) tvShow = dataTvShow
        }
    }

    fun getTvSHowDetail(): DataEntity = tvShow
}