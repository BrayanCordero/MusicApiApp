package com.example.musicapiapp.ui

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.musicapiapp.R
import com.example.musicapiapp.databinding.FragmentClassicalBinding
import com.example.musicapiapp.databinding.FragmentDetailsBinding
import com.example.musicapiapp.model.MusicInfo
import java.io.IOException
import java.text.SimpleDateFormat


class DetailsFragment : Fragment() {

    private val binding by lazy{
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    private var musicInfo:MusicInfo?=null
    private var mediaPlayer: MediaPlayer? =null

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
        binding.collectionName.text = musicInfo?.collectionName
        binding.collectionPrice.text = musicInfo?.collectionPrice.toString()
        binding.trackCount.text=musicInfo?.trackCount.toString()
        binding.countryName.text=musicInfo?.country


        Glide.with(binding.root)
            .load(musicInfo?.albumCover)
            .override(300,300)
            .into(binding.albumCover)

        binding.playBtn.setOnClickListener {
            playSong(musicInfo?.preview)
        }
//        binding.pauseBtn.setOnClickListener {
//            pauseSong()
//        }



        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null

    }


    private fun playSong(url:String?){
       if(url!=null){
                mediaPlayer= MediaPlayer()
                mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
                try {
                    mediaPlayer!!.setDataSource(url)
                    mediaPlayer!!.prepare()
                    mediaPlayer!!.start()

                }catch(e:IOException){
                    e.printStackTrace()
                }
            }
            else{
                Toast.makeText(requireContext(),"NO PREVIEW AVAILABLE",Toast.LENGTH_LONG).show()
            }
        }

    companion object {
        const val MUSIC_DATA = "MUSIC_DATA"
    }

}

//if(mediaPlayer!!.isPlaying) {
//    Toast.makeText(requireContext(),"MUSIC IS ALREADY PLAYING",Toast.LENGTH_LONG).show()
//}else

//    private fun pauseSong(){
//        if(mediaPlayer!!.isPlaying){
//            mediaPlayer!!.stop()
//            mediaPlayer!!.reset()
////            mediaPlayer!!.release()
//        }else{
//            Toast.makeText(requireContext(),"Audio Not Playing",Toast.LENGTH_LONG).show()
//        }
//    }



