package com.example.submissionjetpack.di

import android.content.Context
import com.example.submissionjetpack.data.local.LocalDataSource
import com.example.submissionjetpack.data.local.room.LocalDatabase
import com.example.submissionjetpack.data.remote.RemoteDataSource
import com.example.submissionjetpack.data.repository.DataRepository
import com.example.submissionjetpack.utils.AppExecutors
import com.example.submissionjetpack.utils.JsonConverter

object Injection {
    fun provideRepo(context: Context): DataRepository {
        val database = LocalDatabase.getInstance(context)
        val remoteData = RemoteDataSource.getInstance(JsonConverter(context))
        val localData = LocalDataSource.getInstance(database.databaseDao())
        val executors = AppExecutors()

        return DataRepository.getInstance(remoteData, localData, executors)
    }
}