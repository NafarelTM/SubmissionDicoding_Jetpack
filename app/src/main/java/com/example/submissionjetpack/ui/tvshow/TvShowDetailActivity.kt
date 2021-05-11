package com.example.submissionjetpack.ui.tvshow

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
import com.example.submissionjetpack.databinding.ActivityTvShowDetailBinding
import com.example.submissionjetpack.model.entity.TvShowEntity
import com.example.submissionjetpack.viewmodel.TvShowViewModel
import com.example.submissionjetpack.viewmodel.ViewModelFactory

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        val vmFactory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, vmFactory).get(TvShowViewModel::class.java)

        val tvShowId = intent.getIntExtra(EXTRA_TVSHOW_ID, -1)
        val tvShowTitle = intent.getStringExtra(EXTRA_TVSHOW_TITLE)

        binding.toolbarTitle.text = tvShowTitle
        viewModel.getTvShows().observe(this, {
            populateTvShow(it[tvShowId])
            binding.apply {
                tvDateTitle.visibility = View.VISIBLE
                tvDescTitle.visibility = View.VISIBLE
                tvGenreTitle.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
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

            Glide.with(this@TvShowDetailActivity)
                .asBitmap()
                .load(tvShowEntity.posterImg)
                .into(object: CustomTarget<Bitmap>(){
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        imgPoster.setImageBitmap(resource)
                        setColor(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

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
        const val EXTRA_TVSHOW_ID = "extra_tvshow_id"
        const val EXTRA_TVSHOW_TITLE = "extra_tvshow_title"
    }
}