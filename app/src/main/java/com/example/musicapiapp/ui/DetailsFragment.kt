package com.example.musicapiapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.musicapiapp.R
import com.example.musicapiapp.databinding.FragmentClassicalBinding
import com.example.musicapiapp.databinding.FragmentDetailsBinding
import com.example.musicapiapp.model.MusicInfo


class DetailsFragment : Fragment() {

    private val binding by lazy{
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    private var musicInfo:MusicInfo?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            musicInfo = it.getSerializable(MUSIC_DATA) as MusicInfo
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.artisName.text = musicInfo?.artistName
        binding.trackName.text = musicInfo?.trackName
        if(musicInfo!!.trackPrice<0.00){
            binding.trackPrice.text ="FREE"
        }
        else{
            binding.trackPrice.text= musicInfo?.trackPrice.toString()
        }

        binding.releaseDate.text = musicInfo?.releaseDate



        Glide.with(binding.root)
            .load(musicInfo?.albumCover)
            .override(300,300)
            .into(binding.albumCover)



        return binding.root
    }

    companion object {
        const val MUSIC_DATA = "MUSIC_DATA"
    }

}