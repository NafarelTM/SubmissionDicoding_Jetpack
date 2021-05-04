package com.example.submissionjetpack.ui.movie

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.example.submissionjetpack.databinding.ActivityMovieDetailBinding
import com.example.submissionjetpack.model.DataEntity

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)

        val dataMovie = intent.getParcelableExtra<DataEntity>(EXTRA_MOVIE)
        dataMovie?.let{
            binding.toolbarTitle.text = dataMovie.title

            viewModel.setMovieDetail(dataMovie)
            val movies = viewModel.getMovieDetail()
            populateMovie(movies)
        }
    }

    private fun populateMovie(movieEntity: DataEntity) {
        binding.apply {
            tvTitle.text = movieEntity.title
            tvDuration.text = movieEntity.duration
            tvDate.text = movieEntity.date
            tvDesc.text = movieEntity.description
            tvGenre.text = movieEntity.genre
            Glide.with(this@MovieDetailActivity)
                .load(movieEntity.cover)
                .into(imgCover)
            Glide.with(this@MovieDetailActivity)
                .load(movieEntity.image)
                .into(imgPoster)

            val palette: Palette = Palette.from(BitmapFactory.decodeResource(resources, movieEntity.image)).generate()
            val color: Palette.Swatch? = palette.vibrantSwatch
            color?.let{
                window.statusBarColor = color.rgb
                toolbar.setBackgroundColor(color.rgb)

                detailMovie.setBackgroundColor(color.rgb)
                tvTitle.setTextColor(color.titleTextColor)
                tvDuration.setTextColor(color.titleTextColor)
                tvDateTitle.setTextColor(color.titleTextColor)
                tvDate.setTextColor(color.titleTextColor)
                tvDescTitle.setTextColor(color.titleTextColor)
                tvDesc.setTextColor(color.titleTextColor)
                tvGenreTitle.setTextColor(color.titleTextColor)
                tvGenre.setTextColor(color.titleTextColor)
            }
        }
    }

    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }
}