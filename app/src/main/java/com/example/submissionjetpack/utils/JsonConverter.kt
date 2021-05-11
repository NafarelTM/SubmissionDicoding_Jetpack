package com.example.submissionjetpack.utils

import android.content.Context
import com.example.submissionjetpack.model.entity.MovieEntity
import com.example.submissionjetpack.model.entity.TvShowEntity
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonConverter(private val context: Context) {
    private fun parsingToString(file: String): String? {
        return try {
            val `is` = context.assets.open(file)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (e: IOException){
            e.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<MovieEntity>{
        val movies = ArrayList<MovieEntity>()
        try {
            val jsonObject = JSONObject(parsingToString("DataMovies.json").toString())
            val list = jsonObject.getJSONArray("results")
            for (i in 0 until list.length()){
                val movie = list.getJSONObject(i)
                val movieEntity = MovieEntity(
                    movie.getInt("id"),
                    movie.getString("title"),
                    movie.getString("year"),
                    movie.getString("releaseDate"),
                    movie.getString("genres"),
                    movie.getDouble("voteAvg"),
                    movie.getString("duration"),
                    movie.getString("description"),
                    movie.getString("posterImg"),
                    movie.getString("backdropImg")
                )
                movies.add(movieEntity)
            }
        } catch (e: JSONException){
            e.printStackTrace()
        }

        return movies
    }

    fun loadTvShows(): List<TvShowEntity>{
        val tvShows = ArrayList<TvShowEntity>()
        try {
            val jsonObject = JSONObject(parsingToString("DataTvShows.json").toString())
            val list = jsonObject.getJSONArray("results")
            for (i in 0 until list.length()){
                val tvShow = list.getJSONObject(i)
                val tvShowEntity = TvShowEntity(
                    tvShow.getInt("id"),
                    tvShow.getString("title"),
                    tvShow.getString("year"),
                    tvShow.getString("releaseDate"),
                    tvShow.getString("genres"),
                    tvShow.getDouble("voteAvg"),
                    tvShow.getString("duration"),
                    tvShow.getString("season"),
                    tvShow.getString("description"),
                    tvShow.getString("posterImg"),
                    tvShow.getString("backdropImg")
                )
                tvShows.add(tvShowEntity)
            }
        } catch (e: JSONException){
            e.printStackTrace()
        }

        return tvShows
    }
}