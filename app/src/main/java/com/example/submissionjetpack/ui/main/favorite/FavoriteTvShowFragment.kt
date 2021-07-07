package com.example.submissionjetpack.ui.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submissionjetpack.R
import com.example.submissionjetpack.databinding.FragmentFavTvshowBinding
import com.example.submissionjetpack.ui.tvshow.TvShowAdapter
import com.example.submissionjetpack.viewmodel.FavoriteViewModel
import com.example.submissionjetpack.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class FavoriteTvShowFragment: Fragment() {

    private lateinit var binding: FragmentFavTvshowBinding
    private lateinit var adapter: TvShowAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFavTvshowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.rvTvShow)

        activity?.let{
            val vmFactory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, vmFactory).get(FavoriteViewModel::class.java)

            adapter = TvShowAdapter()
            viewModel.getTvShows().observe(viewLifecycleOwner, {
                if (it.isEmpty()){
                    binding.aviIndicator.visibility = View.GONE
                    binding.txtEmpty.visibility = View.VISIBLE
                    adapter.submitList(it)
                } else{
                    binding.aviIndicator.visibility = View.GONE
                    binding.txtEmpty.visibility = View.GONE
                    adapter.submitList(it)
                }
            })

            binding.rvTvShow.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = this@FavoriteTvShowFragment.adapter
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val position = viewHolder.absoluteAdapterPosition
                val tvShowEntity = adapter.getSwiped(position)
                tvShowEntity?.let { viewModel.setFavTvShow(it) }

                val snackbar = Snackbar.make(view as View, getString(R.string.fav_tvshow_deleted), Snackbar.LENGTH_LONG)
                val snackbarLayout = snackbar.view
                val text = snackbarLayout.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                text.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_undo_24, 0)
                snackbar.setAction(getString(R.string.undo_deleted)) {
                    tvShowEntity?.let { viewModel.setFavTvShow(it) }
                }
                snackbar.show()
            }
        }
    })

}