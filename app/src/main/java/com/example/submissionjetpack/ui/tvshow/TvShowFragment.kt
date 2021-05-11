package com.example.submissionjetpack.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionjetpack.databinding.FragmentTvShowBinding
import com.example.submissionjetpack.viewmodel.TvShowViewModel
import com.example.submissionjetpack.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding
    private lateinit var adapter: TvShowAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let{
            val vmFactory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, vmFactory).get(TvShowViewModel::class.java)

            adapter = TvShowAdapter()
            viewModel.getTvShows().observe(viewLifecycleOwner, {
                binding.aviIndicator.visibility = View.GONE
                adapter.setTvShow(it)
                adapter.notifyDataSetChanged()
            })

            binding.rvTvShow.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = this@TvShowFragment.adapter
            }
        }
    }

}