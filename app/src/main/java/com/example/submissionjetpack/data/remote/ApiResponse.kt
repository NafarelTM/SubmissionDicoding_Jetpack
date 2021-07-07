package com.example.submissionjetpack.data.remote

class ApiResponse<T>(val status: StatusResponse, val body: T, val message: String?) {
    companion object{
        fun <T> success(data: T): ApiResponse<T> = ApiResponse(StatusResponse.SUCCESS, data, null)

        fun <T> empty(msg: String?, body: T): ApiResponse<T> = ApiResponse(StatusResponse.EMPTY, body, msg)

        fun <T> error(msg: String?, body: T): ApiResponse<T> = ApiResponse(StatusResponse.ERROR, body, msg)
    }
}