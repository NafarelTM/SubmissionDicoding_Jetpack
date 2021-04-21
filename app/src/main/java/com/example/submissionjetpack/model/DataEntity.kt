package com.example.submissionjetpack.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataEntity(
    var image: Int,
    var title: String,
    var date: String,
    var genre: String,
    var duration: String,
    var description: String,
    var cover: Int
): Parcelable