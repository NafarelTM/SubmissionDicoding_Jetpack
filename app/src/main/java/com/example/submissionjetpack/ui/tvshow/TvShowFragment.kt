package com.example.submissionjetpack.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionjetpack.databinding.FragmentTvShowBinding
import com.example.submissionjetpack.viewmodel.TvShowViewModel
import com.example.submissionjetpack.viewmodel.ViewModelFactory
import com.example.submissionjetpack.vo.Status

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
                when(it.status){
                    Status.LOADING -> binding.aviIndicator.visibility = View.VISIBLE

                    Status.SUCCESS ->{
                        binding.aviIndicator.visibility = View.GONE
                        adapter.submitList(it.data)
                        adapter.notifyDataSetChanged()
                    }

                    Status.ERROR ->{
                        binding.aviIndicator.visibility = View.GONE
                        Toast.makeText(context, "Data tidak dapat dimuat", Toast.LENGTH_SHORT).show()
                    }
                }
            })

            binding.rvTvShow.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = this@TvShowFragment.adapter
            }
        }
    }

}