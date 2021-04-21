package com.example.submissionjetpack.ui.tvshow

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.example.submissionjetpack.databinding.ActivityTvShowDetailBinding
import com.example.submissionjetpack.model.DataEntity

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            TvShowViewModel::class.java)

        val dataTvShow = intent.getParcelableExtra<DataEntity>(EXTRA_TVSHOW)
        if (dataTvShow != null){
            binding.toolbarTitle.text = dataTvShow.title

            viewModel.setTvShowDetail(dataTvShow)
            val tvShows = viewModel.getTvSHowDetail()
            populateTvShow(tvShows)
        }
    }

    private fun populateTvShow(tvShowEntity: DataEntity) {
        binding.apply {
            tvTitle.text = tvShowEntity.title
            tvDuration.text = tvShowEntity.duration
            tvDate.text = tvShowEntity.date
            tvDesc.text = tvShowEntity.description
            tvGenre.text = tvShowEntity.genre
            Glide.with(this@TvShowDetailActivity)
                .load(tvShowEntity.cover)
                .into(imgCover)
            Glide.with(this@TvShowDetailActivity)
                .load(tvShowEntity.image)
                .into(imgPoster)

            val palette: Palette = Palette.from(BitmapFactory.decodeResource(resources, tvShowEntity.image)).generate()
            val color: Palette.Swatch? = palette.vibrantSwatch
            if(color != null){
                window.statusBarColor = color.rgb
                toolbar.setBackgroundColor(color.rgb)

                detailTvShow.setBackgroundColor(color.rgb)
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
        const val EXTRA_TVSHOW = "extra_tvshow"
    }
}