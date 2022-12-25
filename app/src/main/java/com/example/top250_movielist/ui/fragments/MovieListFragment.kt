package com.example.top250_movielist.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.top250_movielist.MainViewModel
import com.example.top250_movielist.R
import com.example.top250_movielist.adapter.MovieAdapter
import com.example.top250_movielist.databinding.FragmentMovieListBinding
import com.example.top250_movielist.util.Constants.Companion.API_KEY
import com.example.top250_movielist.util.Constants.Companion.Query_ADD_RECIPES_INFORMATION
import com.example.top250_movielist.util.Constants.Companion.Query_API_KEY
import com.example.top250_movielist.util.Constants.Companion.Query_DIET
import com.example.top250_movielist.util.Constants.Companion.Query_FILL_INGREDIENTS
import com.example.top250_movielist.util.Constants.Companion.Query_NUMBER
import com.example.top250_movielist.util.Constants.Companion.Query_TYPE
import com.example.top250_movielist.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_list.view.*

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private  var binding: FragmentMovieListBinding?= null

    private lateinit var mainViewModel: MainViewModel
    private val movieAdapter by lazy { MovieAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentMovieListBinding.inflate(inflater, container, false)
        binding?.lifecycleOwner = this
        binding?.error = mainViewModel


        setUpRecyclerView()
        requestApiData()
        return binding?.root
    }

    private fun requestApiData() {
        mainViewModel.getMovie()
        mainViewModel.movieResponse.observe(viewLifecycleOwner) { response->

            when(response) {
                is NetworkResult.Success-> {
                    response.data?.let {movieAdapter.setData(it)}
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(),response.message.toString(),Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    Toast.makeText(requireContext(),"Loading",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setUpRecyclerView() {
       binding?.recyclerView?.adapter = movieAdapter

    }

}