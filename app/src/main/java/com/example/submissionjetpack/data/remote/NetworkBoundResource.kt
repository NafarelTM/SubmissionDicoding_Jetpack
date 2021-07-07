package com.example.submissionjetpack.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.submissionjetpack.utils.AppExecutors
import com.example.submissionjetpack.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(private val executor: AppExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        result.addSource(loadDatabase()){
            result.removeSource(loadDatabase())
            if (shouldFetch(it)){
                fetchNetwork(loadDatabase())
            } else{
                result.addSource(loadDatabase()){ newData ->
                    result.value = Resource.success(newData)
                }
            }
        }
    }

    private fun fetchNetwork(database: LiveData<ResultType>){
        val apiResponse = createCall()

        result.addSource(database) {
            result.value = Resource.loading(it)
        }

        result.addSource(apiResponse) {
            result.removeSource(apiResponse)
            result.removeSource(database)
            when (it.status) {
                StatusResponse.SUCCESS ->
                    executor.diskIO().execute {
                        saveCallResult(it.body)
                        executor.mainThread().execute {
                            result.addSource(loadDatabase()) { newData ->
                                result.value = Resource.success(newData)
                            }
                        }
                    }

                StatusResponse.EMPTY ->
                    executor.mainThread().execute {
                        result.addSource(loadDatabase()) { newData ->
                            result.value = Resource.success(newData)
                        }
                    }

                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(loadDatabase()) { newData ->
                        result.value = Resource.error(it.message, newData)
                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun saveCallResult(data: RequestType)

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadDatabase(): LiveData<ResultType>

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}