package com.example.submissionjetpack.ui.tvshow

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.submissionjetpack.R
import com.example.submissionjetpack.databinding.ActivityTvShowDetailBinding
import com.example.submissionjetpack.data.local.TvShowEntity
import com.example.submissionjetpack.viewmodel.TvShowViewModel
import com.example.submissionjetpack.viewmodel.ViewModelFactory
import com.example.submissionjetpack.vo.Status

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowDetailBinding
    private lateinit var viewModel: TvShowViewModel
    private var favored: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        val vmFactory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, vmFactory).get(TvShowViewModel::class.java)

        val tvShowId = intent.getIntExtra(EXTRA_TVSHOW_ID, -1)
        val tvShowTitle = intent.getStringExtra(EXTRA_TVSHOW_TITLE)

        binding.toolbarTitle.text = tvShowTitle
        viewModel.setSelectedTvShow(tvShowId)
        viewModel.detailTvShow.observe(this, {
            when(it.status){
                Status.LOADING -> binding.apply {
                    tvDateTitle.visibility = View.GONE
                    tvDescTitle.visibility = View.GONE
                    tvGenreTitle.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    it.data?.let { tvShow ->
                        favored = it.data.favored
                        populateTvShow(tvShow)
                        setFavorite(favored)
                        binding.apply {
                            tvDateTitle.visibility = View.VISIBLE
                            tvDescTitle.visibility = View.VISIBLE
                            tvGenreTitle.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                        }
                    }
                }

                Status.ERROR -> {
                    binding.apply {
                        tvDateTitle.visibility = View.VISIBLE
                        tvDescTitle.visibility = View.VISIBLE
                        tvGenreTitle.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                    Toast.makeText(applicationContext, "Data tidak dapat dimuat", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun populateTvShow(tvShowEntity: TvShowEntity) {
        binding.apply {
            tvTitle.text = tvShowEntity.title
            tvDuration.text = StringBuilder("${tvShowEntity.duration} | ${tvShowEntity.season}")
            tvDate.text = StringBuilder("${tvShowEntity.date}, ${tvShowEntity.year}")
            tvDesc.text = tvShowEntity.description
            tvGenre.text = tvShowEntity.genre

            btnFavorite.setOnClickListener {
                viewModel.setFavorite()
                setFavorite(favored)
            }

            Glide.with(this@TvShowDetailActivity)
                .asBitmap()
                .load(tvShowEntity.posterImg)
                .into(object: CustomTarget<Bitmap>(){
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        imgPoster.setImageBitmap(resource)
                        setColor(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        placeholder?.toBitmap()?.let { setColor(it) }
                    }
                })

            Glide.with(this@TvShowDetailActivity)
                .load(tvShowEntity.backdropImg)
                .into(imgCover)
        }
    }

    private fun setColor(image: Bitmap){
        Palette.from(image).generate {
            binding.apply {
                window.statusBarColor = it?.vibrantSwatch?.rgb ?: R.color.grey
                toolbar.setBackgroundColor(it?.vibrantSwatch?.rgb ?: R.color.grey)
                detailTvShow.setBackgroundColor(it?.vibrantSwatch?.rgb ?: R.color.grey)
                btnFavorite.backgroundTintList = ColorStateList.valueOf(it?.vibrantSwatch?.rgb ?: R.color.grey)
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

    private fun setFavorite(favored: Boolean){
        if (favored){
            binding.btnFavorite.setImageDrawable(ContextCompat.getDrawable(
                this,
                R.drawable.ic_baseline_favorite_24))
        } else{
            binding.btnFavorite.setImageDrawable(ContextCompat.getDrawable(
                this,
                R.drawable.ic_baseline_favorite_border_24))
        }
    }

    companion object{
        const val EXTRA_TVSHOW_ID = "extra_tvshow_id"
        const val EXTRA_TVSHOW_TITLE = "extra_tvshow_title"
    }
}