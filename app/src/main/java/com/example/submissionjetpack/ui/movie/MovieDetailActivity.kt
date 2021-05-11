package com.example.submissionjetpack.ui.movie

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.submissionjetpack.R
import com.example.submissionjetpack.databinding.ActivityMovieDetailBinding
import com.example.submissionjetpack.model.entity.MovieEntity
import com.example.submissionjetpack.viewmodel.MovieViewModel
import com.example.submissionjetpack.viewmodel.ViewModelFactory

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        val vmFactory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, vmFactory).get(MovieViewModel::class.java)

        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, -1)
        val movieTitle = intent.getStringExtra(EXTRA_MOVIE_TITLE)

        binding.toolbarTitle.text = movieTitle
        viewModel.getMovies().observe(this, {
            populateMovie(it[movieId])
            binding.apply {
                tvDateTitle.visibility = View.VISIBLE
                tvDescTitle.visibility = View.VISIBLE
                tvGenreTitle.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        })
    }

    private fun populateMovie(movieEntity: MovieEntity) {
        binding.apply {
            tvTitle.text = movieEntity.title
            tvDuration.text = movieEntity.duration
            tvDate.text = StringBuilder("${movieEntity.date}, ${movieEntity.year}")
            tvDesc.text = movieEntity.description
            tvGenre.text = movieEntity.genre

            Glide.with(this@MovieDetailActivity)
                .asBitmap()
                .load(movieEntity.posterImg)
                .into(object: CustomTarget<Bitmap>(){
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        imgPoster.setImageBitmap(resource)
                        setColor(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })

            Glide.with(this@MovieDetailActivity)
                .load(movieEntity.backdropImg)
                .into(imgCover)
        }
    }

    private fun setColor(image: Bitmap){
        Palette.from(image).generate {
            binding.apply {
                window.statusBarColor = it?.vibrantSwatch?.rgb ?: R.color.grey
                toolbar.setBackgroundColor(it?.vibrantSwatch?.rgb ?: R.color.grey)
                detailMovie.setBackgroundColor(it?.vibrantSwatch?.rgb ?: R.color.grey)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvTitle.setTextColor(resources.getColor(R.color.white, theme))
                    tvDuration.setTextColor(resources.getColor(R.color.white, theme))
                    tvDateTitle.setTextColor(resources.getColor(R.color.white, theme))
                    tvDate.setTextColor(resources.getColor(R.color.white, theme))
                    tvDescTitle.setTextColor(resources.getColor(R.color.white, theme))
                    tvDesc.setTextColor(resources.getColor(R.color.white, theme))
                    tvGenreTitle.setTextColor(resources.getColor(R.color.white, theme))
                    tvGenre.setTextColor(resources.getColor(R.color.white, theme))
                }
            }
        }
    }

    companion object{
        const val EXTRA_MOVIE_ID = "extra_movie_id"
        const val EXTRA_MOVIE_TITLE = "extra_movie_title"
    }
}