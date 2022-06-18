package com.example.musicapiapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapiapp.MusicApp
import com.example.musicapiapp.R
import com.example.musicapiapp.adapter.MusicAdapter
import com.example.musicapiapp.adapter.MusicItemClick
import com.example.musicapiapp.databinding.FragmentRockBinding
import com.example.musicapiapp.domain.DomainMusic
import com.example.musicapiapp.model.MusicInfo
import com.example.musicapiapp.presenter.RockMusicPresenterContract
import com.example.musicapiapp.presenter.RockMusicViewContract
import javax.inject.Inject

class RockFragment : Fragment(), RockMusicViewContract {

    private val binding by lazy{
        FragmentRockBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var rockPresenter:RockMusicPresenterContract

    private val musicAdapter by lazy{
        MusicAdapter(object : MusicItemClick {
            override fun onMusicClicked(music: DomainMusic) {
                val musicInfo = MusicInfo(music.artistName,music.trackName,music.albumCover,music.releaseDate,music.preview,music.trackPrice,
                    music.trackPrice,music.collectionName,music.country,music.trackNumber,music.trackCount)
                findNavController().navigate(R.id.action_navigation_Classical_Fragment_to_navigation_Details_Fragment, bundleOf(
                    Pair(DetailsFragment.MUSIC_DATA, musicInfo)
                )
                )
            }
        })
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            MusicApp.component.inject(this)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.musicRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            adapter = musicAdapter
        }

        MusicApp.component.inject(this)
        rockPresenter.initializePresenter(this)
        rockPresenter.getAllRockMusic()

        return binding.root
    }


    override fun loadingMusic(isLoading: Boolean) {
        Toast.makeText(requireContext(), "LOADING....", Toast.LENGTH_LONG).show()
    }


    override fun allMusicLoadedSuccess(music: List<DomainMusic>, isOffline: Boolean) {
        musicAdapter.updateMusic(music)
    }

    override fun error(error: Throwable) {
        TODO("Not yet implemented")
    }


    override fun onDestroy() {
        super.onDestroy()
        rockPresenter.destroyPresenter()
    }
}