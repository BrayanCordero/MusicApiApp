package com.example.musicapiapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.musicapiapp.R
import com.example.musicapiapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val binding by lazy{
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.classicalButton.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_classical_fragment)
        }
        binding.rockBtn.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_rock_fragment)
        }
        binding.popBtn.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_pop_fragment)
        }

        return binding.root
    }

}