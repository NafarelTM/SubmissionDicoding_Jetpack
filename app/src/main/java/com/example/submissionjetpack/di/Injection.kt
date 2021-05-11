package com.example.submissionjetpack.di

import android.content.Context
import com.example.submissionjetpack.remote.RemoteDataSource
import com.example.submissionjetpack.repository.DataRepository
import com.example.submissionjetpack.utils.JsonConverter

object Injection {
    fun provideRepo(context: Context): DataRepository{
        val remoteDataSource = RemoteDataSource.getInstance(JsonConverter(context))
        return DataRepository.getInstance(remoteDataSource)
    }
}