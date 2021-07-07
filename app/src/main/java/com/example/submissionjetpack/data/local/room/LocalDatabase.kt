package com.example.submissionjetpack.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.submissionjetpack.data.local.MovieEntity
import com.example.submissionjetpack.data.local.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: LocalDatabase? = null
        fun getInstance(context: Context): LocalDatabase{
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "movie.db"
                ).build().apply { INSTANCE = this }
            }
        }
    }
}