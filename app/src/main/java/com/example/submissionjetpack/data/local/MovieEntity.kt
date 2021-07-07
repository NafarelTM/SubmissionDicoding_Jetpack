package com.example.submissionjetpack.data.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_favorite")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "year")
    val year: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "genre")
    val genre: String,

    @ColumnInfo(name = "voteAvg")
    val voteAvg: Double,

    @ColumnInfo(name = "duration")
    val duration: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "posterImg")
    val posterImg: String,

    @ColumnInfo(name = "backdropImg")
    val backdropImg: String,

    @ColumnInfo(name = "movieFavored")
    var favored: Boolean = false
)
